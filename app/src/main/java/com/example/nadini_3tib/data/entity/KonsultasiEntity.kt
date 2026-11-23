package com.example.nadini_3tib.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "konsultasi")
data class KonsultasiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val pesan: String,
    val avatarUrl: String,
    val kategori: String,
    val distance: String,
    val travelTime: String
)
