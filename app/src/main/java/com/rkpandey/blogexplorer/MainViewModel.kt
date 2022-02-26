package com.rkpandey.blogexplorer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkpandey.blogexplorer.api.RetrofitInstance
import com.rkpandey.blogexplorer.models.Post
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"
class MainViewModel : ViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData() // private to prevent
    val posts: LiveData<List<Post>>  // public live data to access from UI main
    get() = _posts // olny get() to prevent modification

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchPosts = RetrofitInstance.api.getPosts()
            Log.i(TAG, "Fetched posts: $fetchPosts")

            _posts.value = fetchPosts
            _isLoading.value = false

        }
    }
}
