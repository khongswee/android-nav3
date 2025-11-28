package kh.sample.android.nav3.ui.note_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteListScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(100) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Note $it")
                    Spacer(modifier = Modifier.size(8.dp))
                    HorizontalDivider()
                }
            }
        }
    }
}