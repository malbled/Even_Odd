package com.example.even_odd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val msgForCompFrame: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val msgForUserFrame: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val msgForMainFromUserFrame: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val msgForMainFromCompFrameCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val msgForMainFromUserFrameCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}