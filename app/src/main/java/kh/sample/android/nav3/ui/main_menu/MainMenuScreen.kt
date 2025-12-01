package kh.sample.android.nav3.ui.main_menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import kh.sample.android.nav3.ui.nav_rout.RouteNoteList
import kh.sample.android.nav3.ui.nav_rout.RouteNoteMain
import kh.sample.android.nav3.ui.nav_rout.RouteSetting

@Composable
fun MainMenu(onEntryFeatureRoute: (NavKey) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        Button(onClick = { onEntryFeatureRoute(RouteNoteMain) }) {
            Text("Note")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { onEntryFeatureRoute(RouteSetting) }) {
            Text("Setting")
        }

    }

}