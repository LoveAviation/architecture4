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
import ru.gb.android.workshop4.domain.favorites.RemoveFavoriteUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class RemoveFavoriteUseCaseIntegrationTest {

    @Mock
    lateinit var favoritesDataSource: FavoritesDataSource

    private lateinit var favoritesRepository: FavoritesRepository
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        favoritesRepository = FavoritesRepository(favoritesDataSource, testDispatcher)
        removeFavoriteUseCase = RemoveFavoriteUseCase(favoritesRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `removeFavorite should call saveFavorite in favoritesDataSource`() = runTest(testDispatcher) {
        // Arrange
        val favId = "228"
        val favoriteEntity = FavoriteEntity(favId)

        // Act
        removeFavoriteUseCase.removeFavorite(favId)

        // Assert
        verify(favoritesDataSource).removeFavorite(eq(favoriteEntity))
    }
}