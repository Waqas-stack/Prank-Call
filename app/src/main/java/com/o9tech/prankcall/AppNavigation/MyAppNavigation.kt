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
import com.o9tech.prankcall.Screen.Callscreen.CallScreen
import com.o9tech.prankcall.Screen.ChooseTheme.ChooseThemeScreen
import com.o9tech.prankcall.Screen.FakeMessage.FakeMessageScreen
import com.o9tech.prankcall.Screen.FakeVideos.FakeVideoScreen
import com.o9tech.prankcall.Screen.HomeScreen
import com.o9tech.prankcall.Screen.IncomingCall.IncommingCallScreen
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
    val context = LocalContext.current // Get context in Compose

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





//        composable(Routes.FakeVideoCall) {
//            FakeVideoCallScreen(mainViewModel)
//        }

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
//        composable(Routes.CallEndedScreen) {
//            CallEndedScreen(
//                navController,
//                profileImage = R.drawable.fake1,
////                onReturn = { Toast.makeText(context, "Return Clicked", Toast.LENGTH_SHORT).show() },
//                onReturn = {
//                    navController.popBackStack()
////                    navController.navigate(Routes.VideoCallingScreen)
//                           },
////                onCallAgain = { Toast.makeText(context, "Call Again Clicked", Toast.LENGTH_SHORT).show() }
//                onCallAgain = {
//                    navController.navigate(Routes.SetCallScreen)
//                }
//            )
//        }

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
//                    navController.navigate("SetCallScreen")
                }
            )
        }



//        composable(Routes.VideoCallingScreen) {
//            VideoCallingScreen(
//                navController,
//                callerImage = "https://example.com/caller_image.jpg",
//                callerName = "Jisoo",
//                onToggleVideo = { /* Handle Video Toggle */ },
//                onToggleMic = { /* Handle Mic Toggle */ },
//                onToggleSpeaker = { /* Handle Speaker Toggle */ },
//                onEndCall = { /* Handle Call End */ }
//            )
//        }


        composable("VideoCallingScreen/{name}/{pic}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val pic = backStackEntry.arguments?.getString("pic") ?: ""

            // Decode the encoded picture path
            val decodedPic = Uri.decode(pic)

            // Use the values in your composable
            VideoCallingScreen(
                navController,
                callerName = name,
                callerImage = decodedPic,
                onToggleVideo = { /* Handle Video Toggle */ },
                onToggleMic = { /* Handle Mic Toggle */ },
                onToggleSpeaker = { /* Handle Speaker Toggle */ },
                onEndCall = { /* Handle Call End */ })
        }

        composable(
            "SetCallScreen/{name}/{flag}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("flag") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val flag = backStackEntry.arguments?.getInt("flag") ?: 0
            SetCallScreen(navController, name, flag)
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


//        composable("IncommingCallScreen/{name}/{drawableId}")
//        { backStackEntry ->
//            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
//            val drawableId = backStackEntry.arguments?.getString("drawableId")?.toIntOrNull() ?: R.drawable.ic_launcher_background
//
//            IncommingCallScreen(navController, callerName = name, profileImage = drawableId.toString(), onCallAgain = {})
//        }


//        composable(
//            "IncommingCallScreen/{name}/{flag}",
//            arguments = listOf(
//                navArgument("name") { type = NavType.StringType },
//                navArgument("flag") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
//            val pic = backStackEntry.arguments?.getString("pic") ?: ""
//            IncommingCallScreen(navController , callerName = name, profileImage = pic, onCallAgain = {})
////            IncommingCallScreen(navController, callerName = name, profileImage = drawableId.toString(), onCallAgain = {})
//
//
//        }


        composable("IncommingCallScreen/{name}/{pic}/{path}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val pic = backStackEntry.arguments?.getString("pic") ?: ""
            val path = backStackEntry.arguments?.getString("path") ?: ""

            // Decode the encoded picture path
            val decodedPic = Uri.decode(pic)

            // Use the values in your composable
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
            OverlappingBoxWithRoundedCorners(navController, mainViewModel)
        }
        composable(Routes.AddCharacterMsg) {
            AddCharacterMsg(navController, mainViewModel)
        }




//        composable("VideoCallingScreen/{name}/{pic}/{videoPath}") { backStackEntry ->
//            // Extract arguments from the navigation back stack entry
//            val name = backStackEntry.arguments?.getString("name") ?: ""
//            val pic = backStackEntry.arguments?.getString("pic") ?: ""
//            val videoPath = backStackEntry.arguments?.getString("videoPath") ?: ""
//
//            // Decode the encoded picture path and video path
//            val decodedPic = Uri.decode(pic)
//            val decodedVideoPath = Uri.decode(videoPath)
//
//            // Now pass the decoded values to the VideoCallingScreen composable
//            VideoCallingScreen(
//                navController,
//                callerName = name,
//                callerImage = decodedPic, // This will be the decoded image path
//                videoPath = decodedVideoPath, // This is the decoded video path
//                onToggleVideo = { /* Handle Video Toggle */ },
//                onToggleMic = { /* Handle Mic Toggle */ },
//                onToggleSpeaker = { /* Handle Speaker Toggle */ },
//                onEndCall = { /* Handle Call End */ }
//            )
//        }



    }
}






