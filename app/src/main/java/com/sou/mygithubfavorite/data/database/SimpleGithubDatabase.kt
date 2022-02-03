package com.sou.mygithubfavorite.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sou.mygithubfavorite.data.dao.RepositoryDao
import com.sou.mygithubfavorite.data.entity.GithubRepoEntity

@Database(entities = [GithubRepoEntity::class], version = 1)
abstract class SimpleGithubDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao
}