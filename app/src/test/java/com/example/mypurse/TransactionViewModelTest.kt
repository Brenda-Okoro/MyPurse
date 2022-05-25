package com.example.mypurse

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mypurse.data.Transaction
import com.example.mypurse.repository.TransactionRepository
import com.example.mypurse.viewmodel.TransactionViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TransactionViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: TransactionRepository

    @Mock
    private lateinit var allTransactions: Observer<List<Transaction>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Transaction>())
                .`when`(repository)
                .fetchTransactions()
            val viewModel = TransactionViewModel(application)
            viewModel.allTransactions.observeForever(allTransactions)
            verify(repository).fetchTransactions()
            verify(allTransactions).onChanged(emptyList())
            viewModel.allTransactions.removeObserver(allTransactions)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "An error occurred!"
            doThrow(RuntimeException(errorMessage))
                .`when`(repository)
                .fetchTransactions()
            val viewModel = TransactionViewModel(application)
            viewModel.allTransactions.observeForever(allTransactions)
            verify(repository).fetchTransactions()
            verify(allTransactions).onChanged(
                error(RuntimeException(errorMessage).toString())
            )

            viewModel.allTransactions.removeObserver(allTransactions)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}