package kh.sample.android.nav3.ui.note_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.sample.android.nav3.model.NoteModel
import kh.sample.android.nav3.usecase.GetNoteListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val useCase: GetNoteListUseCase) : ViewModel() {
    private val _noteListFlow = MutableStateFlow<List<NoteModel>>(emptyList())
    val noteListFlow = _noteListFlow

    fun loadList() {
        _noteListFlow.value = useCase.getNoteList()
    }
}