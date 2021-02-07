package com.example.postsapplication.ui.base

import android.R
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapplication.data.repo.HandleNet
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


open class BaseViewModel() : ViewModel(), HandleNet.RemoteErrorEmitter {
    var isLoading = ObservableField(false)
    var showDialog = ObservableField(false)


    override fun onError(msg: String) {
        isLoading.set(false)

      }

    override fun onError(errorType: HandleNet.ErrorType) {
        isLoading.set(false)
        when(errorType){
              HandleNet.ErrorType.NETWORK ->show_alert()
        }
    }


    private fun show_alert(){
        viewModelScope.launch {
            showDialog.set(true)
            delay(1000)
            showDialog.set(false)
        }
    }
}