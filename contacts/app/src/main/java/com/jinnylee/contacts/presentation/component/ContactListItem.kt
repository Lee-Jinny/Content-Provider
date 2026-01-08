package com.jinnylee.contacts.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinnylee.contacts.domain.model.Contact

@Composable
fun ContactListItem(
    contact: Contact,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 아바타 (초성 표시)
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contact.initial.take(1),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // 이름 및 전화번호
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = contact.phoneNumber,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // 즐겨찾기 버튼
        FavoriteButton(
            isFavorite = isFavorite,
            onClick = onFavoriteClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListItemPreview() {
    ContactListItem(
        contact = Contact(
            id = 1,
            name = "테스트",
            phoneNumber = "010-1234-5678",
            initial = "ㅌ"
        ),
        isFavorite = true,
        onFavoriteClick = {}
    )
}
