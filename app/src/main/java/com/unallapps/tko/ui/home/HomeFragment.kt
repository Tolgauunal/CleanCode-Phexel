package com.unallapps.tko.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.unallapps.tko.R
import com.unallapps.tko.data.state.PhotoState
import com.unallapps.tko.databinding.FragmentHomeBinding
import com.unallapps.tko.showToast
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        binding.edtCategory.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.getAllPhoto(s.toString())
                println(s)
            }

        })
        observeGetPhoto()

    }

    private fun observeGetPhoto() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.photoListState.collect {
                    when (it) {
                        PhotoState.Idle -> {}
                        is PhotoState.Error -> {
                            requireContext().showToast(it.toString())
                        }

                        is PhotoState.Result -> {
                            binding.recyclerView.adapter = PhotoRecyclerViewAdapter(it.photo)
                        }
                    }
                }
            }
        }
    }
}