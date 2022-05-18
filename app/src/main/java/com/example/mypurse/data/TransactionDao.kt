package com.example.mypurse.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getTransaction(id: Int): Transaction

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transaction: List<Transaction>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Delete
    fun deleteAll(transaction: List<Transaction>)

}