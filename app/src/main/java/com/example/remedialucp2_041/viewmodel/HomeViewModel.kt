package com.example.remedialucp2_041.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedialucp2_041.repositori.RepositoriPerpustakaan
import com.example.remedialucp2_041.room.Buku
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repositori: RepositoriPerpustakaan) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = repositori.getAllBuku()
        .map { HomeUiState(listBuku = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState()
        )
}

data class HomeUiState(
    val listBuku: List<Buku> = listOf()
)