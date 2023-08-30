package com.unallapps.tko.data.state

import com.unallapps.tko.data.entity.User

sealed class LoginState {
    object Idle : LoginState()
    class Result(val user: User, val rememberMe: Boolean) : LoginState()
    class Error(val error: String) : LoginState()
}