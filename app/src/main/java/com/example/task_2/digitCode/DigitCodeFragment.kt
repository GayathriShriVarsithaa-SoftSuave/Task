package com.example.task_2.digitCode

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.KeyEvent
import android.widget.EditText
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
        //setupPinInputs()
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
        viewModel.navi.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                //findNavController() .navigate(R.id.conform_to_recyclerfragment)
                //findNavController().navigate(R.id.conform_to_linearlist)
                findNavController().navigate(R.id.pin_to_ecomm)
                viewModel.navigationdone()
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
//    private fun setupPinInputs() {
//        val pin1 = binding.pin1
//        val pin2 = binding.pin2
//        val pin3 = binding.pin3
//        val pin4 = binding.pin4
//
//        moveNext(pin1, pin2)
//        moveNext(pin2, pin3)
//        moveNext(pin3, pin4)
//
//        moveBack(pin2, pin1)
//        moveBack(pin3, pin2)
//        moveBack(pin4, pin3)
//    }
//
//    private fun moveNext(current: EditText, next: EditText) {
//        current.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                if (s?.length == 1) {
//                    next.requestFocus()
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        })
//    }
//
//    private fun moveBack(current: EditText, previous: EditText) {
//        current.setOnKeyListener { _, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_DEL &&
//                event.action == KeyEvent.ACTION_DOWN &&
//                current.text.isEmpty()
//            ) {
//                previous.requestFocus()
//                previous.setSelection(previous.text.length)
//                true
//            } else {
//                false
//            }
//        }
//    }
}
