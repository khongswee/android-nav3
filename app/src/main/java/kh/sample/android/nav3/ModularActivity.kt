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
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import kh.sample.android.nav3.di.EntryProviderInstaller
import kh.sample.android.nav3.ui.Navigator
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu
import kh.sample.android.nav3.ui.theme.Androidnav3Theme
import javax.inject.Inject

@AndroidEntryPoint
class ModularActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var entryProviderScopes: Set<@JvmSuppressWildcards EntryProviderInstaller>

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
                    NoteModularApp(
                        modifier = Modifier.padding(innerPadding),
                        entryProviderScopes = entryProviderScopes,
                        navigator = navigator
                    )
                }
            }
        }
    }

}

@Composable
private fun NoteModularApp(
    modifier: Modifier = Modifier,
    entryProviderScopes: Set<EntryProviderInstaller>,
    navigator: Navigator
) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        onBack = { navigator.goBack() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entryProviderScopes.forEach { builder -> this.builder() }
        },

        )
}