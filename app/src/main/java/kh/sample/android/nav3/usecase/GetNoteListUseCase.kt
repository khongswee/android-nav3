package kh.sample.android.nav3.usecase

import kh.sample.android.nav3.model.NoteModel
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor() {
    fun getNoteList(): List<NoteModel> {
        return List(100) {
            NoteModel(id = it, title = "Note $it", content = "Content $it")
        }
    }
}