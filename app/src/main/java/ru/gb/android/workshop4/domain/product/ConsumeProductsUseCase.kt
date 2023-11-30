package ru.gb.android.workshop4.domain.product

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ConsumeProductsUseCase @Inject constructor() {
    operator fun invoke(): Flow<List<Product>> {
        return flowOf()
    }
}
