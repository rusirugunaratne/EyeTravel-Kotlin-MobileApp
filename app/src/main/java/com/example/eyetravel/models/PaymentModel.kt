package com.example.eyetravel.models

data class PaymentModel(
    var paymentId: String? = null,
    var guideId: String? = null,
    var guideName: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var contactNumber: String? = null,
    var address: String? = null,
    var placesToVisit: String? = null,
    var amount: Double? = null,
)
