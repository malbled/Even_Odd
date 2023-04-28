package com.example.even_odd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.even_odd.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFrame(FragmentOne.newInstance(), R.id.OneFrame)
        openFrame(FragmentTwo.newInstance(), R.id.TwoFrame)

        dataModel.msgForMainFromUserFrame.observe(this) {
            val rbBool = it.toString().toBoolean()
            binding.btnStart.isEnabled = rbBool
        }

        binding.btnStart.setOnClickListener {
            binding.btnRepeat.isEnabled = true
            val bool = true
            binding.btnStart.isEnabled = false
            binding.txtComp.isVisible = true
            binding.txtUser.isVisible = true
            dataModel.msgForCompFrame.value = bool.toString()
            dataModel.msgForUserFrame.value = bool.toString()
            dataModel.msgForMainFromCompFrameCount.observe(this) {
                val dop = it
                binding.txtComp.text = dop
            }

            dataModel.msgForMainFromUserFrameCount.observe(this) {
                val dop = it
                binding.txtUser.text = dop
            }
        }
        binding.btnRepeat.setOnClickListener{
            binding.btnRepeat.isEnabled = false
            dataModel.msgForMainFromUserFrameCount.value = binding.txtUser.text.toString()
            dataModel.msgForMainFromCompFrameCount.value =binding.txtComp.text.toString()
            val bool = false
            binding.txtComp.isVisible = false
            binding.txtUser.isVisible = false
            dataModel.msgForCompFrame.value = bool.toString()
            dataModel.msgForUserFrame.value = bool.toString()
            openFrame(FragmentOne.newInstance(), R.id.OneFrame)
            openFrame(FragmentTwo.newInstance(), R.id.TwoFrame)
        }
        binding.btnExit.setOnClickListener{
            moveTaskToBack(true)
            exitProcess(-1)
        }
    }
    private fun openFrame(f: Fragment, idHolder: Int){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder,f)
            .commit()
    }
}