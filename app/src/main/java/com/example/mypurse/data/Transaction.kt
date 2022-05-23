package com.example.mypurse.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val transType: String,
    val content: String,
    val amount: Double,
    val timeStamp: String
)