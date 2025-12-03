package kh.sample.android.nav3.di

import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.scene.DialogSceneStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import kh.sample.android.nav3.model.RefreshingModel
import kh.sample.android.nav3.ui.Navigator
import kh.sample.android.nav3.ui.NoteSharedViewModel
import kh.sample.android.nav3.ui.ResultStore
import kh.sample.android.nav3.ui.fetch_detail.NoteFetchDetailDialog
import kh.sample.android.nav3.ui.fetch_detail.NoteFetchDetailViewModel
import kh.sample.android.nav3.ui.nav_rout.RouteDialogNoteFetchDetail
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu
import kh.sample.android.nav3.ui.nav_rout.RouteNoteDetail
import kh.sample.android.nav3.ui.nav_rout.RouteNoteList
import kh.sample.android.nav3.ui.note_detail.NoteDetailScreen
import kh.sample.android.nav3.ui.note_detail.NoteDetailViewMode
import kh.sample.android.nav3.ui.note_list.NoteListScreen

@Module
@InstallIn(ActivityRetainedComponent::class)
object NoteModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(
        navigator: Navigator,
        resultRestore: ResultStore,
        sharedViewModel: NoteSharedViewModel
    ): EntryProviderInstaller = {
        entry<RouteNoteList> {
            val refreshing = resultRestore.getResultState<RefreshingModel?>()
            NoteListScreen(
                isRefreshing = refreshing?.isRefreshing ?: false,
                onNavigateDetail = { noteId ->
                    sharedViewModel.saveTime()
                    navigator.goTo(RouteDialogNoteFetchDetail(noteId))
                }
            )
        }

        entry<RouteDialogNoteFetchDetail>(
            metadata = DialogSceneStrategy.dialog(DialogProperties())
        ) { key ->
            val vm = hiltViewModel<NoteFetchDetailViewModel, NoteFetchDetailViewModel.Factory> {
                it.create(key)
            }

            NoteFetchDetailDialog(
                onFetchSuccess = {
                    sharedViewModel.saveMasterDetail(it)
                    navigator.replace(RouteNoteDetail(it))
                },
                onFetchFail = {},
                viewModel = vm
            )
        }

        entry<RouteNoteDetail> { key ->
            val vm = hiltViewModel<NoteDetailViewMode, NoteDetailViewMode.Factory> {
                it.create(key)
            }

            NoteDetailScreen(
                viewModel = vm,
                stampTime = sharedViewModel.getStampTime(),
                onBack = { refresh ->
                    resultRestore.setResult<RefreshingModel>(result = refresh)
                    navigator.goBack()
                },
                onFinish = {
                    navigator.goBackTo(RouteMainMenu)
                }
            )
        }
    }
}