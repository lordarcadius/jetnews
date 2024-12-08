package com.vipuljha.jetnews.core.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun VButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(size = 6.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography
                .labelMedium
                .copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun VTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography
                .labelMedium
                .copy(fontWeight = FontWeight.Medium)
        )
    }
}