package com.vipuljha.jetnews.features.news.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.vipuljha.jetnews.R
import com.vipuljha.jetnews.core.theme.Dimens.articleCardSize
import com.vipuljha.jetnews.core.theme.Dimens.extraSmallPadding

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(articleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = extraSmallPadding)
                .height(articleCardSize),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .shimmerEffect(),
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .shimmerEffect()
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(15.dp)
                    .shimmerEffect()
            )
        }
    }
}