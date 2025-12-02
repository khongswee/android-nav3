package kh.sample.android.nav3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import kh.sample.android.nav3.ui.Navigator
import kh.sample.android.nav3.ui.featureMainMenu
import kh.sample.android.nav3.ui.featureNote
import kh.sample.android.nav3.ui.featureSetting
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu
import kh.sample.android.nav3.ui.theme.Androidnav3Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Androidnav3Theme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = {
                        Text(text = "Note App")
                    })
                }) { innerPadding ->
                    NoteApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NoteApp(modifier: Modifier = Modifier) {
    val navigator = remember {
        Navigator(startDestination = RouteMainMenu)
    }

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = navigator.backStack,
        onBack = {
            navigator.goBack()
        },
        entryProvider = entryProvider {
            featureMainMenu(navigator = navigator)
            featureNote()
            featureSetting()
        }
    )
}


