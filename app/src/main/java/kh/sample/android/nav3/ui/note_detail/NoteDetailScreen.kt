package kh.sample.android.nav3.ui.note_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.sample.android.nav3.model.NoteDetailModel

@Composable
fun NoteDetailScreen(viewModel: NoteDetailViewMode, stampTime: Long, detail: NoteDetailModel?) {
    val noteDetail by viewModel.noteDetail.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.initDetail()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(text = "ID = ${noteDetail?.id}")
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "Detail = ${noteDetail?.detail}")
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "Stamp Time = ${stampTime}")

    }
}