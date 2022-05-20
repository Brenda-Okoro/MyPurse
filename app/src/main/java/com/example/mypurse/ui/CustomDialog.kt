package com.example.mypurse.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.mypurse.R
import com.example.mypurse.data.Transaction
import com.example.mypurse.databinding.LayoutAddTransactionBinding
import com.example.mypurse.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CustomDialog : DialogFragment() {
    private var _binding: LayoutAddTransactionBinding? = null
    private val transactionViewModel: TransactionViewModel by viewModels()
    var transactionID = 0

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onStart() {
        super.onStart()
        val transType = resources.getStringArray(R.array.Transaction_type)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, transType
        )
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Toast.makeText(
                    context,
                    getString(R.string.add_transaction) + " " +
                            "" + transType[position], Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.btnAdd.setOnClickListener {
            // getting all content
            val contentDesc = binding.txtTransDesc.text.toString()
            val amount = binding.txtAmount.text.toString()
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDateAndTime: String = sdf.format(Date())
            transactionViewModel.addTransaction(
                Transaction(transactionID,
                    transType.toString(),
                    contentDesc,
                    amount,
                    currentDateAndTime
                )
            )
            binding.txtTransDesc.text.clear()
            binding.txtAmount.text.clear()
        }

    }
}