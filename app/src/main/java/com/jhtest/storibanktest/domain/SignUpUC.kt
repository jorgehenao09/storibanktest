package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.repository.SignUpRepository
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import com.jhtest.storibanktest.ui.viewmodels.SignUpUserInfo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUC
    @Inject
    constructor(
        private val signUpRepository: SignUpRepository,
        private val userStorageRepository: UserStorageRepository,
        private val userCloudStorageRepository: UserCloudStorageRepository,
    ) {
        operator fun invoke(signUpUserInfo: SignUpUserInfo) =
            flow<Result<Unit>> {
                signUpRepository.signUpUser(signUpUserInfo.email, signUpUserInfo.password)
                    .collect { result ->
                        result.fold(
                            onSuccess = { userResponse ->
                                userStorageRepository.saveUserData(
                                    userResponse.uid,
                                    signUpUserInfo.name,
                                    signUpUserInfo.lastName,
                                    signUpUserInfo.email,
                                )
                                userCloudStorageRepository.saveUserData(
                                    signUpUserInfo.copy(userId = userResponse.uid),
                                )
                                emit(Result.success(Unit))
                            },
                            onFailure = { exception ->
                                emit(Result.failure(exception))
                            },
                        )
                    }
            }
    }
