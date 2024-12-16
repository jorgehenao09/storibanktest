package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserInfoUC
    @Inject
    constructor(
        private val userStorageRepository: UserStorageRepository,
        private val userCloudStorageRepository: UserCloudStorageRepository,
    ) {
        operator fun invoke() =
            flow<Result<String>> {
                userStorageRepository.getUserId().collect { result ->
                    result.fold(
                        onSuccess = {
                            userCloudStorageRepository.getUserData(it).collect { result ->
                                result.fold(
                                    onSuccess = { response ->
                                        emit(Result.success(response.name))
                                    },
                                    onFailure = { exception ->
                                        emit(Result.failure(exception))
                                    },
                                )
                            }
                        },
                        onFailure = { emit(Result.failure(it)) },
                    )
                }
            }
    }
