package com.example.remedialucp2_041.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedialucp2_041.viewmodel.EntryKategoriViewModel
import com.example.remedialucp2_041.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanEntryKategori(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: EntryKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Entry Kategori") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = uiState.kategoriEvent.namaKategori,
                onValueChange = {
                    viewModel.updateUiState(uiState.kategoriEvent.copy(namaKategori = it))
                },
                label = { Text("Nama Kategori") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Mengganti Deskripsi menjadi Parent ID sesuai Entity kamu
            OutlinedTextField(
                value = uiState.kategoriEvent.parentId?.toString() ?: "",
                onValueChange = {
                    viewModel.updateUiState(uiState.kategoriEvent.copy(parentId = it.toIntOrNull()))
                },
                label = { Text("Parent ID (Angka)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveKategori()
                        navigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Simpan Kategori")
            }
        }
    }
}