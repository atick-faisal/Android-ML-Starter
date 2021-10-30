package ai.andromeda.mlstarter.api.vision

import javax.inject.Inject

class ObjectDetectionApiImpl @Inject constructor(
    private val objectDetectionApi: ObjectDetectionApi
) {
    suspend fun analyzeImage(image: String) : String {
        return objectDetectionApi.analyzeImage(image)
    }
}