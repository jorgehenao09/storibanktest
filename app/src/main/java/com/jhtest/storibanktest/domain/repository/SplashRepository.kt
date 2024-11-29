package com.jhtest.storibanktest.domain.repository

import kotlinx.coroutines.flow.Flow

interface SplashRepository {

    fun isUserLogged(): Flow<Result<Boolean>>
}