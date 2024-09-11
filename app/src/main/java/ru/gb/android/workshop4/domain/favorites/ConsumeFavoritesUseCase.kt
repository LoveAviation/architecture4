package ru.gb.android.workshop4.domain.favorites

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.gb.android.workshop4.data.favorites.FavoriteEntity
import ru.gb.android.workshop4.data.favorites.FavoritesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConsumeFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {

    fun consumeFavorites(): List<FavoriteEntity>{
        return runBlocking {
            favoritesRepository.consumeFavorites().first()
        }
    }

}