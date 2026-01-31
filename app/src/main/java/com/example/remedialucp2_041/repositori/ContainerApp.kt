package com.example.remedialucp2_041.repositori

import android.app.Application
import android.content.Context
import com.example.remedialucp2_041.room.PerpustakaanDatabase

interface AppContainer {
    val repositoriPerpustakaan: RepositoriPerpustakaan
}

class ContainerApp(private val context: Context) : AppContainer {
    override val repositoriPerpustakaan: RepositoriPerpustakaan by lazy {
        OfflineRepositoriPerpustakaan(
            PerpustakaanDatabase.getDatabase(context).bukuDao(),
            PerpustakaanDatabase.getDatabase(context).kategoriDao(),
            PerpustakaanDatabase.getDatabase(context).auditLogDao(),
            PerpustakaanDatabase.getDatabase(context)
        )
    }
}
class PerpustakaanApp : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerApp(this)
    }
}