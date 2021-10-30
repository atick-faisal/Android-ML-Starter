package ai.andromeda.mlstarter.repository.vision

interface ObjectDetectionRepository {
    suspend fun analyzeImage(image: String) : String
}