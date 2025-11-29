package kh.sample.android.nav3.ui

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kh.sample.android.nav3.ui.nav_rout.RouteNoteDetail
import kh.sample.android.nav3.ui.nav_rout.RouteNoteList
import kh.sample.android.nav3.ui.note_detail.NoteDetailScreen
import kh.sample.android.nav3.ui.note_detail.NoteDetailViewMode
import kh.sample.android.nav3.ui.note_list.NoteListScreen

fun EntryProviderScope<NavKey>.featureNote(navigator: Navigator) {
    entry<RouteNoteList> {
        NoteListScreen(onNavigateDetail = { noteId ->
            navigator.goTo(RouteNoteDetail(noteId = noteId))
        })
    }

    entry<RouteNoteDetail> { key ->
        val viewModel = hiltViewModel<NoteDetailViewMode, NoteDetailViewMode.Factory>(
            creationCallback = { factory ->
                factory.create(key)
            }
        )
        NoteDetailScreen(viewModel)
    }
}