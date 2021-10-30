package ai.andromeda.mlstarter.repository.vision

import ai.andromeda.mlstarter.api.vision.ObjectDetectionApi
import javax.inject.Inject

class ObjectDetectionRepositoryImpl @Inject constructor(
    private val objectDetectionApi: ObjectDetectionApi
) {
    suspend fun analyzeImage(image: String) : String? {
        return objectDetectionApi.analyzeImage(image)
    }
}