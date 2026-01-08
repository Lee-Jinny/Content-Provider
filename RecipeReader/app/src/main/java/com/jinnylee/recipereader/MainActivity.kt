package com.jinnylee.recipereader

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val recipes = remember { mutableStateListOf<Int>() }

            LaunchedEffect(Unit) {
                val uri = Uri.parse(
                    "content://com.survivalcoding.gangnam2kiandroidstudy.provider/bookmarks"
                )

                val cursor = contentResolver.query(
                    uri,
                    null,
                    null,
                    null,
                    null
                )
                Log.d("ReaderDebug", "cursor=$cursor, count=${cursor?.count}")

                cursor?.use {
                    val recipeIdIndex = it.getColumnIndexOrThrow("recipeId")
                    while (it.moveToNext()) {
                        val recipeId = it.getInt(recipeIdIndex)
                        recipes.add(recipeId)
                        Log.d("ReaderApp", "recipeId=$recipeId")
                    }
                }
            }

            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("📖 Recipe Reader App")
                recipes.forEach {
                    Text("recipeId = $it")
                }
            }
        }
    }
}
