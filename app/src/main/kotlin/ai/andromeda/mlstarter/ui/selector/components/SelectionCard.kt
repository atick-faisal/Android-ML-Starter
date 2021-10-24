package ai.andromeda.mlstarter.ui.selector.components

import ai.andromeda.mlstarter.ui.theme.MaterialColors
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@Composable
fun SelectionCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    color: Brush,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        onClick = { onClick() },
        backgroundColor = MaterialColors.Gray50

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .padding(16.dp)
            )

            Text(
                text = title,
                color = Color.DarkGray,
                fontSize = 20.sp
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(64.dp)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}