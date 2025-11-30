package kh.sample.android.nav3.ui.note_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NoteDetailScreen(viewModel: NoteDetailViewMode) {
    val noteDetail by viewModel.noteDetail.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetail(viewModel.navKey.noteId)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Note Detail Id = ${viewModel.navKey.noteId} | Detail = ${noteDetail?.detail}")
    }
}