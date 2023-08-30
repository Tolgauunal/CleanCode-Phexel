package com.unallapps.tko.data.state

import com.unallapps.tko.data.entity.User

sealed class RegisterState {
    object Idle : RegisterState()
    class Result(val user: User) : RegisterState()
    class Error(val error: String) : RegisterState()
}
