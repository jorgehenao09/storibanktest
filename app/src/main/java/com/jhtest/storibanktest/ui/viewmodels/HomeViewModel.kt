package com.jhtest.storibanktest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhtest.storibanktest.di.IoDispatcher
import com.jhtest.storibanktest.domain.GetUserInfoUC
import com.jhtest.storibanktest.domain.GetUserTransactionsUC
import com.jhtest.storibanktest.ui.home.homeScreen.states.TransactionsUiState
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
class HomeViewModel
    @Inject
    constructor(
        private val getUserInfoUC: GetUserInfoUC,
        private val getUserTransactionsUC: GetUserTransactionsUC,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : ViewModel() {
        private val _userInfoState: MutableStateFlow<String> =
            MutableStateFlow("")
        val userInfoState =
            _userInfoState
                .onStart {
                    getUserInfo()
                }.stateIn(
                    viewModelScope,
                    SharingStarted.Eagerly,
                    "",
                )

        private val _userTransactionsState: MutableStateFlow<TransactionsUiState> =
            MutableStateFlow(TransactionsUiState())
        val userTransactionsState =
            _userTransactionsState
                .onStart {
                    getUserTransactions()
                }.stateIn(
                    viewModelScope,
                    SharingStarted.Eagerly,
                    TransactionsUiState(),
                )

        private fun getUserInfo() {
            getUserInfoUC.invoke().map { result ->
                result.fold(
                    onSuccess = {
                        _userInfoState.value = it
                    },
                    onFailure = {
                        _userInfoState.value = ""
                    },
                )
            }.flowOn(ioDispatcher).launchIn(viewModelScope)
        }

        private fun getUserTransactions() {
            getUserTransactionsUC.invoke().map { result ->
                result.fold(
                    onSuccess = { response ->
                        _userTransactionsState.emit(
                            TransactionsUiState(
                                isLoading = false,
                                isSuccess = response,
                            ),
                        )
                    },
                    onFailure = {
                        _userTransactionsState.emit(
                            TransactionsUiState(
                                isLoading = false,
                                messageError = it.message ?: "error",
                            ),
                        )
                    },
                )
            }.onStart {
                _userTransactionsState.emit(
                    TransactionsUiState(
                        isLoading = true,
                    ),
                )
            }.flowOn(ioDispatcher).launchIn(viewModelScope)
        }
    }
