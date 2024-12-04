package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.repository.SignUpRepository
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUC @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val userStorageRepository: UserStorageRepository,
    private val userCloudStorageRepository: UserCloudStorageRepository
) {
    operator fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String,
        faceId: String
    ) =
        flow<Result<Unit>> {
            signUpRepository.signUpUser(email, password).collect { result ->
                result.fold(
                    onSuccess = { userResponse ->
                        userStorageRepository.saveUserData(userResponse.uid, name, lastName, email)
                        userCloudStorageRepository.saveUserData(
                            userResponse.uid,
                            name,
                            lastName,
                            email,
                            faceId
                        )
                        emit(Result.success(Unit))
                    },
                    onFailure = { exception ->
                        emit(Result.failure(exception))
                    }
                )
            }
        }
}