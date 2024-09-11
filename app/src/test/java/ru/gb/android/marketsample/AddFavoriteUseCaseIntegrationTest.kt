package ru.gb.android.marketsample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.eq
import ru.gb.android.workshop4.data.favorites.FavoriteEntity
import ru.gb.android.workshop4.data.favorites.FavoritesDataSource
import ru.gb.android.workshop4.data.favorites.FavoritesRepository
import ru.gb.android.workshop4.domain.favorites.AddFavoriteUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class AddFavoriteUseCaseIntegrationTest {

    @Mock
    lateinit var favoritesDataSource: FavoritesDataSource

    private lateinit var favoritesRepository: FavoritesRepository
    private lateinit var addFavoriteUseCase: AddFavoriteUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        favoritesRepository = FavoritesRepository(favoritesDataSource, testDispatcher)
        addFavoriteUseCase = AddFavoriteUseCase(favoritesRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addFavorite should call saveFavorite in favoritesDataSource`() = runTest(testDispatcher) {
        // Arrange
        val favId = "52"
        val favoriteEntity = FavoriteEntity(favId)

        // Act
        addFavoriteUseCase.addFavorite(favId)

        // Assert
        verify(favoritesDataSource).saveFavorite(eq(favoriteEntity))
    }
}