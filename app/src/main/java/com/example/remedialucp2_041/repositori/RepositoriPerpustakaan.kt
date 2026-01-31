package com.example.remedialucp2_041.repositori

import com.example.remedialucp2_041.room.AuditLogDao
import com.example.remedialucp2_041.room.Buku
import com.example.remedialucp2_041.room.BukuDao
import com.example.remedialucp2_041.room.Kategori
import com.example.remedialucp2_041.room.KategoriDao
import com.example.remedialucp2_041.room.PerpustakaanDatabase
import kotlinx.coroutines.flow.Flow

interface RepositoriPerpustakaan {
    fun getAllBuku(): Flow<List<Buku>>
    fun getBukuByKategori(id: Int): Flow<List<Buku>>
    fun getBukuStream(id: Int): Flow<Buku?>
    suspend fun insertBuku(buku: Buku)
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateBuku(buku: Buku)
    suspend fun deleteBuku(buku: Buku) // Ubah Int jadi Buku agar sesuai alur Soft Delete
}

class OfflineRepositoriPerpustakaan(
    private val bukuDao: BukuDao,
    private val kategoriDao: KategoriDao,
    private val auditLogDao: AuditLogDao,
    private val database: PerpustakaanDatabase
) : RepositoriPerpustakaan {

    override fun getAllBuku(): Flow<List<Buku>> = bukuDao.getAllBuku()

    override fun getBukuByKategori(id: Int): Flow<List<Buku>> =
        kategoriDao.getBukuByKategoriRecursive(id)

    override fun getBukuStream(id: Int): Flow<Buku?> = bukuDao.getBuku(id)

    override suspend fun insertBuku(buku: Buku) = bukuDao.insertBuku(buku)

    override suspend fun insertKategori(kategori: Kategori) = kategoriDao.insertKategori(kategori)

    override suspend fun updateBuku(buku: Buku) = bukuDao.updateBuku(buku)

    // Logika Soft Delete sesuai soal (mengubah isDeleted jadi 1)
    override suspend fun deleteBuku(buku: Buku) {
        val bukuTerhapus = buku.copy(isDeleted = 1)
        bukuDao.updateBuku(bukuTerhapus)
    }
}