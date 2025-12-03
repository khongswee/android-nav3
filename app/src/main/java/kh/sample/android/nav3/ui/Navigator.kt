package kh.sample.android.nav3.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import dagger.hilt.android.scopes.ActivityRetainedScoped

@ActivityRetainedScoped
class Navigator(startDestination: NavKey) {
    val backStack: SnapshotStateList<NavKey> = mutableStateListOf(startDestination)

    fun goTo(destination: NavKey) {
        backStack.add(destination)
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }

    fun replace(destination: NavKey) {
        if (backStack.isNotEmpty()) {
            backStack.removeLastOrNull()
        }
        backStack.add(destination)
    }

    fun goBackTo(target: NavKey) {
        while (backStack.isNotEmpty() && backStack.last() != target) {
            backStack.removeLastOrNull()
        }
    }
}