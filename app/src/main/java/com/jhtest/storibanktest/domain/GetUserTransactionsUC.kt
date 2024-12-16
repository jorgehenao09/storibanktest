package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.data.mapper.BankTransactionMapper
import com.jhtest.storibanktest.domain.models.BankTransactionListModel
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserTransactionsUC
    @Inject
    constructor(
        private val userStorageRepository: UserStorageRepository,
        private val userCloudStorageRepository: UserCloudStorageRepository,
        private val bankTransactionMapper: BankTransactionMapper,
    ) {
        operator fun invoke() =
            flow<Result<BankTransactionListModel>> {
                userStorageRepository.getUserId().collect { result ->
                    result.fold(
                        onSuccess = { userId ->
                            userCloudStorageRepository.getUserTransactions(userId).collect { result ->
                                result.fold(
                                    onSuccess = { response ->
                                        with(bankTransactionMapper) {
                                            emit(Result.success(mapToBankTransactionListModel(response)))
                                        }
                                    },
                                    onFailure = { emit(Result.failure(it)) },
                                )
                            }
                        },
                        onFailure = { emit(Result.failure(it)) },
                    )
                }
            }
    }
