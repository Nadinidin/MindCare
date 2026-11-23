package com.example.nadini_3tib.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.nadini_3tib.data.entity.KonsultasiEntity

@Dao
interface KonsultasiDao {

    @Insert
    suspend fun insertKonsultasi(data: KonsultasiEntity)

    @Update
    suspend fun updateKonsultasi(data: KonsultasiEntity)

    @Query("DELETE FROM konsultasi WHERE id = :id")
    suspend fun hapusKonsultasiById(id: Int)

    @Insert
    suspend fun insertSemua(data: List<KonsultasiEntity>)

    @Query("SELECT * FROM konsultasi")
    suspend fun getSemuaKonsultasi(): List<KonsultasiEntity>

    @Query("SELECT * FROM konsultasi WHERE kategori = :kategori")
    suspend fun getKonsultasiByKategori(kategori: String): List<KonsultasiEntity>

    @Query("DELETE FROM konsultasi")
    suspend fun hapusSemuaKonsultasi()
}
