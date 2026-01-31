package com.example.remedialucp2_041.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_log")
data class AuditLog(
    @PrimaryKey(autoGenerate = true)
    val logId: Int = 0,
    val tabel: String,
    val aksi: String,
    val dataSebelum: String?,
    val dataSesudah: String?,
    val waktu: Long = System.currentTimeMillis()
)