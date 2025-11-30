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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NoteListScreen(
    onNavigateDetail: (Int) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
) {

    val noteListState by viewModel.noteListFlow.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadList()
    }

    Box(modifier = Modifier.fillMaxSize()
 .padding(12.dp)) {
        LazyColumn {
            items(noteListState) { note ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onNavigateDetail(note.id)
                        }) {
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = note.title)
                    Spacer(modifier = Modifier.size(8.dp))
                    HorizontalDivider()
                }
            }
        }
    }
}