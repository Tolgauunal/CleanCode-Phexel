package com.unallapps.tko.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.unallapps.tko.R
import com.unallapps.tko.data.state.LoginState
import com.unallapps.tko.data.state.RegisterState
import com.unallapps.tko.databinding.FragmentRegisterBinding
import com.unallapps.tko.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    val viewModel: RegisterViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        listeners()
        observedRegister()
    }

    private fun observedRegister() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect {
                    when (it) {
                        RegisterState.Idle -> {}
                        is RegisterState.Result -> {
                            requireContext().showToast("Kayıt Oluşturuldu")
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        }

                        is RegisterState.Error -> {
                            requireContext().showToast(it.error)
                        }
                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.registerlogin.setOnClickListener {
            viewModel.register(
                binding.registerEdtEmail.text.toString(),
                binding.registerEdtPass.text.toString()
            )
        }
    }

}