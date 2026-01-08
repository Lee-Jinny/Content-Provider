package com.jinnylee.customgallery.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExifInfoItem(
    label: String,
    value: String?,
    modifier: Modifier = Modifier
) {
    if (value != null) {
        Column(modifier = modifier.padding(vertical = 4.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExifInfoItemPreview() {
    MaterialTheme {
        ExifInfoItem(label = "Date", value = "2026-01-08 14:30:00")
    }
}