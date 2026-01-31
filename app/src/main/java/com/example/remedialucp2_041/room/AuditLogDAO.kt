package com.example.remedialucp2_041.room

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AuditLogDao {
    @Insert
    suspend fun insertLog(log: AuditLog)
}