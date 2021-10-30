package ai.andromeda.mlstarter.ui.classification

import ai.andromeda.mlstarter.ml.ImageClassifier
import ai.andromeda.mlstarter.ml.data.Prediction
import ai.andromeda.mlstarter.repository.vision.ObjectDetectionRepository
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
class ImageClassificationViewModel @Inject constructor(
    val objectDetectionRepository: ObjectDetectionRepository
): ViewModel() {

    private val _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri>
        get() = _imageUri

    private val _predictionList = MutableLiveData<List<Prediction>>()
    val predictionList: LiveData<List<Prediction>> = _predictionList

    fun setImageUri(uri: Uri) {
        _imageUri.postValue(uri)
    }

    fun analyzeImage(imageClassifier: ImageClassifier, bitmap: Bitmap?) {
        bitmap?.let {
            _predictionList.postValue(
                imageClassifier.classify(it)
            )
        }
    }

    fun alalyzeImageOnline(bitmap: Bitmap?) {
        bitmap?.let {
            val image = convertBitmapToBase64(bitmap)
            viewModelScope.launch {
                objectDetectionRepository.analyzeImage(image)
            }
        }
    }

}