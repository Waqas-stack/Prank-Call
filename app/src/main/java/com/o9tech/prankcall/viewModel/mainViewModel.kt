package com.o9tech.prankcall.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huhx.picker.model.AssetInfo
import com.o9tech.prankcall.repositry.MainRepo
import com.o9tech.prankcall.roomdb.databaseModel.MessageEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserDetailsEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserEntity
import com.o9tech.prankcall.roomdb.databaseModel.VideoEntity
import com.o9tech.prankcall.utils.AppConstant.getVideoPathFromAssets
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepo: MainRepo,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val userDetailsList: StateFlow<List<UserDetailsEntity>> = mainRepo.getAllUserDetails()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun insertUserDetails(name: String,) {
        val userDetailsEntity = UserDetailsEntity(
            name = name,
            imageName =  _selectedImages.value.first().filepath,
            videoPath = _selectedVideos.value.first().filepath
        )
        viewModelScope.launch {
            mainRepo.insertUserDetails(userDetailsEntity)
        }
    }

    fun getUserDetailsById(userId: Int): Flow<UserDetailsEntity> = mainRepo.getUserDetailsById(userId)
    private var isVideoAdded = false
    val videoList = mainRepo.getAllVideos().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )



    fun insertAssetVideos() {
        if (!isVideoAdded) {
            val videoNames = listOf(
                "call1.mp4", "call2.mp4", "prank.mp4"
            )

            viewModelScope.launch {
                videoNames.forEach { videoName ->
                    val path = getVideoPathFromAssets(context, videoName)
                    mainRepo.insertVideo(VideoEntity(videoPath = path, title = videoName))
                }
            }
            isVideoAdded = true
        }
    }

    private val _selectedImages = MutableStateFlow<List<AssetInfo>>(emptyList())
    val selectedImages: StateFlow<List<AssetInfo>> = _selectedImages

    private val _selectedVideos = MutableStateFlow<List<AssetInfo>>(emptyList())
    val selectedVideos: StateFlow<List<AssetInfo>> = _selectedVideos

    fun updateSelectedVideos(assets: List<AssetInfo>) {
        _selectedVideos.value = assets.filter { it.isVideo() }
    }
    private val _selectedFakeMessage = MutableStateFlow<List<AssetInfo>>(emptyList())
    val selectedFakeMessage: StateFlow<List<AssetInfo>> = _selectedFakeMessage
    fun updateSelectedFakeMessage(newImages: List<AssetInfo>) {
        _selectedFakeMessage.value = newImages
    }
    fun clearSelectedFakeMessage() {
        _selectedFakeMessage.value = emptyList()
    }
    fun updateSelectedImages(newImages: List<AssetInfo>) {

        _selectedImages.value = newImages
    }

    fun clearSelectedVideos() {
        _selectedVideos.value = emptyList()
    }
    fun clearSelectedImages() {
        _selectedImages.value = emptyList()
    }

    val allVideos: StateFlow<List<VideoEntity>> = mainRepo.getAllVideos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveVideo(title: String, Videopath: String) {
        viewModelScope.launch {
            mainRepo.insertVideo(VideoEntity(title = title, videoPath = Videopath))
        }
    }

    val allUsers: StateFlow<List<UserEntity>> = mainRepo.getUsers()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun saveUser(name: String) {
        viewModelScope.launch {
            mainRepo.insertUser(UserEntity(name = name, imageUri = _selectedImages.value.first().filepath))
        }
    }

    val fakeMessage: StateFlow<List<MessageEntity>> = mainRepo.getFakeMessage()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveFakeMessage(name: String,) {
        viewModelScope.launch {
            mainRepo.InsertFakeMessage(MessageEntity(name = name, imageUri = _selectedFakeMessage.value.first().filepath))
        }
    }

}
