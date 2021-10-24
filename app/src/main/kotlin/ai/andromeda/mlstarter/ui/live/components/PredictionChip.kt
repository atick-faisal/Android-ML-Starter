package ai.andromeda.mlstarter.ui.live.components

import ai.andromeda.mlstarter.ui.theme.MaterialColors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PredictionChip(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(192.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialColors.Orange500,
                        MaterialColors.Orange900
                    )
                )
            )
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}