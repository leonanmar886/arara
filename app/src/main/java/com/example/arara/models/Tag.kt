package com.example.arara.models

import kotlinx.serialization.Serializable

@Serializable
data class Tag (
	var name: String
) {
	constructor() : this(
		""
	)
}