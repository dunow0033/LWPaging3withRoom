package com.express.android.lwpaging3withroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.express.android.lwpaging3withroom.db.CharacterData
import com.express.android.lwpaging3withroom.db.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RoomRepository): ViewModel() {

    fun getAllRecords(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
        pagingSourceFactory = {repository.getAllRecords()}).flow.cachedIn(viewModelScope)
    }

    fun insertDummyRecords() {
        for(i in 1..500) {
            repository.insertRecord(CharacterData(0, "Dummy " + i))
        }
    }
}