package com.example.photogallery.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.photogallery.data.Photosource
import com.example.photogallery.model.Photo

@Composable
fun FullPhotoViewScreen(
    photoIndex: Int,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val context: Context = LocalContext.current
    val photos: List<Photo> = Photosource().fetchAllPhotos()
    val mainPhoto: Photo = photos[photoIndex]
    val previousButtonVisible: Boolean = (photoIndex > 0)
    val nextButtonVisible: Boolean = (photoIndex < photos.size - 1)

    Column {
        BackToGridButton(navController)

        Row (modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(color = Color.Black)
            .padding(10.dp)
        ){
            PreviousButton(
                isVisible = previousButtonVisible,
                targetIndex = photoIndex - 1,
                navController,
                modifier = Modifier
                    .weight(0.1f)
            )

            FullPhotoBox(
                mainPhoto,
                context,
                modifier = Modifier
                    .weight(0.8f)
            )

            NextButton(
                isVisible = nextButtonVisible,
                targetIndex = photoIndex + 1,
                navController,
                modifier = Modifier
                    .weight(0.1f)
            )
        }

        PhotoIndexText(
            photoIndex = photoIndex + 1,
            photosSize = photos.size
        )
    }
}

@Composable
fun BackToGridButton (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Black)
    ) {
        IconButton(
            onClick = { navController.popBackStack(route = "grid", inclusive = false) },
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = "Back",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun FullPhotoBox (
    mainPhoto: Photo,
    context: Context,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(mainPhoto.photoResourceId),
            contentDescription = Photosource().getPhotoName(mainPhoto, context),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun PreviousButton (
    isVisible: Boolean,
    targetIndex: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
    ) {
        IconButton(
            onClick = { navController.navigate("photo/$targetIndex") },
            enabled = isVisible,
            modifier = Modifier
                .alpha(if (isVisible) 1f else 0f)
                .height(if (isVisible) Dp.Unspecified else 0.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun NextButton (
    isVisible: Boolean,
    targetIndex: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
    ) {
        IconButton(
            onClick = { navController.navigate("photo/$targetIndex") },
            enabled = isVisible,
            modifier = Modifier
                .alpha(if (isVisible) 1f else 0f)
                .height(if (isVisible) Dp.Unspecified else 0.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Back",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun PhotoIndexText (
    photoIndex: Int,
    photosSize: Int,
    modifier: Modifier = Modifier
) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Black)
    ) {
        Text(
            text = "$photoIndex in $photosSize",
            fontSize = 30.sp,
            color = Color.White
        )
    }
}

fun navigateToTargetPhoto(
    photoIndex: Int
) {

}

@Preview(showBackground = true)
@Composable
fun FullPhotoViewScreenPreview() {
    FullPhotoViewScreen(photoIndex = 0)
}