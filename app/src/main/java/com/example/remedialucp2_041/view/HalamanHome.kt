package com.example.remedialucp2_041.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedialucp2_041.room.Buku
import com.example.remedialucp2_041.viewmodel.HomeViewModel
import com.example.remedialucp2_041.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanHome(
    navigateToItemEntry: () -> Unit,
    navigateToEntryKategori: () -> Unit,
    onDetailClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Daftar Buku") }
            )
        },
        floatingActionButton = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FloatingActionButton(
                    onClick = navigateToEntryKategori,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text("Kategori", modifier = Modifier.padding(8.dp))
                }
                FloatingActionButton(
                    onClick = navigateToItemEntry,
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Buku")
                }
            }
        },
    ) { innerPadding ->
        BodyHome(
            listBuku = homeUiState.listBuku,
            onBukuClick = onDetailClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHome(
    listBuku: List<Buku>,
    onBukuClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {
        if (listBuku.isEmpty()) {
            Text(
                text = "Tidak ada data buku",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            ListBuku(
                listBuku = listBuku,
                onItemClick = { onBukuClick(it.id) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun ListBuku(
    listBuku: List<Buku>,
    onItemClick: (Buku) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = listBuku, key = { it.id }) { buku ->
            CardBuku(
                buku = buku,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(buku) }
            )
        }
    }
}

@Composable
fun CardBuku(
    buku: Buku,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = buku.judul,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = buku.status,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (buku.status == "Tersedia") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }
            Text(
                text = "ISBN: ${buku.isbn}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}