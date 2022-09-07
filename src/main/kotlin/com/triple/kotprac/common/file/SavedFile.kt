package com.triple.kotprac.common.file

data class SavedFile(
        val name: String,
        val extension: String,
        val url: String,
        val size: String,
        val width: Int,
        val height: Int,
)