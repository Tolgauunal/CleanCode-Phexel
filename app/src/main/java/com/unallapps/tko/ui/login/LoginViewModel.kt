package com.unallapps.tko.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unallapps.tko.data.repository.UserRepository
import com.unallapps.tko.data.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {


    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun loginControl(email: String, pass: String, rememberMe: Boolean) {
        viewModelScope.launch {
            if (email.isNullOrEmpty() && pass.isNullOrEmpty()) {
                _loginState.value = LoginState.Error("Boş Bırakmayın")
            } else {
                kotlin.runCatching {
                    userRepository.getAllUser(email, pass)?.let {
                        _loginState.value = LoginState.Result(it, rememberMe)
                    } ?: kotlin.run {
                        _loginState.value = LoginState.Error("Kullanıcı Bulunamadı")
                    }
                }.onFailure {
                    _loginState.value = LoginState.Error(it.message.toString())
                }
            }
        }
    }
}