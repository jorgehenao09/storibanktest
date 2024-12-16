package com.jhtest.storibanktest.data.mapper

import com.jhtest.storibanktest.data.models.BankTransactionBody
import com.jhtest.storibanktest.domain.models.BankTransactionListModel
import com.jhtest.storibanktest.domain.models.BankTransactionModel

class BankTransactionMapper {

    internal fun mapToBankTransactionListModel(transactions: BankTransactionBody): BankTransactionListModel =
        BankTransactionListModel(
            transactions = transactions.transactions?.map { transaction ->
                BankTransactionModel(
                    type = transaction.type,
                    value = transaction.value,
                    date = transaction.date
                )
            }
        )
}