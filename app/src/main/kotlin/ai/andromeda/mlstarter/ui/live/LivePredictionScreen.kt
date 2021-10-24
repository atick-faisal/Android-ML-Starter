package ai.andromeda.mlstarter.ui.live

import ai.andromeda.mlstarter.ui.live.components.CameraPreview
import ai.andromeda.mlstarter.ui.live.components.PredictionChip
import androidx.camera.core.ImageAnalysis
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LivePredictionScreen(
    imageAnalyzer: ImageAnalysis,
    livePredictionViewModel: LivePredictionViewModel = viewModel()
) {
    val permissionGranted = livePredictionViewModel.permissionGranted.observeAsState()
    val predictions = livePredictionViewModel.predictionList.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (permissionGranted.value == true) {
            CameraPreview(imageAnalyzer)

            predictions.value?.let { predictions ->

                if (predictions.isNotEmpty()) {
                    PredictionChip(
                        title = predictions[0].label,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black)
                            )
                        )
                        .align(Alignment.BottomCenter)
                        .padding(24.dp)
                ) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(predictions) { prediction ->
                            Text(
                                text = prediction.toString(), color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}