package com.focusstart.loanapp.features.loan.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.focusstart.loanapp.R
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanState

class LoansDataAdapter : RecyclerView.Adapter<LoansDataAdapter.ViewHolder>() {

    private var loans: List<Loan> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loan: Loan = loans[position]
        holder.bind(loan)
    }

    fun updateList(newLoans: List<Loan>) {
        //loans.clear()
        //loans.addAll(newLoans)
        loans = newLoans
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = loans.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_loan, parent, false)) {
        private val dateView: TextView = itemView.findViewById(R.id.dateView)
        private val nameView: TextView = itemView.findViewById(R.id.nameView)
        private val statusImageView: ImageView = itemView.findViewById(R.id.statusImageView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
        private val amountView: TextView = itemView.findViewById(R.id.amountView)

        fun bind(loan: Loan) {
            dateView.text = loan.date
            nameView.text = itemView.context.getString(R.string.full_name,
                    loan.lastName, loan.firstName)
            statusImageView.setImageResource(setImageStatus(loan.state))
            statusTextView.setText(setTextStatus(loan.state))
            amountView.text = loan.amount.toString()
        }

        /*
        * Установка изображения и текста в зависимости от
        * статуса заёма
        * */
        private fun setImageStatus(state: LoanState): Int {
            return when (state) {
                LoanState.APPROVED -> R.drawable.ic_approved
                LoanState.REGISTERED -> R.drawable.ic_registered
                LoanState.REJECTED -> R.drawable.ic_rejected
            }
        }

        private fun setTextStatus(state: LoanState): Int {
            return when (state) {
                LoanState.APPROVED -> R.string.loan_approved
                LoanState.REGISTERED -> R.string.loan_registered
                LoanState.REJECTED -> R.string.loan_rejected
            }
        }
    }
}
