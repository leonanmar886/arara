package com.example.arara

import android.app.Application
import com.example.arara.data.AppContainer
import com.example.arara.data.AppDataContainer

class AraraApplication: Application() {
  lateinit var container: AppContainer
    override fun onCreate() {
      super.onCreate()
      container = AppDataContainer(this)
    }
}