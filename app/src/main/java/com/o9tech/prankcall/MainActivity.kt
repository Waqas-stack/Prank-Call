package com.o9tech.prankcall
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.o9tech.prankcall.AppNavigation.Navigation
import com.o9tech.prankcall.ui.theme.PrankCallTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrankCallTheme {
                Navigation()
            }
        }
    }
}
