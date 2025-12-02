package kh.sample.android.nav3.ui

import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kh.sample.android.nav3.ui.nav_rout.RouteNoteDetail
import kh.sample.android.nav3.ui.nav_rout.RouteNoteList
import kh.sample.android.nav3.ui.nav_rout.RouteNoteMain
import kh.sample.android.nav3.ui.note_detail.NoteDetailScreen
import kh.sample.android.nav3.ui.note_detail.NoteDetailViewMode
import kh.sample.android.nav3.ui.note_list.NoteListScreen

fun EntryProviderScope<NavKey>.featureNote() {
    entry<RouteNoteMain> {
        val navigator = remember {
            Navigator(startDestination = RouteNoteList)
        }
        val sharedViewModel = hiltViewModel<NoteSharedViewModel>()

        NavDisplay(
            backStack = navigator.backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            onBack = {
                navigator.goBack()
            },
            entryProvider = entryProvider {
                entry<RouteNoteList> {
                    NoteListScreen(onNavigateDetail = { noteId ->
                        sharedViewModel.saveTime()
                        navigator.goTo(RouteNoteDetail(noteId = noteId))
                    })
                }
                entry<RouteNoteDetail> { key ->
                    val viewModel = hiltViewModel<NoteDetailViewMode, NoteDetailViewMode.Factory>(
                        creationCallback = { factory ->
                            factory.create(key)
                        }
                    )
                    NoteDetailScreen(
                        viewModel = viewModel,
                        stampTime = sharedViewModel.getStampTime()
                    )
                }

            }
        )

    }
}