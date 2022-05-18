package com.example.mypurse.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey
    val id: Int,
    val transType: String,
    val content: String,
    val amount: String,
    val timeStamp: String
)