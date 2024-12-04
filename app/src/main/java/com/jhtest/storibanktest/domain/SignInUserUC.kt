package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.repository.SignInRepository
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUserUC @Inject constructor(
    private val signInRepository: SignInRepository,
    private val userStorageRepository: UserStorageRepository,
    private val userCloudStorageRepository: UserCloudStorageRepository
) {
    operator fun invoke(email: String, password: String) = flow<Result<Unit>> {
        signInRepository.signInUser(email, password).collect { result ->
            result.fold(
                onSuccess = { userResponse ->
                    userCloudStorageRepository.getUserData(userResponse.uid)
                        .collect { userCloudStorageResult ->
                            userCloudStorageResult.fold(
                                onSuccess = { userData ->
                                    userStorageRepository.saveUserData(userResponse.uid, userData.name, userData.lastName, userData.email)
                                    emit(Result.success(Unit))
                                }, onFailure = {
                                    emit(Result.failure(it))
                                }
                            )
                        }
                },
                onFailure = {
                    emit(Result.failure(it))
                }
            )
        }
    }
}