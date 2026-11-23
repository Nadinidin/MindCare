package com.example.nadini_3tib.data.repository

import com.example.nadini_3tib.data.dao.KonsultasiDao
import com.example.nadini_3tib.data.entity.KonsultasiEntity
import com.example.nadini_3tib.data.seed.KonsultasiDataAwal

class KonsultasiRepository(
    private val dao: KonsultasiDao
) {

    suspend fun insertKonsultasi(data: KonsultasiEntity) {
        dao.insertKonsultasi(data)
    }

    suspend fun updateKonsultasi(data: KonsultasiEntity) {
        dao.updateKonsultasi(data)
    }

    suspend fun hapusKonsultasiById(id: Int) {
        dao.hapusKonsultasiById(id)
    }

    suspend fun insertSemua(data: List<KonsultasiEntity>) {
        dao.insertSemua(data)
    }

    suspend fun getSemuaKonsultasi(): List<KonsultasiEntity> {
        return dao.getSemuaKonsultasi()
    }

    suspend fun getKonsultasiByKategori(kategori: String): List<KonsultasiEntity> {
        return dao.getKonsultasiByKategori(kategori)
    }

    suspend fun hapusSemuaKonsultasi() {
        dao.hapusSemuaKonsultasi()
    }

    suspend fun isiDataAwalJikaKosong() {

        val dataPsikiater = dao.getKonsultasiByKategori("Psikiater")
        if (dataPsikiater.isEmpty()) {
            dao.insertSemua(KonsultasiDataAwal.daftarPsikiater)
        }

        val dataPsikolog = dao.getKonsultasiByKategori("Psikolog")
        if (dataPsikolog.isEmpty()) {
            dao.insertSemua(KonsultasiDataAwal.daftarPsikolog)
        }
    }
}
