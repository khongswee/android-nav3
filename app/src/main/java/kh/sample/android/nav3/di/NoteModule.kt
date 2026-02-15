package kh.sample.android.nav3.di

import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.scene.DialogSceneStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import kh.sample.android.nav3.ext.SharedViewModelStoreNavEntryDecorator
import kh.sample.android.nav3.ext.toContentKey
import kh.sample.android.nav3.model.RefreshingModel
import kh.sample.android.nav3.ui.Navigator
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
import kh.sample.android.nav3.ui.shared.NoteSharedViewModel

@Module
@InstallIn(ActivityRetainedComponent::class)
object NoteModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(
        navigator: Navigator,
        resultRestore: ResultStore,
    ): EntryProviderInstaller = {
        entry<RouteNoteList>(
            clazzContentKey = { key -> key.toContentKey() },
        )
        {
            val refreshing = resultRestore.getResultState<RefreshingModel?>()
            val sharedViewModel = viewModel(modelClass = NoteSharedViewModel::class)

            NoteListScreen(
                isRefreshing = refreshing?.isRefreshing ?: false,
                onNavigateDetail = { noteId ->
                    sharedViewModel.count()
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
                    navigator.replace(RouteNoteDetail(it))
                },
                onFetchFail = {},
                viewModel = vm
            )
        }

        entry<RouteNoteDetail>(
            metadata =
                SharedViewModelStoreNavEntryDecorator.parent(
                    RouteNoteList.toContentKey()
                ),
        ) { key ->
            val vm = hiltViewModel<NoteDetailViewMode, NoteDetailViewMode.Factory> {
                it.create(key)
            }
            val sharedViewModel = viewModel(modelClass = NoteSharedViewModel::class)


            NoteDetailScreen(
                viewModel = vm,
                countTing = sharedViewModel.count,
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