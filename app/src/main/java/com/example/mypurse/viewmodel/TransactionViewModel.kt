package com.example.mypurse.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypurse.data.AppDatabase
import com.example.mypurse.data.Transaction
import com.example.mypurse.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val application: Application,
) : ViewModel() {

    val allTransactions: LiveData<List<Transaction>>
    val repository: TransactionRepository

    // initialize dao
    init {
        val dao = AppDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(dao)
        allTransactions = repository.fetchTransactions()
    }

    fun addTransaction(transaction: Transaction) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertTransaction(transaction) }

    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTransactions(transaction)
    }
}