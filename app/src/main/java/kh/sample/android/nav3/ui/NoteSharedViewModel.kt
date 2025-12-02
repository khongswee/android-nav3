package kh.sample.android.nav3.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.sample.android.nav3.model.NoteDetailModel
import javax.inject.Inject

@HiltViewModel
class NoteSharedViewModel @Inject constructor() : ViewModel() {

    private var stampTime: Long = 0L
    var masterDetail: NoteDetailModel? = null
        private set

    fun saveTime() {
        stampTime = System.currentTimeMillis()
    }

    fun getStampTime(): Long {
        return stampTime
    }

    fun saveMasterDetail(detail: NoteDetailModel) {
        masterDetail = detail
    }

}


