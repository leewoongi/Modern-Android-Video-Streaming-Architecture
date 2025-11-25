package com.woon.modernandroidvideostreamingarchitecture.domain.favorite

import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.model.Favorite
import com.woon.modernandroidvideostreamingarchitecture.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<Favorite>> {
        return favoriteRepository.get()
    }
}
