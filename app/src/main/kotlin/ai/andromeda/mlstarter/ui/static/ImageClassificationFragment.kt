package ai.andromeda.mlstarter.ui.static

import ai.andromeda.mlstarter.ml.ImageClassifier
import ai.andromeda.mlstarter.ui.theme.MLStarterTheme
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.graphics.decodeBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

@ExperimentalAnimationApi
class ImageClassificationFragment : Fragment() {

    private val viewModel: ImageClassificationViewModel by viewModels()
    private val imageClassifier by lazy { ImageClassifier(requireContext()) }

    @Suppress("DEPRECATION")
    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        val bitmap: Bitmap? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(
                requireActivity().contentResolver, uri
            )
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver, uri
            )
        }
        viewModel.setImageUri(uri)
        viewModel.analyzeImage(imageClassifier, bitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MLStarterTheme {
                    ImageClassificationScreen({
                        selectImageLauncher.launch("image/*")
                    })
                }
            }
        }
    }
}