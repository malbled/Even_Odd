package com.example.even_odd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.even_odd.databinding.FragmentOneBinding

class FragmentOne : Fragment() {
    lateinit var binding: FragmentOneBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater)

        var winCount = 0
        dataModel.msgForMainFromCompFrameCount.observe(activity as LifecycleOwner){
            winCount = it.toString().toInt()
        }

        randomValues()

        dataModel.msgForCompFrame.observe(activity as LifecycleOwner){
            val check = it.toString().toBoolean()
            if (check)
            {
                binding.linerOne.isVisible = true
                binding.imgEven.isVisible = true
                binding.imgOdd.isVisible = true
            }
            else{
                binding.linerOne.isVisible = false
                binding.imgEven.isVisible = false
                binding.imgOdd.isVisible = false
            }
        }

        when ((0..1).random()){

            0 -> {binding.rbEven.isChecked = true
                if (binding.txtCompResult.text.toString().toInt() % 2 == 0 && binding.rbEven.isChecked)
                {
                    binding.imgEven.setImageResource(R.drawable.ico_01)
                    binding.imgOdd.setImageResource(R.drawable.ico_03)
                    winCount += 1
                    dataModel.msgForMainFromCompFrameCount.value = winCount.toString()
                }
                else
                {
                    binding.imgEven.setImageResource(R.drawable.ico_02)
                    binding.imgOdd.setImageResource(R.drawable.ico_03)
                    dataModel.msgForMainFromCompFrameCount.value = winCount.toString()
                }
            }

            1 -> {
                binding.rbOdd.isChecked = true
                if (binding.txtCompResult.text.toString().toInt() % 2 == 1 && binding.rbOdd.isChecked)
                {
                    binding.imgOdd.setImageResource(R.drawable.ico_01)
                    binding.imgEven.setImageResource(R.drawable.ico_03)
                    winCount += 1
                    dataModel.msgForMainFromCompFrameCount.value = winCount.toString()
                }
                else
                {
                    binding.imgOdd.setImageResource(R.drawable.ico_02)
                    binding.imgEven.setImageResource(R.drawable.ico_03)
                    dataModel.msgForMainFromCompFrameCount.value = winCount.toString()
                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentOne()
    }
    private fun randomValues()
    {
        binding.txtComp1.text = (1..6).random().toString()
        binding.txtComp2.text = (1..6).random().toString()
        binding.txtComp3.text = (1..6).random().toString()
        (binding.txtComp1.text.toString().toInt() +
                binding.txtComp2.text.toString().toInt() +
                binding.txtComp3.text.toString().toInt()).toString()
            .also { binding.txtCompResult.text = it }
    }
}