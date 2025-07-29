package com.example.testapp.View

import android.app.ActionBar
import android.app.StatusBarManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapp.R

@Composable
fun ItemDetailScreen(navController: NavController, img: Int, decode: String, decode1: String) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .verticalScroll(scrollState)

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = img),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds

            )
            Text(
                text = decode,
                fontSize = 20.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .align(Alignment.BottomStart)

            )

        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = decode1,
            fontSize = 15.sp,
            color = Color.Black,

            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 10.dp)


        )

    }
}

@Preview
@Composable
fun ItemDetailScreenPreview() {
    ItemDetailScreen(navController = NavController(LocalContext.current), R.drawable.ic_launcher_foreground, "Title", "Description")
}
