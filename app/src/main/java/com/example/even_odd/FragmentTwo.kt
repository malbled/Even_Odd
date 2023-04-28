package com.example.even_odd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.even_odd.databinding.FragmentTwoBinding

class FragmentTwo : Fragment() {

    lateinit var binding: FragmentTwoBinding
     private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater)

        var winCount = 0
        dataModel.msgForMainFromUserFrameCount.observe(activity as LifecycleOwner){
            winCount = it.toString().toInt()
        }

        val rbBool = true
        randomValues()

        dataModel.msgForUserFrame.observe(activity as LifecycleOwner){
            val check = it.toString().toBoolean()
            if (check)
            {
                binding.linerTwo.isVisible = true
                binding.imgEven.isVisible = true
                binding.imgOdd.isVisible = true
            }
            else{
                binding.linerTwo.isVisible = false
                binding.imgEven.isVisible = false
                binding.imgOdd.isVisible = false
            }
        }

        binding.rbEven.setOnClickListener{
            dataModel.msgForMainFromUserFrame.value = rbBool.toString()
            binding.rbOdd.isEnabled = false
            if (binding.txtUserResult.text.toString().toInt() % 2 == 0 && binding.rbEven.isChecked)
            {
                binding.imgEven.setImageResource(R.drawable.ico_01)
                binding.imgOdd.setImageResource(R.drawable.ico_03)
                winCount += 1
                dataModel.msgForMainFromUserFrameCount.value = winCount.toString()
            }
            else
            {
                binding.imgEven.setImageResource(R.drawable.ico_02)
                binding.imgOdd.setImageResource(R.drawable.ico_03)
                dataModel.msgForMainFromUserFrameCount.value = winCount.toString()
            }
        }

        binding.rbOdd.setOnClickListener{
            dataModel.msgForMainFromUserFrame.value = rbBool.toString()
            binding.rbEven.isEnabled = false
            if (binding.txtUserResult.text.toString().toInt() % 2 == 1 && binding.rbOdd.isChecked)
            {
                binding.imgOdd.setImageResource(R.drawable.ico_01)
                binding.imgEven.setImageResource(R.drawable.ico_03)
                winCount += 1
                dataModel.msgForMainFromUserFrameCount.value = winCount.toString()
            }
            else
            {
                binding.imgOdd.setImageResource(R.drawable.ico_02)
                binding.imgEven.setImageResource(R.drawable.ico_03)
                dataModel.msgForMainFromUserFrameCount.value = winCount.toString()
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentTwo()
    }

    private fun randomValues()
    {
        binding.txtUser1.text = (1..6).random().toString()
        binding.txtUser2.text = (1..6).random().toString()
        binding.txtUser3.text = (1..6).random().toString()
        (binding.txtUser1.text.toString().toInt() +
                binding.txtUser2.text.toString().toInt() +
                binding.txtUser3.text.toString().toInt()).toString()
            .also { binding.txtUserResult.text = it }
    }
}