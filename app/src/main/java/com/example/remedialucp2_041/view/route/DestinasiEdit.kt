package com.example.remedialucp2_041.view.route

import com.example.remedialucp2_041.R

object DestinasiEdit : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_buku
    const val BUKU_ID_ARG = "itemId"
    val routeWithArgs = "$route/{$BUKU_ID_ARG}"
}