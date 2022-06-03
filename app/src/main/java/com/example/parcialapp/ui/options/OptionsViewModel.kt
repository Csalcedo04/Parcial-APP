package com.example.parcialapp.ui.options

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OptionsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Andres Ramirez     Carlos Salcedo      Magic Mirror        Version 1.0"
    }
    val text: LiveData<String> = _text
}