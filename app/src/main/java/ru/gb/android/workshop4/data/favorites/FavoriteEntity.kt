package ru.gb.android.workshop4.data.favorites

import kotlinx.serialization.Serializable

@Serializable
class FavoriteEntity (
    val id: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FavoriteEntity) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}