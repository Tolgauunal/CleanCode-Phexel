package com.unallapps.tko.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unallapps.tko.data.entity.User
import com.unallapps.tko.data.repository.UserRepository
import com.unallapps.tko.data.state.LoginState
import com.unallapps.tko.data.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {


    private val _registerState: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(email: String, pass: String) {
        viewModelScope.launch {
            if (email.isNullOrEmpty() && pass.isNullOrEmpty()) {
                _registerState.value = RegisterState.Error("Boş Bırakma")
            } else {
                userRepository.getAllUserEmail(email)?.let {
                    _registerState.value = RegisterState.Error("Kullanıcı var")
                }?:kotlin.run {
                    val user = User(email = email, pass = pass)
                    userRepository.insert(user)
                    _registerState.value = RegisterState.Result(user)
                }
            }
        }
    }
}