package com.example.nicklefox.view_model

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nicklefox.service.InjectorUtil
import com.example.nicklefox.service.YouTubeRepository

class ViewModelFactory private constructor(
    val mApplication: Application,
    val repository: YouTubeRepository
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    public companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        public var INSTANCE: ViewModelFactory? = null

        // creates single object for ViewModelFactory through singleton pattern
        @JvmStatic
        public fun getInstance(application: Application): ViewModelFactory? {

            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            ViewModelFactory(application, InjectorUtil.provideReceptionRepository())
                    }
                }
            }
            return INSTANCE
        }
    }

}