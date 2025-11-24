package com.woon.modernandroidvideostreamingarchitecture.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.dao.MediaDao
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.dao.MediaRemoteKeyDao
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaEntity
import com.woon.modernandroidvideostreamingarchitecture.datasource.local.model.media.MediaRemoteKey

@Database(
    entities = [
        MediaEntity::class,
        MediaRemoteKey::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
    abstract fun mediaRemoteKeysDao(): MediaRemoteKeyDao

    companion object {
        const val DATABASE_NAME = "mvs_database"
    }
}