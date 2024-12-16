package com.jhtest.storibanktest.ui.home.homeScreen.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.domain.models.BankTransactionListModel

@Composable
fun HomeTransactions(
    modifier: Modifier = Modifier,
    userTransactions: BankTransactionListModel
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(R.string.home_view_transactions_title),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(userTransactions.transactions!!) { transaction ->
                BankTransactionItem(transaction = transaction)
            }
        }
    }
}