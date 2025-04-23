package com.o9tech.prankcall.AppNavigation

import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.o9tech.prankcall.R
import com.o9tech.prankcall.Screen.AddCharacterMsg.AddCharacterMsg
import com.o9tech.prankcall.Screen.AddNewCharacter.AddCharacterSCreen
import com.o9tech.prankcall.Screen.AddNewCharacter.ImagePicker
import com.o9tech.prankcall.Screen.AddNewCharacter.VideoPicker
import com.o9tech.prankcall.Screen.AudioCallEnded.AudioCallEndedScreen
import com.o9tech.prankcall.Screen.AudioCalling.AudioCallingScreen
import com.o9tech.prankcall.Screen.Callscreen.CallScreen
import com.o9tech.prankcall.Screen.ChooseTheme.ChooseThemeScreen
import com.o9tech.prankcall.Screen.FakeMessage.FakeMessageScreen
import com.o9tech.prankcall.Screen.FakeVideos.FakeVideoScreen
import com.o9tech.prankcall.Screen.HomeScreen
import com.o9tech.prankcall.Screen.IncomingCall.IncommingCallScreen
import com.o9tech.prankcall.Screen.IncommingAudioCallScreen.IncommingAudioCall
import com.o9tech.prankcall.Screen.Languages.LanguageScreen
import com.o9tech.prankcall.Screen.Search.SearchScreen
import com.o9tech.prankcall.Screen.SetCall.SetCallScreen
import com.o9tech.prankcall.Screen.SetVideoCall.SetVideoCallScreen
import com.o9tech.prankcall.Screen.SettingsSc.SettingsScreen
import com.o9tech.prankcall.Screen.Videocalling.FakeVideoCallScreen
import com.o9tech.prankcall.Screen.callended.CallEndedScreen
import com.o9tech.prankcall.Screen.fakeaudioca.FakeAudioScreen
import com.o9tech.prankcall.Screen.setFakeMessage.OverlappingBoxWithRoundedCorners
import com.o9tech.prankcall.Screen.videocallingscreen.VideoCallingScreen
import com.o9tech.prankcall.viewModel.MainViewModel


@Composable
fun Navigation() {
    val context = LocalContext.current

    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()
    NavHost(navController = navController, startDestination = Routes.HomeScreen) {
        composable(Routes.HomeScreen) {
            HomeScreen(navController, mainViewModel)
        }
        composable(Routes.FakeMessage) {
            FakeMessageScreen(navController, mainViewModel)
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
            AddCharacterSCreen(navController, mainViewModel)
        }
        composable(
            "SetVideoCallScreen/{name}/{pic}/{path}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("pic") { type = NavType.StringType },
                navArgument("path") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
            val pic = backStackEntry.arguments?.getString("pic") ?: ""
            val path = backStackEntry.arguments?.getString("path") ?: "Unknown"
            SetVideoCallScreen(navController, name = name, flag = pic, videoPath = path)

        }


        composable("FakeVideoCall/{videoPath}/{pic}") { backStackEntry ->
            val encodedPath = backStackEntry.arguments?.getString("videoPath") ?: ""
            val videoPath = Uri.decode(encodedPath)
            val picencodedPath = backStackEntry.arguments?.getString("pic") ?: ""
            val picvideoPath = Uri.decode(picencodedPath)

            FakeVideoCallScreen(
                mainViewModel = mainViewModel,
                navController = navController,
                profileImage = picvideoPath,
                videoPath = videoPath
            )
        }



        composable(Routes.ChooseThemeScreen) {
            ChooseThemeScreen(navController)
        }
        composable("CallEndedScreen/{profileImage}") { backStackEntry ->
            val profileImage = backStackEntry.arguments?.getString("profileImage") ?: ""
            val decodedPic = Uri.decode(profileImage)

            CallEndedScreen(
                navController = navController,
                profileImage = decodedPic,
                onReturn = {
                    navController.popBackStack()
                },
                onCallAgain = {
                    navController.popBackStack()
                }
            )
        }



        composable("VideoCallingScreen/{name}/{pic}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val pic = backStackEntry.arguments?.getString("pic") ?: ""
            val decodedPic = Uri.decode(pic)
            VideoCallingScreen(
                navController,
                callerName = name,
                callerImage = decodedPic,
                onToggleVideo = {},
                onToggleMic = {  },
                onToggleSpeaker = {  },
                onEndCall = {  })
        }

        composable(
            "SetCallScreen/{name}/{flag}/{audioPath}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("flag") { type = NavType.IntType },
                navArgument("audioPath") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val flag = backStackEntry.arguments?.getInt("flag") ?: 0
            val audioPath = Uri.decode(backStackEntry.arguments?.getString("audioPath") ?: "")

            SetCallScreen(navController, name, flag,audioPath)
        }
        composable("asset_picker") {
            ImagePicker(
                onPicked = { assets ->
                    mainViewModel.clearSelectedImages()
                    mainViewModel.updateSelectedImages(assets)
                    navController.navigateUp()
                },
                onClose = { assets ->
                    mainViewModel.clearSelectedImages()
                    navController.navigateUp()
                }
            )
        }

        composable("fake_message_asset_picker") {
            ImagePicker(
                onPicked = { assets ->
                    mainViewModel.clearSelectedFakeMessage()
                    mainViewModel.updateSelectedFakeMessage(assets)
                    navController.navigateUp()
                },
                onClose = { assets ->
                    mainViewModel.clearSelectedFakeMessage()
                    navController.navigateUp()
                }
            )
        }

        composable("video_picker") {
            VideoPicker(
                onPicked = { assets ->
                    mainViewModel.clearSelectedVideos()
                    mainViewModel.updateSelectedVideos(assets)
                    navController.navigateUp()
                },
                onClose = { assets ->
                    mainViewModel.clearSelectedVideos()
                    navController.navigateUp()
                }
            )
        }

        composable("IncommingCallScreen/{name}/{pic}/{path}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val pic = backStackEntry.arguments?.getString("pic") ?: ""
            val path = backStackEntry.arguments?.getString("path") ?: ""
            val decodedPic = Uri.decode(pic)
            IncommingCallScreen(
                navController,
                callerName = name,
                profileImage = decodedPic,
                videoPath = path,
                onCallAgain = {})
        }


        composable(Routes.FakeVideoScreen) {
            FakeVideoScreen(navController, mainViewModel)
        }
        composable(Routes.FakeAudioScreen) {
            FakeAudioScreen(navController)
        }
        composable(Routes.OverlappingBoxWithRoundedCorners) {
            OverlappingBoxWithRoundedCorners(navController )
        }
        composable(Routes.AddCharacterMsg) {
            AddCharacterMsg(navController, mainViewModel)
        }
        composable("IncommingAudioCall/{name}/{flag}/{audioPath}", arguments = listOf(
            navArgument("name") { type = NavType.StringType },
            navArgument("flag") { type = NavType.IntType },
            navArgument("audioPath") { type = NavType.StringType },
        )) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val flag = backStackEntry.arguments?.getInt("flag") ?: 0
            val audioPath = Uri.decode(backStackEntry.arguments?.getString("audioPath") ?: "")
            IncommingAudioCall(
                navController,
                audioPath,
                profileImage = flag,
                onCallAgain = {},
                callerName = name,
            )
        }



            composable("AudioCallingScreen/{name}/{flag}/{audioPath}", arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("flag") { type = NavType.IntType },
                navArgument("audioPath") { type = NavType.StringType },

                )) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val flag = backStackEntry.arguments?.getInt("flag") ?: 0
                val audioPath = Uri.decode(backStackEntry.arguments?.getString("audioPath") ?: "")

                AudioCallingScreen(
                    navController,
                    audioPath,
                    profileImage = flag,
                    onCallAgain = {},
                    callerName = name,
                )

        }



        composable("AudioCallEndedScreen/{name}/{flag}", arguments = listOf(
            navArgument("name") { type = NavType.StringType },
            navArgument("flag") { type = NavType.IntType }
        )) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val flag = backStackEntry.arguments?.getInt("flag") ?: 0
            AudioCallEndedScreen(
                navController,
                profileImage = flag,
                onCallAgain = {
                    navController.popBackStack()
                },
                onReturn = {
                    navController.popBackStack()
                },
                callername = name,
            )

        }

    }
}






