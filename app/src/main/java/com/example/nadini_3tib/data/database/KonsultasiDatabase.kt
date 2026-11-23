package com.example.nadini_3tib.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nadini_3tib.data.dao.KonsultasiDao
import com.example.nadini_3tib.data.entity.KonsultasiEntity

@Database(
    entities = [KonsultasiEntity::class],
    version = 2,
    exportSchema = false
)
abstract class KonsultasiDatabase : RoomDatabase() {

    abstract fun konsultasiDao(): KonsultasiDao

    companion object {

        @Volatile
        private var INSTANCE: KonsultasiDatabase? = null

        fun getDatabase(context: Context): KonsultasiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KonsultasiDatabase::class.java,
                    "konsultasi_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
