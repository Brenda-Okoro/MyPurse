package com.example.mypurse

import androidx.lifecycle.LiveData
import com.example.mypurse.data.Transaction
import com.example.mypurse.data.TransactionDao
import com.example.mypurse.repository.TransactionRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionRepositoryTest {

    lateinit var transactionRepository: TransactionRepository

    @Mock
    lateinit var localDataSource: TransactionDao

    @Mock
    private lateinit var allTransactions: LiveData<List<Transaction>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        transactionRepository = TransactionRepository(localDataSource)
    }

    @Test
    fun getAllTransactionTest() {

        runBlocking {
            Mockito.`when`(localDataSource.getAllTransactions()).thenReturn(allTransactions)
            val response = transactionRepository.fetchTransactions()
            assertEquals(listOf<Transaction>(), response.value)
        }

    }


}