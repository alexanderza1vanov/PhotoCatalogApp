package com.example.photocatalogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.photocatalogapp.presentation.navigation.AppNavigation
import com.example.photocatalogapp.ui.theme.PhotoCatalogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PhotoCatalogAppTheme {
                AppNavigation()
            }
        }
    }
}