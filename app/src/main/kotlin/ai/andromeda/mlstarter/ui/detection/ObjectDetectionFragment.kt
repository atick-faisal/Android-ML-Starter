package ai.andromeda.mlstarter.ui.detection

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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class ObjectDetectionFragment : Fragment() {

    private val viewModel: ObjectDetectionViewModel by viewModels()

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
        viewModel.analyzeImageOnline(bitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MLStarterTheme {
                    ObjectDetectionScreen({
                        selectImageLauncher.launch("image/*")
                    })
                }
            }
        }
        viewModel.serverStatus.observe(viewLifecycleOwner, {
            Logger.i("Server online ...")
        })
        viewModel.checkServer()

        return composeView
    }
}