package kh.sample.android.nav3.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import kh.sample.android.nav3.ui.Navigator
import kh.sample.android.nav3.ui.main_menu.MainMenu
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainMenuModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(navigator: Navigator): EntryProviderInstaller =
        {
            entry<RouteMainMenu> {
                MainMenu { navKey ->
                    navigator.goTo(navKey)
                }
            }
        }
}