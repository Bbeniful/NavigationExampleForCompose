package com.bbeniful.navigationexample.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {

    private var _data = MutableStateFlow("")
    val data = _data.asStateFlow()


    fun updateData(data:String){
        _data.value = data
    }
}