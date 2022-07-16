package com.elton.businesscardshared

import android.app.Application
import com.elton.businesscardshared.data.AppDatabase
import com.elton.businesscardshared.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}