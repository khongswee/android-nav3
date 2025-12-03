package kh.sample.android.nav3.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

typealias EntryProviderInstaller = EntryProviderScope<NavKey>.() -> Unit