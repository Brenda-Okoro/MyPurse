package com.example.mypurse.repository

import androidx.lifecycle.LiveData
import com.example.mypurse.data.Transaction
import com.example.mypurse.data.TransactionDao
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val localDataSource: TransactionDao
) {

    fun fetchTransactions(): LiveData<List<Transaction>> =
        localDataSource.getAllTransactions()

    suspend fun insertTransaction(transaction: Transaction) =
        localDataSource.insert(transaction)

    suspend fun deleteTransactions(transaction: Transaction) =
        localDataSource.delete(transaction)

}