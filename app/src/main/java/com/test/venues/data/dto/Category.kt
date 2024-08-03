package com.test.venues.data.dto

data class Category(
    val categoryCode: Int,
    val icon: Icon,
    val id: String,
    val mapIcon: String,
    val name: String,
    val pluralName: String,
    val primary: Boolean,
    val shortName: String
)