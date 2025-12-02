package kh.sample.android.nav3.ui.nav_rout

import androidx.navigation3.runtime.NavKey
import kh.sample.android.nav3.model.NoteDetailModel
import kotlinx.serialization.Serializable

@Serializable
data class RouteNoteDetail(val detail: NoteDetailModel) : NavKey