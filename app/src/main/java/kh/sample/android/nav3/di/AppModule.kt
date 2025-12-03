package kh.sample.android.nav3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kh.sample.android.nav3.ui.Navigator
import kh.sample.android.nav3.ui.NoteSharedViewModel
import kh.sample.android.nav3.ui.ResultStore
import kh.sample.android.nav3.ui.nav_rout.RouteMainMenu

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    @ActivityRetainedScoped
    fun provideNavigator() : Navigator = Navigator(startDestination = RouteMainMenu)

    @Provides
    @ActivityRetainedScoped
    fun provideResultStore() : ResultStore = ResultStore()

    @Provides
    @ActivityRetainedScoped
    fun provideNoteSharedViewModel(): NoteSharedViewModel = NoteSharedViewModel()
}