package kh.sample.android.nav3.ui

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kh.sample.android.nav3.ui.main_menu.MainMenu
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu

fun EntryProviderScope<NavKey>.featureMainMenu(navigator: Navigator) {
    entry<RouteMainMenu> {
        MainMenu { navKey ->
            navigator.goTo(navKey)
        }
    }

}