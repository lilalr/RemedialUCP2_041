package com.example.remedialucp2_041.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.remedialucp2_041.repositori.PerpustakaanApp
import com.example.remedialucp2_041.viewmodel.EditViewModel
import com.example.remedialucp2_041.viewmodel.EntryBukuViewModel
import com.example.remedialucp2_041.viewmodel.EntryKategoriViewModel
import com.example.remedialucp2_041.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(perpustakaanApp().container.repositoriPerpustakaan)
        }

        initializer {
            EntryKategoriViewModel(perpustakaanApp().container.repositoriPerpustakaan)
        }

        initializer {
            EntryBukuViewModel(perpustakaanApp().container.repositoriPerpustakaan)
        }

        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                perpustakaanApp().container.repositoriPerpustakaan
            )
        }
    }
}

fun CreationExtras.perpustakaanApp(): PerpustakaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApp)