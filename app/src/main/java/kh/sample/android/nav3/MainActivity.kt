package kh.sample.android.nav3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kh.sample.android.nav3.ui.note_list.NoteListScreen
import kh.sample.android.nav3.ui.theme.Androidnav3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidnav3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NoteListScreen()
                }
            }
        }
    }
}