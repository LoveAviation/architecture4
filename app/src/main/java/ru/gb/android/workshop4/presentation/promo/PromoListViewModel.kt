package ru.gb.android.workshop4.presentation.promo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PromoListViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(PromoScreenState())
    val state: StateFlow<PromoScreenState> = _state.asStateFlow()

    init {
        requestPromos()
    }

    private fun requestPromos() {

    }

    fun refresh() {
        requestPromos()
    }

    fun errorHasShown() {

    }
}
