package kh.sample.android.nav3.ui

import androidx.navigation3.runtime.NavKey

class Navigator(
    private val onPush: (NavKey) -> Unit,
    private val onPop: () -> Unit,
) {

    fun navigate(key: NavKey) {
        onPush(key)
    }

    fun goBack() {
        onPop()
    }
}