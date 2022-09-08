package com.triple.kotprac.common.file

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileOutputStream
import java.util.*
import java.util.stream.Collectors
import javax.imageio.ImageIO

@Component
class S3Uploader(
        private val s3Client: AmazonS3Client,
        @Value("\${cloud.aws.s3.bucket}")
        private val s3Bucket: String,
        @Value("\${cloud.aws.s3.public}")
        private val s3Public: String,
        @Value("\${s3.file.prefix}")
        private val fileNamePrefix: String,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun uploadFileList(files: List<MultipartFile>): List<SavedFile> {
        return files.stream().map { file -> upload(file) }.collect(Collectors.toList())
    }

    fun upload(file: MultipartFile): SavedFile {
        val originalName = file.originalFilename
        var extension: String? = null
        if (originalName?.contains(".") == true) {
            extension = originalName.substring(originalName.lastIndexOf(".") + 1)
        }
        val s3FileName: String = UUID.randomUUID().toString() + "." + extension
        val publicUrl = "$s3Public/$s3FileName"
        val isImage = isImage(extension)
        var width: Int? = null
        var height: Int? = null
        try {
            val uploadFile: File = convert(file)
                    ?: throw IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다.")
            putS3(uploadFile, s3FileName)

            if (isImage) {
                val image: BufferedImage = ImageIO.read(file.inputStream)
                width = image.width
                height = image.height
            }
            removeNewFile(uploadFile)
        } catch (e: Exception) {
            removeNewFile(File(fileNamePrefix + file.originalFilename))
            log.error("S3 업로드 과정이 실패하였습니다.", e)
        }
        return SavedFile(name = s3FileName, extension = extension!!, size = file.size, url = publicUrl, width = width, height = height)
    }

    private fun putS3(uploadFile: File, fileName: String) {
        s3Client.putObject(PutObjectRequest(s3Bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead))
    }

    private fun removeNewFile(targetFile: File) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.")
        } else {
            log.info("파일이 삭제되지 못했습니다.")
        }
    }

    private fun isImage(extension: String?): Boolean {
        return Optional.ofNullable(extension).map { s -> s.lowercase().matches("png|jpeg|jpg|bmp|gif|svg".toRegex()) }.orElse(false)
    }

    private fun convert(file: MultipartFile): File? {
        val convertFile = File(fileNamePrefix + file.originalFilename)
        if (convertFile.createNewFile()) {
            FileOutputStream(convertFile).use { fos -> fos.write(file.bytes) }
            return convertFile
        }
        return null
    }

    fun delete(fileName: String?) {
        val isExistObject: Boolean = s3Client.doesObjectExist(s3Bucket, fileName)
        if (isExistObject) {
            s3Client.deleteObject(s3Bucket, fileName)
        }
    }
}