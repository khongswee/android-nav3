package kh.sample.android.nav3.ui.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NoteSharedViewModel : ViewModel() {

    var count by mutableIntStateOf(0)
        private set

    fun count(){
        count++
    }

}