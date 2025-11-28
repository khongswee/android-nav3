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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kh.sample.android.nav3.ui.Navigator
import kh.sample.android.nav3.ui.featureMainMenu
import kh.sample.android.nav3.ui.featureNote
import kh.sample.android.nav3.ui.featureSetting
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu
import kh.sample.android.nav3.ui.nav_rout.RouteNoteDetail
import kh.sample.android.nav3.ui.nav_rout.RouteNoteList
import kh.sample.android.nav3.ui.note_detail.NoteDetailScreen
import kh.sample.android.nav3.ui.note_list.NoteListScreen
import kh.sample.android.nav3.ui.theme.Androidnav3Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidnav3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NoteApp()
                }
            }
        }
    }
}

@Composable
fun NoteApp() {
    val backStack = remember { mutableStateListOf<NavKey>(RouteMainMenu) }

    val navigator = remember {
        Navigator(
            onPush = { backStack.add(it) },
            onPop = { backStack.removeLastOrNull() }
        )
    }

    NavDisplay(
        backStack = backStack,
        onBack = {
            navigator.goBack()
        },
        entryProvider = entryProvider {
            featureMainMenu(navigator = navigator)
            featureNote(navigator = navigator)
            featureSetting()
        }
    )
}


