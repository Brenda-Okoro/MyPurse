package com.example.mypurse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this creates a vertical layout Manager
        binding.budgetList.layoutManager = LinearLayoutManager(context)

        val adapter = ExpensesAdapter(requireContext(), this)

        // Setting the Adapter with the recyclerview
        binding.budgetList.adapter = adapter

        transactionViewModel.allTransactions.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                adapter.updateList(it)
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