package com.triple.kotprac.review

import com.triple.kotprac.common.entity.BaseTimeEntity
import com.triple.kotprac.common.file.SavedFile
import javax.persistence.*

@Entity
@Table(name = "review_file")
class ReviewFile(
        @Column(name = "name", nullable = false)
        val name: String,
        @Column(name = "extension", nullable = false)
        val extension: String,
        @Column(name = "url", nullable = false)
        val url: String,
        @Column(name = "size", nullable = false)
        val size: String,
        @Column(name = "width")
        val width: Int,
        @Column(name = "height")
        val height: Int,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "review_id")
        var review: Review? = null,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "review_file_id")
        val id: Long? = null,
) : BaseTimeEntity() {
    private fun assignReview(review: Review) {
        review.reviewFileList.add(this)
        this.review = review
    }

    companion object {
        fun create(review: Review, saveFile: SavedFile): ReviewFile {
            val reviewFile = ReviewFile(
                    name = saveFile.name,
                    extension = saveFile.extension,
                    size = saveFile.size,
                    url = saveFile.url,
                    width = saveFile.width,
                    height = saveFile.height
            )
            reviewFile.assignReview(review)
            return reviewFile
        }
    }
}