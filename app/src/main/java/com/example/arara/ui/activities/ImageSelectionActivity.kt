package com.example.arara.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ImageSelectionActivity : AppCompatActivity() {
	private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
			if (uri != null) {
				Log.d("PhotoPicker", "Selected URI: $uri")
				val resultIntent = Intent().apply {
					data = uri
				}
				setResult(Activity.RESULT_OK, resultIntent)
			} else {
				Log.d("PhotoPicker", "No media selected")
				setResult(Activity.RESULT_CANCELED)
			}
			finish()
		}
		
		pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
	}
}