package com.example.remedialucp2_041.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penulis")
data class Penulis(
    @PrimaryKey(autoGenerate = true)
    val penulisId: Int = 0,
    val namaPenulis: String
)

@Entity(
    tableName = "buku_penulis_cross_ref",
    primaryKeys = ["id", "penulisId"]
)
data class BukuPenulisCrossRef(
    val id: Int,
    val penulisId: Int
)