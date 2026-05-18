package com.example.photocatalogapp.presentation.detail

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photocatalogapp.domain.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun PhotoDetailScreen(
    photo: Photo,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val saveLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("image/jpeg")
    ) { uri: Uri? ->
        if (uri != null) {
            downloadPhotoToUri(
                context = context,
                imageUrl = photo.downloadUrl,
                uri = uri
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onBackClick) {
            Text(text = "Назад")
        }

        Spacer(modifier = Modifier.height(12.dp))

        AsyncImage(
            model = photo.downloadUrl,
            contentDescription = photo.author,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Автор: ${photo.author}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Размер: ${photo.width} × ${photo.height}")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Ссылка: ${photo.url}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                saveLauncher.launch("photo_${photo.id}.jpg")
            }
        ) {
            Text(text = "Скачать фото")
        }
    }
}

private fun downloadPhotoToUri(
    context: Context,
    imageUrl: String,
    uri: Uri
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val bytes = URL(imageUrl).readBytes()

            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                outputStream.write(bytes)
            }

            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Фото сохранено",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Ошибка сохранения: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}