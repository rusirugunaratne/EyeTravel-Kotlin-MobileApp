package com.example.eyetravel.models

data class GuideModel(
    var guideId: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var age: Int? = null,
    var imageUrl: String? = null,
    var additionalDetails: String? = null,
    var rating: Float? = null
)
