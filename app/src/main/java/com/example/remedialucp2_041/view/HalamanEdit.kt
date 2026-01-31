package com.example.remedialucp2_041.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedialucp2_041.viewmodel.EditViewModel
import com.example.remedialucp2_041.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanEdit(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.uiStateBuku

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Edit Buku") })
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
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateBuku()
                        navigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update")
            }
        }
    }
}