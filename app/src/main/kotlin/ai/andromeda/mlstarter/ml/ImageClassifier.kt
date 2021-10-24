package ai.andromeda.mlstarter.ml

import ai.andromeda.mlstarter.ml.data.Prediction
import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp
import org.tensorflow.lite.support.model.Model


class ImageClassifier(context: Context) {

    companion object {
        const val MAX_RESULT_DISPLAY = 3
        const val NUM_CPU_THREADS = 4
        const val INPUT_SIZE = 224
    }

    private val model: VisionModel by lazy {
        val compatList = CompatibilityList()
        val options = if (compatList.isDelegateSupportedOnThisDevice) {
            Model.Options.Builder().setDevice(Model.Device.GPU).build()
        } else {
            Model.Options.Builder().setNumThreads(NUM_CPU_THREADS).build()
        }
        VisionModel.newInstance(context, options)
    }

    fun classify(bitmap: Bitmap): List<Prediction> {
        val items = mutableListOf<Prediction>()
        val supportedBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val tfImage = TensorImage.fromBitmap(supportedBitmap)

        val width: Int = bitmap.width
        val height: Int = bitmap.height

        val size = if (height > width) width else height

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeWithCropOrPadOp(size, size))
            .add(ResizeOp(INPUT_SIZE, INPUT_SIZE, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        val processedImage = imageProcessor.process(tfImage)

        val outputs = model.process(processedImage)
            .probabilityAsCategoryList
            .apply { sortByDescending { it.score } }
            .take(MAX_RESULT_DISPLAY)

        for (output in outputs) {
            items.add(
                Prediction(
                    output.label.uppercase(),
                    output.score
                )
            )
        }

        return items.toList()
    }
}