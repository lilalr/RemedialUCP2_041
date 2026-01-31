package com.example.remedialucp2_041.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buku")
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val isbn: String,
    val kategoriId: Int?,
    val status: String = "Tersedia",
    val isDeleted: Int = 0
)