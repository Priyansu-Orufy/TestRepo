package com.example.testapp.View

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapp.R
import com.example.testapp.ViewModel.ItemViewModel
import com.example.testapp.ui.theme.TestAppTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {



    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = rememberNavController())
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.FROYO)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    val itemViewModel: ItemViewModel = viewModel()
    val searchQuery by itemViewModel.searchQuery.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .border(BorderStroke(0.5.dp, Color.Gray), shape = RoundedCornerShape(45.dp))
                .clip(RoundedCornerShape(45.dp))
                .background(color = Color.White)
                .padding(end = 5.dp, start = 5.dp),

            singleLine = true,
            value = searchQuery,
            onValueChange = {    itemViewModel.onSearchQueryChanged(it) },
                colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            placeholder = {Text(text = "Search", fontStyle = FontStyle.Normal)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { itemViewModel.onSearchQueryChanged("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear search"
                        )
                    }
                }
            }

        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),

        ) {

            val itemlist = itemViewModel.items.value ?: emptyList()

            items(itemlist.filter{it.title.lowercase().contains(searchQuery.lowercase()) || it.description.lowercase().contains(searchQuery.lowercase())}) { item ->
                ItemCard(
                    image = item.image,
                    title = item.title,
                    description = item.description,
                    onItemClick = {
                        val encodedTitle = Base64.encodeToString(item.title.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP)
                        val encodedDesc = Base64.encodeToString(item.description.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP)

                        navController.navigate("detail/$encodedTitle/$encodedDesc/${item.image}")
                    }
                )
            }

        }
    }
}

@Composable
fun ItemCard(image: Int, title: String, description: String ,onItemClick: () -> Unit = {} ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onItemClick() }


    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),

            ) {
            Image(
                modifier = Modifier
                    .height(90.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .width(90.dp),
                painter = painterResource(id = image),
                contentDescription = "Image",
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillBounds,


                )
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp)) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(),
                    maxLines = 1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis

                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = description,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxSize(),
                    maxLines = 3,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis


                )
            }
        }
    }

}

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(navController)
        }
        composable(
            "detail/{title}/{description}/{image}",
            arguments = listOf(
                navArgument("image") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType},
                navArgument("description") { type = NavType.StringType}
            )
        ){entry ->
            val encodedTitle = entry.arguments?.getString("title")!!
            val encodedDesc = entry.arguments?.getString("description")!!
            val image = entry.arguments?.getInt("image")!!
            val title = String(Base64.decode(encodedTitle, Base64.URL_SAFE))
            val description = String(Base64.decode(encodedDesc, Base64.URL_SAFE))

            ItemDetailScreen(
                navController = navController,
                img = image,
                decode = title,
                decode1 = description
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    ItemCard(R.drawable.ic_launcher_foreground, "Title", "Description")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAppTheme {
        MainScreen(navController = rememberNavController())
    }
}