package kh.sample.android.nav3.ui.fetch_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.sample.android.nav3.model.NoteDetailModel

@Composable
fun NoteFetchDetailDialog(
    onFetchSuccess: (NoteDetailModel) -> Unit,
    onFetchFail: () -> Unit,
    viewModel: NoteFetchDetailViewModel
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    /**
     * Observe State
     */
    when (uiState) {
        is FetchNoteDetailState.Idle -> {}
        is FetchNoteDetailState.Loading -> {}
        is FetchNoteDetailState.Success -> {
            onFetchSuccess((uiState as FetchNoteDetailState.Success).detail)
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getDetail()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (uiState is FetchNoteDetailState.Loading) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}