package com.example.mypurse.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypurse.data.Transaction
import com.example.mypurse.databinding.FragmentTransactionBinding
import com.example.mypurse.ui.adapter.ClickDeleteInterface
import com.example.mypurse.ui.adapter.ExpensesAdapter
import com.example.mypurse.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class TransactionFragment : Fragment(), ClickDeleteInterface {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!
    private val transactionViewModel: TransactionViewModel by viewModels()
    private var totalIncome: Double = 0.0
    private var totalExpenses: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this creates a vertical layout Manager
        binding.budgetList.layoutManager = LinearLayoutManager(context)

        val adapter = ExpensesAdapter(requireContext(), this)

        // Setting the Adapter with the recyclerview
        binding.budgetList.adapter = adapter

        transactionViewModel.allTransactions.observe(this, { list ->
            list?.let {
                // on below line we are updating our list.
                adapter.updateList(it)
                for (item in it) {
                    if (item.transType == "Income") {
                        val income = item.amount.toString()
                        totalIncome = income.toDouble()
                        binding.txtAmtIncome.setText(totalIncome.toString()).toString()
                    } else if (item.transType == "Expense") {
                        val expenses = item.amount.toString()
                        totalExpenses = expenses.toDouble()
                        binding.txtAmtExpenses.setText(totalExpenses.toString()).toString()
                    }
                    val bal: Double = totalIncome - totalExpenses
                    binding.txtAmtBalance.setText(bal.toString()).toString()

                    val ratio: Double = totalExpenses / totalIncome
                    binding.progressbar.setProgress((ratio * 100).toInt()).toString()
                    println(item)
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteIconClick(transaction: Transaction) {
        //method to delete transaction
        transactionViewModel.deleteTransaction(transaction)
        // displaying a toast message
        Toast.makeText(context, "${transaction.content} Deleted", Toast.LENGTH_LONG).show()
    }
}