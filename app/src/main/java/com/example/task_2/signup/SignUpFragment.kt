package com.example.task_2.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_2.R
import com.example.task_2.base.BaseFragment
import com.example.task_2.databinding.FragmentSignInBinding
import com.example.task_2.databinding.FragmentSignUpBinding
import com.example.task_2.listeners.FragmentClickListener
import com.example.task_2.signup.SignUpViewModel


//class SignUpFragment : Fragment(R.layout.fragment_sign_up),FragmentClickListener {
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
){

    private val viewModel: SignUpViewModel by viewModels()
    override fun setupViews() {
        binding.signupbutton.setOnClickListener { onClick(it.id) }
        binding.back1.setOnClickListener {
            onClick(it.id)
        }

    }
    override fun observeViewModel() {
        viewModel.msg.observe(viewLifecycleOwner) { message ->
            if(message.equals("no name"))
            {
                binding.nameLayout.error="Name field must be filled"
            }
            else if(message.equals("no mail"))
            {
                binding.emailLayout.error="Mail field must be filled"
            }
            else if(message.equals("no password"))
            {
                binding.passwordLayout.error="Password field must be filled"
            }
            else if(message.equals("no check"))
            {
                binding.checkerror.visibility= View.VISIBLE
            }
            else if(message.equals("invalid mail"))
            {
                binding.emailLayout.error="Invalid email"
            }
            else if(message.equals("short"))
            {
                binding.passwordLayout.error="Password must be minimum of 6 characters"
            }
            else{
                Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.navi.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigate(R.id.back_to_welcome_from_signup)
                viewModel.navigationdone()
            }
        }
    }
    override fun onClick(viewId: Int) {
        when(viewId){
            R.id.back1->{
                findNavController().popBackStack()
            }
            R.id.signupbutton->{
                binding.nameLayout.error = null
                binding.emailLayout.error = null
                binding.passwordLayout.error = null
                binding.checkerror.visibility = View.GONE

                viewModel.signUp(
                    binding.emailBox.text.toString(),
                    binding.passwordBox.text.toString(),
                    binding.passwordBox1.text.toString(),
                    binding.checkbox1.isChecked
                )
            }
        }
    }
}
