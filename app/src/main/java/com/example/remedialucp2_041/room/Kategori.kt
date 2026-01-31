package com.example.remedialucp2_041.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val kategoriId: Int = 0,
    val namaKategori: String,
    val parentId: Int? = null
)