package ru.gb.android.marketsample

import org.junit.Test

import org.mockito.Mockito.`when`
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.gb.android.workshop4.domain.product.Product
import ru.gb.android.workshop4.presentation.common.PriceFormatter
import ru.gb.android.workshop4.presentation.product.ProductStateFactory

class ProductStateFactoryTest {

    @Mock
    lateinit var priceFormatter: PriceFormatter // Мокируем зависимость

    private lateinit var productStateFactory: ProductStateFactory

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this) // Инициализация моков
        productStateFactory = ProductStateFactory(priceFormatter)
    }

    @Test
    fun `create should return ProductState with correct isFavorite value`() {
        val product = Product(
            id = "123",
            name = "Test Product",
            image = "test_image_url",
            price = 100.0,
            isFavorite = true
        )
        val formattedPrice = "$100.00"
        `when`(priceFormatter.format(product.price)).thenReturn(formattedPrice)

        val result = productStateFactory.create(product)

        assertEquals(product.isFavorite, result.isFavorite)
    }
}