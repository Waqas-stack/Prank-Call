package com.o9tech.prankcall.Screen.FakeMessage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.DataModel.FakeMessage
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.PurpleGrey80

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeMessageScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()

    val fakeMessage = listOf(
        FakeMessage("Trivas", R.drawable.fake1),
        FakeMessage("Smith", R.drawable.fake2),
        FakeMessage("jhon", R.drawable.fake3),
        FakeMessage("ayan", R.drawable.fake4),
        FakeMessage("elisha", R.drawable.fake5),
        FakeMessage("Nawaz", R.drawable.fake6),
        FakeMessage("deph", R.drawable.fake7),
        FakeMessage("Elsvish", R.drawable.fake8),
        FakeMessage("United States", R.drawable.usa),
        FakeMessage("Canada", R.drawable.canada),
        FakeMessage("Turkey", R.drawable.turkey),
        FakeMessage("UAE", R.drawable.dubai),
        FakeMessage("Trivas", R.drawable.fake1),
        FakeMessage("ayan", R.drawable.fake4),
        FakeMessage("Elsvish", R.drawable.fake8),
        FakeMessage("Turkey", R.drawable.turkey),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Fake Message",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Orange40
                    )
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { safeNavController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = PurpleGrey80,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(start = 6.dp)
                        )
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        },

        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3), // 3 columns
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(fakeMessage.size) { item ->
                        val item = fakeMessage[item]
//                        CircularImageWithText(imageRes = item.first, text = item.second)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(80.dp) // Circle size
                                    .clip(CircleShape)
                                    .background(Color.LightGray) // Placeholder background
                            ) {
                                Image(
                                    painter = painterResource(id = item.pic),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(80.dp) // Fits inside the circle
                                        .clip(CircleShape)
                                )
                            }
//                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = item.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    )
}