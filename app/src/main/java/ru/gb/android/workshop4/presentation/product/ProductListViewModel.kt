package ru.gb.android.workshop4.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.gb.android.workshop4.data.product.ProductDataMapper
import ru.gb.android.workshop4.data.product.ProductLocalDataSource
import ru.gb.android.workshop4.data.product.ProductRemoteDataSource
import ru.gb.android.workshop4.domain.product.ProductDomainMapper
import ru.gb.android.workshop4.marketsample.R
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productStateFactory: ProductStateFactory,
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource,
    private val productDataMapper: ProductDataMapper,
    private val productDomainMapper: ProductDomainMapper,
) : ViewModel() {

    private val _state = MutableStateFlow(ProductsScreenState())
    val state: StateFlow<ProductsScreenState> = _state.asStateFlow()

    fun requestProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val products = productRemoteDataSource.getProducts()
            productLocalDataSource.saveProducts(
                products.map(productDataMapper::toEntity)
            )
        }

        productLocalDataSource.consumeProducts()
            .map { productEntities ->
                productEntities.map(productDomainMapper::fromEntity)
            }
            .map { products ->
                products.map { product -> productStateFactory.create(product) }
            }
            .onStart {
                _state.update { screenState -> screenState.copy(isLoading = true) }
            }
            .onEach { productListState ->
                _state.update { screenState ->
                    screenState.copy(
                        isLoading = false,
                        productListState = productListState,
                    )
                }
            }
            .catch {
                _state.update { screenState ->
                    screenState.copy(
                        hasError = true,
                        errorRes = R.string.error_wile_loading_data,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        requestProducts()
    }

    fun errorHasShown() {
        _state.update { screenState -> screenState.copy(hasError = false) }
    }
}
