package com.example.remedialucp2_041.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface KategoriDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKategori(kategori: Kategori)

    @Update
    suspend fun updateKategori(kategori: Kategori)

    @Delete
    suspend fun deleteKategori(kategori: Kategori)

    @Query("SELECT * FROM kategori")
    fun getAllKategori(): Flow<List<Kategori>>

    @Query("SELECT * FROM kategori WHERE kategoriId = :id")
    fun getKategoriById(id: Int): Flow<Kategori>

    @Query("""
        WITH RECURSIVE sub_tree AS (
            SELECT kategoriId FROM kategori WHERE kategoriId = :parentId
            UNION ALL
            SELECT k.kategoriId FROM kategori k JOIN sub_tree st ON k.parentId = st.kategoriId
        )
        SELECT * FROM buku WHERE kategoriId IN sub_tree AND isDeleted = 0
    """)
    fun getBukuByKategoriRecursive(parentId: Int): Flow<List<Buku>>
}