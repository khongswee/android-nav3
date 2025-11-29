package kh.sample.android.nav3.ui.note_detail

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.sample.android.nav3.ui.nav_rout.RouteNoteDetail

@HiltViewModel(assistedFactory = NoteDetailViewMode.Factory::class)
class NoteDetailViewMode @AssistedInject constructor(
    @Assisted val navKey: RouteNoteDetail
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(runtimeArg: RouteNoteDetail): NoteDetailViewMode
    }
}