package kh.sample.android.nav3.ui.note_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteListScreen(onNavigateDetail: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        LazyColumn {
            items(100) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onNavigateDetail()
                        }) {
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Note $it")
                    Spacer(modifier = Modifier.size(8.dp))
                    HorizontalDivider()
                }
            }
        }
    }
}