package com.example.remedialucp2_041.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.remedialucp2_041.repositori.RepositoriPerpustakaan
import com.example.remedialucp2_041.room.Kategori

class EntryKategoriViewModel(private val repositori: RepositoriPerpustakaan) : ViewModel() {
    var uiState by mutableStateOf(KategoriUiState())
        private set

    fun updateUiState(kategoriEvent: KategoriEvent) {
        uiState = KategoriUiState(kategoriEvent = kategoriEvent)
    }

    suspend fun saveKategori() {
        repositori.insertKategori(uiState.kategoriEvent.toKategori())
    }
}

data class KategoriUiState(
    val kategoriEvent: KategoriEvent = KategoriEvent()
)

data class KategoriEvent(
    val id: Int = 0,
    val namaKategori: String = "",
    val parentId: Int? = null
)

fun KategoriEvent.toKategori(): Kategori = Kategori(
    kategoriId = id,
    namaKategori = namaKategori,
    parentId = parentId
)