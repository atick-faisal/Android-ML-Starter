package ai.andromeda.mlstarter.ui.live

import ai.andromeda.mlstarter.ml.ImageAnalyzer
import ai.andromeda.mlstarter.ui.theme.MLStarterTheme
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

@androidx.camera.core.ExperimentalGetImage
class LivePredictionFragment : Fragment() {

    private val viewModel: LivePredictionViewModel by viewModels()
    private lateinit var imageAnalyzer: ImageAnalysis

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 999
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (allPermissionsGranted()) {
            viewModel.setPermissionStatus(true)
        } else {
            requestForPermissions()
        }

        imageAnalyzer = ImageAnalyzer.getInstance(requireContext()) { predictions ->
            viewModel.updatePredictions(predictions)
        }
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
                    LivePredictionScreen(imageAnalyzer)
                }
            }
        }
    }

    private fun allPermissionsGranted(): Boolean = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestForPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                viewModel.setPermissionStatus(true)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permission Denied",
                    Toast.LENGTH_SHORT
                ).show()
                activity?.finish()
            }
        }
    }
}