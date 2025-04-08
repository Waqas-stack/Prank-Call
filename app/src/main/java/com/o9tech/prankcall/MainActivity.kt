package com.o9tech.prankcall
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.o9tech.prankcall.AppNavigation.Navigation
import com.o9tech.prankcall.ui.theme.PrankCallTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrankCallTheme {
                Navigation()
            }
        }
    }
}
