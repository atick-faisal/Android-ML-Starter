package ai.andromeda.mlstarter.repository.vision

import ai.andromeda.mlstarter.api.vision.ObjectDetectionApi
import javax.inject.Inject

class ObjectDetectionRepositoryImpl @Inject constructor(
    private val objectDetectionApi: ObjectDetectionApi
) : ObjectDetectionRepository {

    override suspend fun checkServer(): String? {
        return objectDetectionApi.checkServer()
    }

    override suspend fun analyzeImage(image: String): String? {
        return objectDetectionApi.analyzeImage(image)
    }

}