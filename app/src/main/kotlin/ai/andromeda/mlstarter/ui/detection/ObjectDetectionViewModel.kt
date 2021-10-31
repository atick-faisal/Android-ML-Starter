package ai.andromeda.mlstarter.ui.detection

import ai.andromeda.mlstarter.repository.vision.ObjectDetectionRepository
import ai.andromeda.mlstarter.utils.convertBase64ToBitmap
import ai.andromeda.mlstarter.utils.convertBitmapToBase64
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectDetectionViewModel @Inject constructor(
    private val objectDetectionRepository: ObjectDetectionRepository
) : ViewModel() {

    private val _serverStatus = MutableLiveData<Boolean>()
    val serverStatus: LiveData<Boolean>
        get() = _serverStatus

    private val _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri>
        get() = _imageUri

    private val _predictedImage = MutableLiveData<Bitmap>()
    val predictedImage: LiveData<Bitmap>
        get() = _predictedImage

    private val _hidePredictions = MutableLiveData(true)
    val hidePredictions: LiveData<Boolean>
        get() = _hidePredictions

    fun setImageUri(uri: Uri) {
        _imageUri.postValue(uri)
    }

    fun checkServer() {
        viewModelScope.launch {
            try {
                val response = objectDetectionRepository.checkServer()
                response?.let {
                    _serverStatus.postValue(true)
                }
            } catch (e: Exception) {
                Logger.e(e.toString())
                e.printStackTrace()
            }
        }
    }

    fun analyzeImageOnline(bitmap: Bitmap?) {
        bitmap?.let {
            val image = convertBitmapToBase64(bitmap)
            viewModelScope.launch {
                try {
                    val response = objectDetectionRepository.analyzeImage(image)
                    response?.let {
                        val resultBitmap = convertBase64ToBitmap(response)
                        resultBitmap?.let {
                            _predictedImage.postValue(it)
                            _hidePredictions.postValue(false)
                            // _imageUri.postValue(null)
                        }
                    }
                } catch (e: Exception) {
                    Logger.e(e.toString())
                }
            }
        }
    }

    fun togglePrediction() {
        val currentState = _hidePredictions.value
        currentState?.let {
            _hidePredictions.postValue(!currentState)
        }
    }
}