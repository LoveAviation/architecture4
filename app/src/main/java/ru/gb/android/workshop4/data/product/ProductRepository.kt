package ru.gb.android.workshop4.data.product

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {
    fun consumeProducts(): Flow<List<ProductEntity>> {
        return flowOf()
    }
}
