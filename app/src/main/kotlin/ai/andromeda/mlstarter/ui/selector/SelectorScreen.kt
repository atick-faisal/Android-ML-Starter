package ai.andromeda.mlstarter.ui.selector

import ai.andromeda.mlstarter.R
import ai.andromeda.mlstarter.ui.selector.components.SelectionCard
import ai.andromeda.mlstarter.ui.theme.MaterialColors
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@Composable
fun SelectorScreen(
    onLivePredictionClick: () -> Unit,
    onImageClassificationClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Vision Demo",
            fontSize = 32.sp,
            fontWeight = FontWeight.Light,
            color = Color.DarkGray
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        SelectionCard(
            icon = R.drawable.live,
            title = "Live Prediction",
            color = Brush.horizontalGradient(
                listOf(MaterialColors.Orange500, MaterialColors.Orange900)
            ),
            onClick = { onLivePredictionClick() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SelectionCard(
            icon = R.drawable.gallery,
            title = "Image Classification",
            color = Brush.horizontalGradient(
                listOf(MaterialColors.Purple500, MaterialColors.Purple900)
            ),
            onClick = { onImageClassificationClick() }
        )
    }
}