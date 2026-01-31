package com.example.remedialucp2_041.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BukuDao {

    @Query("SELECT * FROM buku WHERE isDeleted = 0 ORDER BY judul ASC")
    fun getAllBuku(): Flow<List<Buku>>

    @Query("SELECT * FROM buku WHERE id = :id")
    fun getBuku(id: Int): Flow<Buku?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBuku(buku: Buku)

    @Update
    suspend fun updateBuku(buku: Buku)

    @Query("UPDATE buku SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Int)

    @Query("SELECT * FROM buku WHERE kategoriId = :catId AND status = 'Dipinjam' AND isDeleted = 0")
    suspend fun getBukuDipinjam(catId: Int): List<Buku>
}