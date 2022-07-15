package br.com.dio.businesscard

import android.app.Application
import br.com.dio.businesscard.data.AppDataBase
import br.com.dio.businesscard.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDataBase.getDataBase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDao())}
}