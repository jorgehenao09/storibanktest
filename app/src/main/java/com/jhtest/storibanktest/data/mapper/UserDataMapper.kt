package com.jhtest.storibanktest.data.mapper

import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.domain.models.UserDataModel

class UserDataMapper {

    internal fun mapToUserDataModel(user: UserData): UserDataModel {
        return UserDataModel(
            name = user.name,
            email = user.email
        )
    }
}