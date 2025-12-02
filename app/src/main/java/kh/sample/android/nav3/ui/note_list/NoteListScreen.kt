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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.sample.android.nav3.model.NoteModel
import kotlinx.coroutines.launch

@Composable
fun NoteListScreen(
    isRefreshing: Boolean,
    onNavigateDetail: (Int) -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
) {

    val noteListState by viewModel.noteListFlow.collectAsStateWithLifecycle()
    val refreshState by viewModel.refreshing.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadList(isRefresh = isRefreshing)
    }

    LaunchedEffect(key1 = refreshState) {
        if (refreshState) {
            scope.launch {
                listState.scrollToItem(0)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        PullToRefreshBasicSample(
            lazyListState = listState,
            items = noteListState,
            isRefreshing = refreshState,
            onRefresh = { viewModel.loadList(true) },
            onNavigateDetail = {
                onNavigateDetail(it)
            }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBasicSample(
    lazyListState: LazyListState = rememberLazyListState(),
    items: List<NoteModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateDetail: (Int) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        LazyColumn(Modifier.fillMaxSize(), state = lazyListState) {
            items(items) { note ->
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

