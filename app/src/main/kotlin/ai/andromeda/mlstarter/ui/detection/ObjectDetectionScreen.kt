package ai.andromeda.mlstarter.ui.detection

import ai.andromeda.mlstarter.R
import ai.andromeda.mlstarter.ui.common.components.GradientButton
import ai.andromeda.mlstarter.ui.theme.MaterialColors
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalAnimationApi
@Composable
fun ObjectDetectionScreen(
    onSelectImageClick: () -> Unit,
    objectDetectionViewModel: ObjectDetectionViewModel = viewModel()
) {

    val hidePredictions = objectDetectionViewModel.hidePredictions.observeAsState()
    val imageUri = objectDetectionViewModel.imageUri.observeAsState()
    val predictedImage = objectDetectionViewModel.predictedImage.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        when {
            imageUri.value != null && hidePredictions.value == true -> {
                GlideImage(
                    imageModel = imageUri.value,
                    circularReveal = CircularReveal(250),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillWidth
                )
            }
            predictedImage.value != null && hidePredictions.value == false -> {
                predictedImage.value?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
            else -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.vector_empty),
                        contentDescription = ""
                    )
                    Text(text = "No Image Found", fontSize = 18.sp, color = Color.LightGray)
                }
            }
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            AnimatedVisibility(visible = predictedImage.value != null) {
                GradientButton(text = "Toggle Predictions",
                    gradient = Brush.verticalGradient(
                        listOf(
                            MaterialColors.Orange500,
                            MaterialColors.Orange900
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    onClick = { objectDetectionViewModel.togglePrediction() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            GradientButton(text = "Load Image from Gallery",
                gradient = Brush.verticalGradient(
                    listOf(
                        MaterialColors.Purple500,
                        MaterialColors.Purple900
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp)),
                onClick = { onSelectImageClick() }
            )
        }
    }
}