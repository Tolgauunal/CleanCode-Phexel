package com.unallapps.tko.ui.login

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.Slider
import com.google.android.material.slider.Slider.OnChangeListener
import com.unallapps.tko.R
import com.unallapps.tko.data.state.LoginState
import com.unallapps.tko.databinding.FragmentLoginBinding
import com.unallapps.tko.showToast
import com.unallapps.tko.ui.login.LoginFragment.Constants.REMEMBER_ME_KEY
import com.unallapps.tko.ui.login.LoginFragment.Constants.SHARED_PREF_NAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    object Constants {
        const val SHARED_PREF_NAME = "SHARED_PREF_NAME"
        const val REMEMBER_ME_KEY = "remember_me_key"
    }

    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentLoginBinding
    var rememberMe: Boolean = false
    val viewModel: LoginViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        listeners()
        rememberMeControl()
        observedLogin()
    }

    private fun rememberMeControl() {
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        rememberMe = sharedPreferences.getBoolean(REMEMBER_ME_KEY, false)
        if (rememberMe == true) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }else{
            binding.rememberMe.setOnCheckedChangeListener(object : OnChangeListener,
                CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    rememberMe = isChecked
                }

                override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    private fun observedLogin() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect {
                    when (it) {
                        LoginState.Idle -> {}
                        is LoginState.Result -> {
                            sharedPreferences.edit().putBoolean(REMEMBER_ME_KEY, rememberMe).apply()
                            requireContext().showToast("Hoş Geldiniz ${it.user.email}")
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }

                        is LoginState.Error -> {
                            requireContext().showToast(it.error)
                        }
                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.login.setOnClickListener {
            viewModel.loginControl(
                binding.edtEmail.text.toString(),
                binding.edtPass.text.toString(),
                rememberMe
            )
        }
    }

}