package ai.andromeda.mlstarter.ml

import ai.andromeda.mlstarter.ml.ImageClassifier.Companion.INPUT_SIZE
import ai.andromeda.mlstarter.ml.data.Prediction
import ai.andromeda.mlstarter.utils.YuvToRgbConverter
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import java.util.concurrent.Executors

@androidx.camera.core.ExperimentalGetImage
class ImageAnalyzer(
    context: Context,
    private val predictionCallback: (List<Prediction>) -> Unit
) : ImageAnalysis.Analyzer {

    private val imageClassifier = ImageClassifier(context)
    private val yuvToRgbConverter = YuvToRgbConverter(context)
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var rotationMatrix: Matrix

    companion object {

        inline fun getInstance(
            context: Context,
            crossinline predictionCallback: (List<Prediction>) -> Unit
        ): ImageAnalysis {
            val cameraExecutor = Executors.newSingleThreadExecutor()
            return ImageAnalysis.Builder()
                .setTargetResolution(Size(INPUT_SIZE, INPUT_SIZE))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also { analysisUseCase: ImageAnalysis ->
                    analysisUseCase.setAnalyzer(
                        cameraExecutor,
                        ImageAnalyzer(context) { items -> predictionCallback(items) })
                }
        }
    }

    override fun analyze(imageProxy: ImageProxy) {
        val bitmap = toBitmap(imageProxy)

        bitmap?.let {
            val items = imageClassifier.classify(it)
            predictionCallback(items.toList())
        }

        imageProxy.close()
    }

    // ... ImageProxy to RGB Bitmap converter
    private fun toBitmap(imageProxy: ImageProxy): Bitmap? {

        val image = imageProxy.image ?: return null

        if (!::bitmapBuffer.isInitialized) {
            rotationMatrix = Matrix()
            rotationMatrix.postRotate(imageProxy.imageInfo.rotationDegrees.toFloat())
            bitmapBuffer = Bitmap.createBitmap(
                imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888
            )
        }

        yuvToRgbConverter.yuvToRgb(image, bitmapBuffer)

        return Bitmap.createBitmap(
            bitmapBuffer,
            0,
            0,
            bitmapBuffer.width,
            bitmapBuffer.height,
            rotationMatrix,
            false
        )
    }
}