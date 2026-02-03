package com.example.task_2.forgot_password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_2.forgot_password.ForgotPasswordViewModel
import com.example.task_2.R
import com.example.task_2.base.BaseFragment
import com.example.task_2.databinding.FragmentForgotPasswordBinding
import com.example.task_2.listeners.FragmentClickListener

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(
    FragmentForgotPasswordBinding::inflate
){
    private val viewModel: ForgotPasswordViewModel by viewModels()
    override fun setupViews() {
        binding.continuebtn.setOnClickListener {
            onClick(it.id)
        }
        binding.back2.setOnClickListener {
            onClick(it.id)
        }
    }

    override fun observeViewModel() {
        viewModel.msg.observe(viewLifecycleOwner) {
                message ->
            if(message.equals("invalid"))
            {
                binding.emailLayout.error="Invalid email"
            }
            else if(message.equals("no mail"))
            {
                binding.emailLayout.error="Mail field must be filled"
            }
        }
        viewModel.navi.observe(viewLifecycleOwner) {
                shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(R.id.forgotpassword_to_pin)
                viewModel.next()
            }
        }
    }

    override fun onClick(viewId: Int) {
        when(viewId){
            R.id.continuebtn->{
                val mail = binding.emailBox.text.toString()
                binding.emailLayout.error = null

                viewModel.conti(mail)
            }
            R.id.back2->{
                findNavController().popBackStack()
            }
        }
    }
}