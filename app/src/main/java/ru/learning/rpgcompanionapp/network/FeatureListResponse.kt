package ru.learning.rpgcompanionapp.network

data class FeatureListResponse(
    val count: Int,
    val results: List<FeatureDto>
)