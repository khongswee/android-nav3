package kh.sample.android.nav3.usecase

import jakarta.inject.Inject
import kh.sample.android.nav3.model.NoteDetailModel

class GetNoteDetailUseCase @Inject constructor() {

    fun getDetail(id: Int): NoteDetailModel {
        return when {
            id % 2 == 0 -> {
                NoteDetailModel(id, "Great!")
            }

            id % 3 == 0 -> {
                NoteDetailModel(id, "Good!")
            }

            else -> {
                NoteDetailModel(id, "Normal!")
            }
        }
    }

}