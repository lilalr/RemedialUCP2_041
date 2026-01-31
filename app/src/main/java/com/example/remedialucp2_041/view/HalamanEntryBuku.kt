package com.example.remedialucp2_041.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedialucp2_041.viewmodel.EntryBukuViewModel
import com.example.remedialucp2_041.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanEntryBuku(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: EntryBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Entry Buku") }
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
                value = uiState.bukuEvent.judul,
                onValueChange = { viewModel.updateUiState(uiState.bukuEvent.copy(judul = it)) },
                label = { Text("Judul Buku") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.bukuEvent.isbn,
                onValueChange = { viewModel.updateUiState(uiState.bukuEvent.copy(isbn = it)) },
                label = { Text("ISBN") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.bukuEvent.kategoriId.toString(),
                onValueChange = { viewModel.updateUiState(uiState.bukuEvent.copy(kategoriId = it.toIntOrNull() ?: 0)) },
                label = { Text("ID Kategori") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveBuku()
                        navigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}