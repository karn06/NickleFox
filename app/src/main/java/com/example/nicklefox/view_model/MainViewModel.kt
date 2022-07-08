package com.example.nicklefox.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.nicklefox.data_holder.DataItem
import com.example.nicklefox.service.DataWrapper
import com.example.nicklefox.service.YouTubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val youTubeRepository: YouTubeRepository
) : ViewModel() {

    private val listMutableLiveData = MutableLiveData<List<DataItem>>()
    val listLiveData: LiveData<List<DataItem>>
        get() = listMutableLiveData

    fun callParseDataCall(maxSize: Int) {
        viewModelScope.launch {
            val response = youTubeRepository.getList(maxSize)
            when (response.status) {
                DataWrapper.Status.SUCCESS -> {
                    listMutableLiveData.postValue(response.data?.listOfData)
                }
                DataWrapper.Status.ERROR -> {
                }
            }
        }
    }

}