package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.repository.SplashRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsUserLoggedUC @Inject constructor(
    private val splashRepository: SplashRepository
) {
    operator fun invoke() = flow<Result<Boolean>> {
        splashRepository.isUserLogged().collect { result ->
            result.fold(
                onSuccess = { emit(Result.success(it)) },
                onFailure = { emit(Result.failure(it)) }
            )
        }
    }
}