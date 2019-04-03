package com.example.testapp2

import java.util.*
import kotlin.collections.ArrayList

/**
 * Klasa dla obiektów przechowujących szczegóły danego zlecenia
 *
 * POJO Data class
 */
class Order(
    val login: String,
    val date: Date,
    val number: String,
    val type: String,
    val gpsLocation: String,
    val networkElement: String,
    val msisdn: String,
    val userName: String,
    val attachmentFileName: ArrayList<String>,
    val status: String ) {
}

