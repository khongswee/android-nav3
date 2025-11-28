package kh.sample.android.nav3.ui

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kh.sample.android.nav3.ui.nav_rout.RouteSetting
import kh.sample.android.nav3.ui.setting.SettingScreen

fun EntryProviderScope<NavKey>.featureSetting() {
    entry<RouteSetting> {
        SettingScreen()
    }
}