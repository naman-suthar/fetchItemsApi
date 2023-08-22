package com.naman.ezobooksassignmentfetchapi.model

data class APIResult(
    val items: List<Item>,
    val status: String
)