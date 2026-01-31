package com.example.remedialucp2_041.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.remedialucp2_041.repositori.RepositoriPerpustakaan
import com.example.remedialucp2_041.room.Buku

class EntryBukuViewModel(private val repositori: RepositoriPerpustakaan) : ViewModel() {

    var uiState by mutableStateOf(BukuUiState())
        private set

    fun updateUiState(bukuEvent: BukuEvent) {
        uiState = BukuUiState(
            bukuEvent = bukuEvent,
            isEntryValid = validasiInput(bukuEvent)
        )
    }

    private fun validasiInput(uiState: BukuEvent = this.uiState.bukuEvent): Boolean {
        return with(uiState) {
            judul.isNotBlank() && isbn.isNotBlank() && status.isNotBlank()
        }
    }

    suspend fun saveBuku() {
        if (validasiInput()) {
            repositori.insertBuku(uiState.bukuEvent.toBuku())
        }
    }
}

data class BukuUiState(
    val bukuEvent: BukuEvent = BukuEvent(),
    val isEntryValid: Boolean = false
)

data class BukuEvent(
    val id: Int = 0,
    val judul: String = "",
    val isbn: String = "",
    val kategoriId: Int? = null,
    val status: String = "Tersedia"
)

fun BukuEvent.toBuku(): Buku = Buku(
    id = id,
    judul = judul,
    isbn = isbn,
    kategoriId = kategoriId,
    status = status,
    isDeleted = 0
)

fun Buku.toUiStateBuku(isEntryValid: Boolean = false): BukuUiState = BukuUiState(
    bukuEvent = this.toBukuEvent(),
    isEntryValid = isEntryValid
)

fun Buku.toBukuEvent(): BukuEvent = BukuEvent(
    id = id,
    judul = judul,
    isbn = isbn,
    kategoriId = kategoriId,
    status = status
)