package com.triple.kotprac.review.service

import com.triple.kotprac.common.file.S3Uploader
import com.triple.kotprac.review.Review
import com.triple.kotprac.review.ReviewFile
import com.triple.kotprac.review.repository.ReviewFileRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.stream.Collectors


@Service
class ReviewFileService(
        private val s3Uploader: S3Uploader,
        private val reviewFileRepository: ReviewFileRepository
) {

    fun uploadList(review: Review, images: List<MultipartFile>?): List<Long?>? {
        if (images != null) {
            val uploadFileList = s3Uploader.uploadFileList(images)
            val files = uploadFileList.stream().map { image -> ReviewFile.create(review, image) }.collect(Collectors.toList())
            reviewFileRepository.saveAll(files)
            return files.stream().map { reviewFile -> reviewFile.id }.collect(Collectors.toList())
        }
        return emptyList()
    }

    fun updateFiles(imageChanged: Boolean, review: Review, images: List<MultipartFile>?): List<Long?>? {
        if (imageChanged) {
            deleteByReview(review)
            return uploadList(review,images)
        }
        return emptyList()
    }

    fun deleteByReview(review: Review) {
        val reviewFileList = review.reviewFileList
        if (reviewFileList.size > 0) {
            val reviewFileIds = review.reviewFileList.stream().map { reviewFile -> reviewFile.id!! }.collect(Collectors.toList())
            reviewFileRepository.deleteAllByIdIn(reviewFileIds)
            reviewFileList.forEach { reviewFile -> s3Uploader.delete(reviewFile.name) }
        }
    }
}