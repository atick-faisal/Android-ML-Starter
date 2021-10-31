package ai.andromeda.mlstarter.repository.vision

interface ObjectDetectionRepository {
    suspend fun checkServer() : String?
    suspend fun analyzeImage(image: String) : String?
}