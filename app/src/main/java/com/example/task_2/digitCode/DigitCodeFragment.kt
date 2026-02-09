package com.example.task_2.digitCode

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task_2.R
import com.example.task_2.base.BaseFragment
import com.example.task_2.databinding.FragmentDigitcodeBinding
import com.example.task_2.listeners.FragmentClickListener
import kotlin.math.log

class DigitCodeFragment : BaseFragment<FragmentDigitcodeBinding>(
    FragmentDigitcodeBinding::inflate
){
    private val viewModel: DigitcodeViewModel by viewModels()
    override fun setupViews() {
        binding.conformbtn.setOnClickListener{
            onClick(it.id)
        }
        binding.cancelbtn.setOnClickListener{
            Log.d("CANCEL","CANCEL CLICKED")
            onClick(it.id)
        }
    }
    override fun observeViewModel() {
        viewModel.msg.observe(viewLifecycleOwner) { message ->
            if (message.equals("missing")) {
                binding.pinerror.visibility = View.VISIBLE
            } else if (message.equals("Canceled")) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            } else if (message.equals("Submitted successfully")) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onClick(viewId: Int) {
        when(viewId){
            R.id.conformbtn->{
                binding.pinerror.visibility = View.GONE

                val n1 = binding.pin1.text.toString()
                val n2 = binding.pin2.text.toString()
                val n3 = binding.pin3.text.toString()
                val n4 = binding.pin4.text.toString()

                viewModel.conform(n1, n2, n3, n4)
            }
            R.id.cancelbtn->{
                binding.pinerror.visibility= View.GONE
                //findNavController().navigate(R.id.pin_to_welcome)
                findNavController().navigate(R.id.pin_to_api_action)
                Log.d("NEXT","NAVIGATED")

            }
        }
    }
}
