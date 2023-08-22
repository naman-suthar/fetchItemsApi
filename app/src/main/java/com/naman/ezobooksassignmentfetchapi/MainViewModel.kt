package com.naman.ezobooksassignmentfetchapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naman.ezobooksassignmentfetchapi.model.Item
import com.naman.ezobooksassignmentfetchapi.repository.ItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = ItemsRepository()
    var data: List<Item> = emptyList()
    private var _uiState:MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllItems().also {

                if (it.status == "success"){
                    data = it.items
                    _uiState.value = UIState.Success
                }else {
                    _uiState.value = UIState.Loading
                }
            }

        }
    }
}

sealed class UIState(){
    object Loading: UIState()
    object Success : UIState()
}