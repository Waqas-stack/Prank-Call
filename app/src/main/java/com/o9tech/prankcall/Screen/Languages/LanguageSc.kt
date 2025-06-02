package com.o9tech.prankcall.Screen.Languages

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.DataModel.Languagesis
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.white
import com.o9tech.prankcall.utils.LocaleManager
import androidx.core.content.edit
import com.o9tech.prankcall.utils.PreferenceHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()
    val context = LocalContext.current

    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val savedLangCode = prefs.getString("language", "en") ?: "en"

    val languages = listOf(
        Languagesis("French", R.drawable.france, "fr"),
        Languagesis("Chinese", R.drawable.china, "zh"),
        Languagesis("Spanish", R.drawable.spain, "es"),
        Languagesis("English", R.drawable.usa, "en"),
        Languagesis("German", R.drawable.germany, "de"),

    )




//    var selectedLanguage by remember { mutableStateOf(languages.first()) }
    val preferenceHelper = remember { PreferenceHelper(context) }



    var selectedLanguage by remember {
        mutableStateOf(
            languages.find { it.localeCode == savedLangCode } ?: languages.first()
        )
    }






    Scaffold(

        topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth().background(white),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.background(white).padding(start = 10.dp),
                        onClick = {
                        safeNavController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = grey,
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize().background(white),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
//                            text = "Language",
                            text = stringResource(R.string.language),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleLarge,
                            color = Orange40
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize().background(white)
                    .padding(it)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize().background(white)
                        .padding(10.dp)
                ) {
                    items(languages.size) { index ->
                        val language = languages[index]
                        val isSelected = language == selectedLanguage

                        Card(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            ),
                            onClick = {
                                selectedLanguage = language
                                val newContext = LocaleManager.setLocale(context, language.localeCode)
                                preferenceHelper.saveLanguage(language)
                                (context as Activity).recreate()
                                      },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = white
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .background(Color.LightGray)
                                    ) {
                                        Image(
                                            painter = painterResource(id = language.flag),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(CircleShape)
                                        )
                                    }
                                    Text(
                                        text = language.name,
                                        style = TextStyle(
                                            platformStyle = PlatformTextStyle(
                                                includeFontPadding = false
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.W400,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                                RadioButton(
                                    selected = isSelected,
                                    onClick = {
                                        selectedLanguage = language
                                        val newContext = LocaleManager.setLocale(context, language.localeCode)
                                        preferenceHelper.saveLanguage(language)
                                        (context as Activity).recreate()

//                                        val intent = (context as Activity).intent
//                                        context.finish()
//                                        context.startActivity(intent)

                                              },

                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Orange40,
                                        unselectedColor = Color.Gray,
                                        disabledSelectedColor = Color.LightGray,
                                        disabledUnselectedColor = Color.DarkGray
                                    )
                                )

                            }
                        }
                    }
                }
            }
        }
    )
}