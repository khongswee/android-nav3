package kh.sample.android.nav3.ui.fetch_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.sample.android.nav3.model.NoteDetailModel
import kh.sample.android.nav3.ui.nav_rout.RouteDialogNoteFetchDetail
import kh.sample.android.nav3.usecase.GetNoteDetailUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = NoteFetchDetailViewModel.Factory::class)
class NoteFetchDetailViewModel @AssistedInject constructor(
    @Assisted val navKey: RouteDialogNoteFetchDetail,
    private val useCase: GetNoteDetailUseCase
) : ViewModel() {
    @AssistedFactory
    interface Factory {
        fun create(runtimeArg: RouteDialogNoteFetchDetail): NoteFetchDetailViewModel
    }

    private val _state = MutableStateFlow<FetchNoteDetailState>(FetchNoteDetailState.Idle)
    val state = _state


    fun getDetail() {
        viewModelScope.launch {
            _state.value = FetchNoteDetailState.Loading
            delay(2000L)
            val result = useCase.getDetail(navKey.noteId)
            _state.value = FetchNoteDetailState.Success(result)
        }
    }

    fun clearState() {
        _state.value = FetchNoteDetailState.Idle
    }
}

sealed class FetchNoteDetailState {
    data object Idle : FetchNoteDetailState()
    data object Loading : FetchNoteDetailState()
    data class Success(val detail: NoteDetailModel) : FetchNoteDetailState()
}