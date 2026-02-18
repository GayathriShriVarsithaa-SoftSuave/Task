package com.example.task_2.sign_in

//import android.os.Bundle
//import android.view.View
import android.widget.Toast
//import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_2.R
import com.example.task_2.SessionManager
import com.example.task_2.databinding.FragmentSignInBinding
//import com.example.task_2.listeners.FragmentClickListener
//import com.example.task_2.sign_in.SignInViewModel
import com.example.task_2.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {
    //    private var _binding: FragmentSignInBinding? = null
//    private val binding get() = _binding!!
    private val viewModel: SignInViewModel by viewModels()
    override fun setupViews() {
        binding.idsigninbutton.setOnClickListener { onClick(it.id) }
        binding.idforgotpassword.setOnClickListener { onClick(it.id) }
        binding.idback.setOnClickListener { onClick(it.id) }
    }

    override fun observeViewModel() {
        viewModel.msg.observe(viewLifecycleOwner) { message ->
            if (message.equals("no email")) {
                binding.emailLayout.error = "Mail field must be filled"
            } else if (message.equals("no password")) {
                binding.passwordLayout.error = "Password field must be filled"
            } else if (message.equals("invalid")) {
                binding.emailLayout.error = "Invalid mail"
            } else {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.navi.observe(viewLifecycleOwner) { shouldnavigate ->
            if (shouldnavigate) {
                findNavController().navigate(R.id.back_to_welcome_from_signin)
                viewModel.onForgotNavigationDone()
            }
        }
        viewModel.forgotStatus.observe(viewLifecycleOwner) { shouldnavigate ->
            if (shouldnavigate) {
                findNavController().navigate(R.id.signin_to_forgotpassword)
                viewModel.onForgotNavigationDone()
            }
        }
        viewModel.signedIn.observe(viewLifecycleOwner) { shouldnavigate ->
            if (shouldnavigate) {
                val sessionManager = SessionManager(requireContext())
                sessionManager.login()
                findNavController().navigate(R.id.signinToEcommerce)
                viewModel.onForgotNavigationDone()
            }
        }
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            R.id.idback -> {
                findNavController().popBackStack()
            }

            R.id.idsigninbutton -> {
                binding.emailLayout.error = null
                binding.passwordLayout.error = null
                val mail = binding.idemailBox.text.toString()
                val password = binding.idpasswordBox.text.toString()
                viewModel.signin(mail, password)
            }

            R.id.idforgotpassword -> {
                viewModel.forgot()
            }
        }
    }
}
