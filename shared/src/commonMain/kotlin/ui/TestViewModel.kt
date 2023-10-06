package ui

import core.BaseViewModel
import data.Repository
import io.ktor.client.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestViewModel(
    private val repository: Repository = Repository(HttpClient())
) : BaseViewModel() {

    private val _test = MutableStateFlow("")
    val test = _test.asStateFlow()

    init {
        getTest()
    }

    private fun getTest() = viewModelScope.launch {
        _test.value = repository.get()
    }
}