package com.jhtest.storibanktest.ui.home.homeScreen.states

import com.jhtest.storibanktest.domain.models.BankTransactionListModel
import com.jhtest.storibanktest.utils.Empty

data class TransactionsUiState(
    val isLoading: Boolean = false,
    val isSuccess: BankTransactionListModel? = null,
    val messageError: String = String.Empty,
)
