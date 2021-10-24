package ai.andromeda.mlstarter.ml.data

data class Prediction(
    val label: String,
    val confidence: Float
) {
    private val probabilityString = String.format("%.1f%%", confidence * 100.0f)

    override fun toString(): String {
        return "$label \t $probabilityString"
    }
}
