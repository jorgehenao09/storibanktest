package com.jhtest.storibanktest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhtest.storibanktest.di.IoDispatcher
import com.jhtest.storibanktest.domain.IsUserLoggedUC
import com.jhtest.storibanktest.ui.authentication.splash.states.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        private val isUserLoggedUC: IsUserLoggedUC,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : ViewModel() {
        private val _splashState = MutableStateFlow(SplashState())
        val splashState =
            _splashState
                .onStart {
                    loginShouldBeShowed()
                }.stateIn(
                    viewModelScope,
                    SharingStarted.Eagerly,
                    SplashState(),
                )

        private fun loginShouldBeShowed() {
            isUserLoggedUC.invoke().map { result ->
                result.fold(
                    onSuccess = { _splashState.emit(SplashState(isUserLogged = it)) },
                    onFailure = { _splashState.emit(SplashState(error = it.message, isUserLogged = false)) },
                )
            }.flowOn(ioDispatcher).launchIn(viewModelScope)
        }
    }
