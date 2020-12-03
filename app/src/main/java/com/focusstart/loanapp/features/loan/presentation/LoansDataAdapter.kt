package com.focusstart.loanapp.features.loan.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.focusstart.loanapp.R
import com.focusstart.loanapp.features.loan.domain.entity.Loan

class LoansDataAdapter(private val clickListener: (position: Int) -> Unit)
    : RecyclerView.Adapter<LoansDataAdapter.ViewHolder>() {

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
        loans = newLoans
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = loans.size

    inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_loan, parent, false)) {
        private val dateView: TextView = itemView.findViewById(R.id.dateView)
        private val nameView: TextView = itemView.findViewById(R.id.nameView)
        private val statusImageView: ImageView = itemView.findViewById(R.id.statusImageView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
        private val amountView: TextView = itemView.findViewById(R.id.amountView)

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clickListener(adapterPosition)
                }
            }
        }

        fun bind(loan: Loan) {
            dateView.text = LoanFieldsSetter.setDate(loan.date)
            nameView.text = itemView.context.getString(R.string.full_name,
                    loan.lastName, loan.firstName)
            statusImageView.setImageResource(LoanFieldsSetter.setImageStatus(loan.state))
            statusTextView.setText(LoanFieldsSetter.setTextStatus(loan.state))
            amountView.text = itemView.context.getString(R.string.loan_amount_number, loan.amount)
        }

/*        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }*/
    }
}
