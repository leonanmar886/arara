package com.example.arara.models

data class Clothes (
    val id: Int,
    val name: String,
    val color: String,
    val size: String,
    val description: String,
    val tags: List<String>,
    val imageResId: Int
)