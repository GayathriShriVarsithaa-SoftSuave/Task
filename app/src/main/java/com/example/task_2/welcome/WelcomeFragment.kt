package com.example.task_2.welcome

//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_2.R
import com.example.task_2.SessionManager
import com.example.task_2.base.BaseFragment
//import com.example.task_2.welcome.WelcomeViewModel
import com.example.task_2.databinding.FragmentWelcomeBinding

//import com.example.task_2.listeners.FragmentClickListener

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {
    override fun onStart() {
        super.onStart()
        val sessionManager = SessionManager(requireContext())
        if (sessionManager.isLoggedIn()) {
            findNavController().navigate(R.id.welcome_to_Ecommerce)
        }
    }

    private val viewModel: WelcomeViewModel by viewModels()

    override fun setupViews() {
        binding.btnSignin.setOnClickListener { onClick(it.id) }
        binding.CreateAccount.setOnClickListener { onClick(it.id) }
    }

    override fun observeViewModel() {

        viewModel.navi.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                if (binding.btnSignin.isPressed) {
                    findNavController().navigate(R.id.welcome_to_signin)
                } else if (binding.CreateAccount.isPressed) {
                    findNavController().navigate(R.id.welcome_to_signup)
                }
                viewModel.navigationdone()
            }
        }
    }

    override fun onClick(viewId: Int) {
        when (viewId) {
            R.id.btnSignin -> viewModel.onSignInClicked()
            R.id.CreateAccount -> viewModel.onCreateAccountClicked()
        }
    }
}
