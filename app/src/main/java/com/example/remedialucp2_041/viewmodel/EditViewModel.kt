package com.example.remedialucp2_041.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedialucp2_041.repositori.RepositoriPerpustakaan
import com.example.remedialucp2_041.view.route.DestinasiEdit
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriPerpustakaan: RepositoriPerpustakaan
) : ViewModel() {

    var uiStateBuku by mutableStateOf(BukuUiState())
        private set

    private val idBuku: Int = checkNotNull(savedStateHandle[DestinasiEdit.BUKU_ID_ARG])

    init {
        viewModelScope.launch {
            uiStateBuku = repositoriPerpustakaan.getBukuStream(idBuku)
                .filterNotNull()
                .first()
                .toUiStateBuku(true)
        }
    }

    fun updateUiState(bukuEvent: BukuEvent) {
        uiStateBuku = BukuUiState(
            bukuEvent = bukuEvent,
            isEntryValid = validasiInput(bukuEvent)
        )
    }

    private fun validasiInput(uiState: BukuEvent = uiStateBuku.bukuEvent): Boolean {
        return with(uiState) {
            judul.isNotBlank() && isbn.isNotBlank() && status.isNotBlank()
        }
    }

    suspend fun updateBuku() {
        if (validasiInput(uiStateBuku.bukuEvent)) {
            repositoriPerpustakaan.updateBuku(uiStateBuku.bukuEvent.toBuku())
        }
    }
}

