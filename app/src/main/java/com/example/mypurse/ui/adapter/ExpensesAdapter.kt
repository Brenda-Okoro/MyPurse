package com.example.mypurse.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypurse.R
import com.example.mypurse.data.Transaction

class ExpensesAdapter(
    val context: Context,
    private val clickDeleteInterface: ClickDeleteInterface,
) :
    RecyclerView.Adapter<ExpensesAdapter.ViewHolder>() {
    private val allTransactions = ArrayList<Transaction>()

    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dateTV = itemView.findViewById<TextView>(R.id.txt_date)
        val itemTV = itemView.findViewById<TextView>(R.id.txt_item)
        val amountTV = itemView.findViewById<TextView>(R.id.txt_amount)
        val deleteIV = itemView.findViewById<ImageView>(R.id.img_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_budget,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        holder.itemTV.text = allTransactions[position].content
        holder.dateTV.text = allTransactions[position].timeStamp
        holder.amountTV.text = allTransactions[position].amount
        // click listener for delete image view icon.
        holder.deleteIV.setOnClickListener {
            clickDeleteInterface.onDeleteIconClick(allTransactions[position])
        }
    }

    override fun getItemCount(): Int {
        return allTransactions.size
    }

    // below method is use to update Transaction List.
    fun updateList(newList: List<Transaction>) {
        //clear list
        allTransactions.clear()
        // add new list
        allTransactions.addAll(newList)
        notifyDataSetChanged()
    }
}

interface ClickDeleteInterface {
    //method for delete click action
    fun onDeleteIconClick(transaction: Transaction)
}
