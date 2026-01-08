package com.jinnylee.contacts.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = if (isFavorite) Color.Red else Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteButtonPreview() {
    FavoriteButton(
        isFavorite = true,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteButtonUnselectedPreview() {
    FavoriteButton(
        isFavorite = false,
        onClick = {}
    )
}