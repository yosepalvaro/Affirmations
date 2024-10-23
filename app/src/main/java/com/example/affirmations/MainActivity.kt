package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

// Kelas MainActivity, yang berfungsi sebagai titik masuk aplikasi.
class MainActivity : ComponentActivity() {
    // Dipanggil saat activity dibuat.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Terapkan tema kustom ke UI aplikasi
            AffirmationsTheme {
                // Buat permukaan yang mengisi ukuran maksimum layar
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Panggil fungsi composable utama untuk menampilkan konten
                    AffirmationsApp()
                }
            }
        }
    }
}

// Fungsi composable utama untuk aplikasi afirmasi.
@Composable
fun AffirmationsApp() {
    // Muat dan tampilkan daftar afirmasi menggunakan kelas Datasource.
    AffirmationList(affirmationList = Datasource().loadAffirmations())
}

// Fungsi composable untuk menampilkan satu kartu afirmasi.
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    // Buat kartu untuk menampung konten afirmasi.
    Card(modifier = modifier) {
        Column {
            // Tampilkan gambar afirmasi.
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = modifier.fillMaxWidth().height(194.dp), // Atur dimensi gambar.
                contentScale = ContentScale.Crop // Skala gambar untuk mengisi area.
            )

            // Tampilkan teks afirmasi.
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = modifier.padding(16.dp), // Tambahkan padding di sekitar teks.
                style = MaterialTheme.typography.headlineSmall // Atur gaya teks.
            )
        }
    }
}

// Fungsi composable untuk menampilkan daftar afirmasi.
@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    // Buat kolom lazy untuk scrolling yang efisien.
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            // Tampilkan setiap afirmasi dalam kartu dengan padding.
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// Fungsi preview untuk melihat aplikasi afirmasi di IDE.
@Preview
@Composable
private fun AffirmationPreview() {
    AffirmationsApp() // Tampilkan AffirmationsApp untuk preview.
}
