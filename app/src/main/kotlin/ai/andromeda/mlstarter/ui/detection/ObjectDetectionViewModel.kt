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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectDetectionViewModel @Inject constructor(
    private val objectDetectionRepository: ObjectDetectionRepository
) : ViewModel() {

    private val _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri>
        get() = _imageUri

    private val _predictedImage = MutableLiveData<Bitmap>()
    val predictedImage: LiveData<Bitmap>
        get() = _predictedImage

    fun setImageUri(uri: Uri) {
        _imageUri.postValue(uri)
    }

    fun alalyzeImageOnline(bitmap: Bitmap?) {
        bitmap?.let {
            val image = convertBitmapToBase64(bitmap)
            viewModelScope.launch {
                val response = objectDetectionRepository.analyzeImage(image)
                response?.let {
                    _predictedImage.postValue(
                        convertBase64ToBitmap(response)
                    )
                }
            }
        }
    }

}