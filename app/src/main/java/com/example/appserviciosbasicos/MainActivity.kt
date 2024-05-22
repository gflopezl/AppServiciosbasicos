package com.example.appserviciosbasicos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appserviciosbasicos.ui.composable.AppRegistrosUI


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRegistrosUI()
        }
    }
}