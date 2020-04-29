package com.lockwood.themoviedb.movies.di.module.favorite

import android.content.Context
import androidx.room.Room
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.cache.db.FavoriteMoviesDatabase
import dagger.Module
import dagger.Provides

@Module
class FavoriteMoviesRoomModule {

    companion object {

        private const val DB_NAME: String = "movies.db"

        private const val MIGRATION_1_2 = "MIGRATION_1_2"
        private const val MIGRATION_2_3 = "MIGRATION_2_3"
        private const val MIGRATION_3_4 = "MIGRATION_3_4"
    }

    @Provides
    @FeatureScope
    fun provideAppDatabase(
//        migrations: Array<Migration>,
        context: Context
    ): FavoriteMoviesDatabase {
        val db = Room.databaseBuilder(
            context, FavoriteMoviesDatabase::class.java,
            DB_NAME
        )
//        migrations.forEach { db.addMigrations(it) }
        return db.build()
    }

}