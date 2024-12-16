package com.jhtest.storibanktest.ui.home.homeScreen.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jhtest.storibanktest.domain.models.BankTransactionModel

private enum class TransactionType(val value: String) {
    INPUT("Entrada")
}

@Composable
internal fun BankTransactionItem(transaction: BankTransactionModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.type,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = transaction.date,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = transaction.value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            color =
            if (transaction.type == TransactionType.INPUT.value) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.tertiary
        )
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
}