package com.jhtest.storibanktest.domain.mapper

import com.google.firebase.auth.FirebaseUser
import com.jhtest.storibanktest.domain.models.UserDataModel

class UserDataMapper {

    internal fun mapToUserDataModel(user: FirebaseUser): UserDataModel {
        return UserDataModel(
            name = user.displayName ?: "",
            email = user.email ?: ""
        )
    }
}