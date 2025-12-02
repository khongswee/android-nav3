package kh.sample.android.nav3.ui.nav_rout

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class RouteDialogNoteFetchDetail(val noteId: Int) : NavKey
