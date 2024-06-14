package com.example.arara.ui.activities

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
			} else {
				Log.d("PhotoPicker", "No media selected")
			}
			finish()
		}
		
		pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

	}
}