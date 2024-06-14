package com.example.arara.services

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.arara.ui.activities.ImageSelectionActivity

class ImageService(private val context: Context) {
	fun getImageFromGallery() {
		val intent = Intent(context, ImageSelectionActivity::class.java)
		startActivity(context, intent, null)
	}
}