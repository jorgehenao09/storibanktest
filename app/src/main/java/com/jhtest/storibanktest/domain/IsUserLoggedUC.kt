package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsUserLoggedUC
    @Inject
    constructor(
        private val userStorageRepository: UserStorageRepository,
    ) {
        operator fun invoke() =
            flow<Result<Boolean>> {
                userStorageRepository.isUserLogged().collect { result ->
                    result.fold(
                        onSuccess = { emit(Result.success(it)) },
                        onFailure = { emit(Result.failure(it)) },
                    )
                }
            }
    }
