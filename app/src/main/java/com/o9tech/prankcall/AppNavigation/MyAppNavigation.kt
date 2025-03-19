package com.o9tech.prankcall.AppNavigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.R
import com.o9tech.prankcall.Screen.AddNewCharacter.AddCharacterSCreen
import com.o9tech.prankcall.Screen.Callscreen.CallScreen
import com.o9tech.prankcall.Screen.ChooseTheme.ChooseThemeScreen
import com.o9tech.prankcall.Screen.FakeMessage.FakeMessageScreen
import com.o9tech.prankcall.Screen.HomeScreen
import com.o9tech.prankcall.Screen.Languages.LanguageScreen
import com.o9tech.prankcall.Screen.Search.SearchScreen
import com.o9tech.prankcall.Screen.SetVideoCall.SetVideoCallScreen
import com.o9tech.prankcall.Screen.SettingsSc.SettingsScreen
import com.o9tech.prankcall.Screen.Videocalling.FakeVideoCallScreen
import com.o9tech.prankcall.Screen.callended.CallEndedScreen
import com.o9tech.prankcall.Screen.videocallingscreen.VideoCallingScreen


@Composable
fun Navigation(){
    val context = LocalContext.current // Get context in Compose

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =Routes.HomeScreen ) {
        composable(Routes.HomeScreen) {
            HomeScreen(navController)
        }
        composable(Routes.FakeMessage) {
            FakeMessageScreen(navController)
        }
        composable(Routes.Language) {
            LanguageScreen(navController)
        }
        composable(Routes.Setting) {
            SettingsScreen(navController)
        }
        composable(Routes.Search) {
            SearchScreen(navController)
        }
        composable(Routes.CallScreen) {
            CallScreen(
                callerName = "Millar",
                callerImage = R.drawable.usa,
                onAnswer = { Toast.makeText(context, "Call Answered", Toast.LENGTH_SHORT).show() },
                onDecline = { Toast.makeText(context, "Call Declined", Toast.LENGTH_SHORT).show() }
            )
        }
        composable(Routes.AddCharacter) {
            AddCharacterSCreen()
        }
        composable(Routes.SetVideoCall) {
            SetVideoCallScreen(navController)
        }
        composable(Routes.FakeVideoCall) {
            FakeVideoCallScreen()
        }
        composable(Routes.ChooseThemeScreen) {
            ChooseThemeScreen()
        }
        composable(Routes.CallEndedScreen) {
            CallEndedScreen(
                profileImage = R.drawable.fake1,
//                onReturn = { Toast.makeText(context, "Return Clicked", Toast.LENGTH_SHORT).show() },
                onReturn = { navController.navigate(Routes.VideoCallingScreen)},
                onCallAgain = { Toast.makeText(context, "Call Again Clicked", Toast.LENGTH_SHORT).show() }
            )
        }
        composable(Routes.VideoCallingScreen) {
            VideoCallingScreen(
                callerImage = R.drawable.fake1,
                callerName = "Jisoo",
                onToggleVideo = { /* Handle Video Toggle */ },
                onToggleMic = { /* Handle Mic Toggle */ },
                onToggleSpeaker = { /* Handle Speaker Toggle */ },
                onEndCall = { /* Handle Call End */ }
            )
        }


    }
}






