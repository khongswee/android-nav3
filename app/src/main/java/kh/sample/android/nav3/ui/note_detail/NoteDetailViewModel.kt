package kh.sample.android.nav3.ui.note_detail

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.sample.android.nav3.model.NoteDetailModel
import kh.sample.android.nav3.ui.nav_rout.RouteNoteDetail
import kh.sample.android.nav3.usecase.GetNoteDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = NoteDetailViewMode.Factory::class)
class NoteDetailViewMode @AssistedInject constructor(
    @Assisted val navKey: RouteNoteDetail,
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(runtimeArg: RouteNoteDetail): NoteDetailViewMode
    }

    private val _noteDetail: MutableStateFlow<NoteDetailModel?> = MutableStateFlow(null)
    val noteDetail = _noteDetail

    fun initDetail(){
        _noteDetail.update {
            navKey.detail
        }
    }
}