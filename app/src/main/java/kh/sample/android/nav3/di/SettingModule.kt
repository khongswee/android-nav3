package kh.sample.android.nav3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import kh.sample.android.nav3.ui.Navigator
import kh.sample.android.nav3.ui.nav_rout.RouteSetting
import kh.sample.android.nav3.ui.setting.SettingScreen

@Module
@InstallIn(ActivityRetainedComponent::class)
object SettingModule {

    @IntoSet
    @Provides
    fun provideEntryProviderInstaller(navigator: Navigator): EntryProviderInstaller =
        {
            entry<RouteSetting> {
                SettingScreen()
            }
        }
}