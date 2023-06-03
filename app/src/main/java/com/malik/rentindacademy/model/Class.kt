package com.malik.rentindacademy.model

data class Class(
    var id: Int,
    var image: Int,
    var name: String,
    var mentor: String,
    var duration: Float,
    var registrationTime: String,
    var startTime: String,
    var sessions: Int,
    var description: String,
    var registrationLink: String,
    var price: String,
)