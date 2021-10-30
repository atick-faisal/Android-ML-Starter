package ai.andromeda.mlstarter.ui.static

import ai.andromeda.mlstarter.ml.ImageClassifier
import ai.andromeda.mlstarter.ml.data.Prediction
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageClassificationViewModel @Inject constructor(): ViewModel() {

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

}