package ai.andromeda.mlstarter.ui.detection

import ai.andromeda.mlstarter.R
import ai.andromeda.mlstarter.ui.common.components.GradientButton
import ai.andromeda.mlstarter.ui.theme.MaterialColors
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
    imageClassificationViewModel: ObjectDetectionViewModel = viewModel()
) {

    val imageUri = imageClassificationViewModel.imageUri.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        if (imageUri.value != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    imageModel = imageUri.value,
                    circularReveal = CircularReveal(250),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                )

//                Spacer(modifier = Modifier.height(16.dp))

//                AnimatedVisibility(
//                    visible = predictions.value != null
//                ) {
//                    predictions.value?.let { predictions ->
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            elevation = 4.dp,
//                            shape = RoundedCornerShape(16.dp),
//                            backgroundColor = MaterialColors.Purple50
//                        ) {
//                            LazyColumn(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(24.dp)
//                            ) {
//                                items(predictions) { prediction ->
//                                    Text(
//                                        text = prediction.toString(), color = Color.DarkGray,
//                                        fontSize = 16.sp
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }

//                Spacer(modifier = Modifier.height(16.dp))
//
//                AnimatedVisibility(
//                    visible = predictions.value != null
//                ) {
//                    predictions.value?.let { predictions ->
//                        if (predictions.isNotEmpty())
//                            Text(
//                                text = "Prediction: ${predictions[0].label}",
//                                fontSize = 20.sp,
//                                color = Color.DarkGray
//                            )
//                    }
//                }
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.vector_empty),
                    contentDescription = ""
                )
                Text(text = "No Image Found", fontSize = 18.sp, color = Color.LightGray)
            }
        }

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
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(28.dp)),
            onClick = { onSelectImageClick() }
        )
    }
}