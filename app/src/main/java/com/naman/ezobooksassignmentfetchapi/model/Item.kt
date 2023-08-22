package com.naman.ezobooksassignmentfetchapi.model

data class Item(
    val itemBarcode: String,
    val itemName: String,
    val itemPrice: Int,
    val url: String
)