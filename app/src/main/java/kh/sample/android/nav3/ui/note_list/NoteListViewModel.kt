package kh.sample.android.nav3.ui.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.sample.android.nav3.model.NoteModel
import kh.sample.android.nav3.usecase.GetNoteListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val useCase: GetNoteListUseCase) : ViewModel() {
    private val _noteListFlow = MutableStateFlow<List<NoteModel>>(emptyList())
    val noteListFlow = _noteListFlow

    private val _refreshing = MutableStateFlow(false)
    val refreshing = _refreshing


    fun loadList(isRefresh: Boolean) {
        viewModelScope.launch {
            _refreshing.value = isRefresh
            if (isRefresh){
                delay(2000L)
            }
            _noteListFlow.value = useCase.getNoteList()
            _refreshing.value = false
        }

    }
}