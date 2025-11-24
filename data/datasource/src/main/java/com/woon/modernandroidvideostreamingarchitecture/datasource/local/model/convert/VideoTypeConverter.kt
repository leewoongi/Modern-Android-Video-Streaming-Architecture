package com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.convert

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.woon.modernandroidvideostreamingarchitecture.datasource.remote.model.video.VideoFilesDto

class VideoTypeConverters {
    private val gson = Gson()

    // VideoFilesDto → String (저장할 때)
    @TypeConverter
    fun fromVideoFiles(videoFiles: VideoFilesDto?): String {
        return gson.toJson(videoFiles)
    }

    // String → VideoFilesDto (읽을 때)
    @TypeConverter
    fun toVideoFiles(videoFilesString: String?): VideoFilesDto? {
        return if (videoFilesString == null) {
            null
        } else {
            gson.fromJson(videoFilesString, VideoFilesDto::class.java)
        }
    }
}