package ai.andromeda.mlstarter.ui.live

import ai.andromeda.mlstarter.ml.data.Prediction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LivePredictionViewModel : ViewModel() {

    private val _permissionGranted = MutableLiveData<Boolean>()
    val permissionGranted: LiveData<Boolean>
        get() = _permissionGranted

    private val _predictionList = MutableLiveData<List<Prediction>>()
    val predictionList: LiveData<List<Prediction>> = _predictionList

    fun setPermissionStatus(status: Boolean) {
        _permissionGranted.value = status
    }

    fun updatePredictions(predictions: List<Prediction>) {
        _predictionList.postValue(predictions)
    }

}