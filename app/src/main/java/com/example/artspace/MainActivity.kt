package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    val artworks = listOf(
        Artwork(R.drawable.luffy, "luffy"),
        Artwork(R.drawable.luffy, "zoro"),
        Artwork(R.drawable.luffy, "nami")
    )
    var currentArtworkIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        ArtworkWall(artworks[currentArtworkIndex])
        Spacer(modifier = Modifier.height(16.dp))
        ArtworkDescriptor(artworks[currentArtworkIndex])
        Spacer(modifier = Modifier.height(16.dp))
        DisplayController(
            onPrevious = {
                if (currentArtworkIndex > 0) currentArtworkIndex--
            },
            onNext = {
                if (currentArtworkIndex < artworks.size - 1) currentArtworkIndex++
            }
        )
    }
}

data class Artwork(val imageRes: Int, val title: String)

@Composable
fun ArtworkWall(artwork: Artwork) {
    Image(
        painter = painterResource(id = artwork.imageRes),
        contentDescription = "Artwork Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

@Composable
fun ArtworkDescriptor(artwork: Artwork) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = artwork.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

    }
}

@Composable
fun DisplayController(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPrevious, enabled = true) {
            Text("Previous")
        }
        Button(onClick = onNext, enabled = true) {
            Text("Next")
        }
    }
}
