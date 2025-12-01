package kh.sample.android.nav3.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteSharedViewModel @Inject constructor() : ViewModel() {

    private var stampTime: Long = 0L

    fun saveTime() {
        stampTime = System.currentTimeMillis()
    }

    fun getStampTime(): Long {
        return stampTime
    }
}


