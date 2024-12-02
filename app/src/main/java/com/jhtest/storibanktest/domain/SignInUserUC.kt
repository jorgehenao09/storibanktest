package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.mapper.UserDataMapper
import com.jhtest.storibanktest.domain.models.UserDataModel
import com.jhtest.storibanktest.domain.repository.SignInRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUserUC @Inject constructor(
    private val signInRepository: SignInRepository,
    private val userStorageRepository: UserStorageRepository,
    private val userDataMapper: UserDataMapper
) {
    operator fun invoke(email: String, password: String) = flow<Result<UserDataModel>> {
        signInRepository.loginUser(email, password).collect { result ->
            result.fold(
                onSuccess = { userResponse ->
                    with(userDataMapper) {
                        val userData = mapToUserDataModel(userResponse)
                        userStorageRepository.saveUserData(userData.email, userData.name)
                        emit(Result.success(userData))
                    }
                },
                onFailure = {
                    emit(Result.failure(it))
                }
            )
        }
    }
}