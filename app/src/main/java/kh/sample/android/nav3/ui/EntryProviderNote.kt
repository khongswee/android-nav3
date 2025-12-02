package kh.sample.android.nav3.ui

import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import kh.sample.android.nav3.ui.fetch_detail.NoteFetchDetailDialog
import kh.sample.android.nav3.ui.fetch_detail.NoteFetchDetailViewModel
import kh.sample.android.nav3.ui.nav_rout.RouteDialogNoteFetchDetail
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu
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
        val dialogStrategy = remember { DialogSceneStrategy<NavKey>() }

        NavDisplay(
            backStack = navigation.backStack,
            sceneStrategy = dialogStrategy,
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
                entry<RouteDialogNoteFetchDetail> (
                    metadata = DialogSceneStrategy.dialog(DialogProperties())
                ){

                    val viewModel =
                        hiltViewModel<NoteFetchDetailViewModel, NoteFetchDetailViewModel.Factory>(
                            creationCallback = { factory ->
                                factory.create(it)
                            }
                        )
                    NoteFetchDetailDialog(
                        onFetchFail = {},
                        onFetchSuccess = { detail ->
                            sharedViewModel.saveMasterDetail(detail)
                            navigator.replace(RouteNoteDetail(detail))
                        },
                        viewModel = viewModel
                    )
                }
                entry<RouteNoteDetail> { key ->
                    val viewModel = hiltViewModel<NoteDetailViewMode, NoteDetailViewMode.Factory>(
                        creationCallback = { factory ->
                            factory.create(key)
                        }
                    )
                    NoteDetailScreen(
                        viewModel = viewModel,
                        stampTime = sharedViewModel.getStampTime(),
                    )
                }

            }
        )

    }
}