package ru.gb.android.workshop4.domain.favorites

import ru.gb.android.workshop4.data.favorites.FavoriteEntity
import ru.gb.android.workshop4.data.favorites.FavoritesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoveFavoriteUseCase@Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {

    suspend fun removeFavorite(favId: String){
        favoritesRepository.removeFromFavorites(FavoriteEntity(favId))
    }

}