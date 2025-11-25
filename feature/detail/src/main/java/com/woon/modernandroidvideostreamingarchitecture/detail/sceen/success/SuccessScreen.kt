package com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.woon.modernandroidvideostreamingarchitecture.core.R
import com.woon.modernandroidvideostreamingarchitecture.detail.model.MediaUiModel
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.component.MediaInfoCard
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.component.StatsCard
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.component.TagsSection
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.component.UserInfoCard
import com.woon.modernandroidvideostreamingarchitecture.detail.sceen.success.layout.MediaHeaderScreen
import com.woon.modernandroidvideostreamingarchitecture.domain.media.model.MediaType

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SuccessScreen(
    modifier: Modifier,
    media: MediaUiModel,
    scrollState: ScrollState,
) {
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
    ) {
        MediaHeaderScreen(
            modifier = Modifier.fillMaxWidth(),
            content = {
                when (media.type) {
                    MediaType.IMAGE -> {
                        GlideImage(
                            model = media.mediaUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            loading = placeholder(R.drawable.placeholder_image),
                            failure = placeholder(R.drawable.error_image)
                        )
                    }

                    MediaType.VIDEO -> {
                        // TODO: Video player implementation
                    }
                }
            },
            isToggled = media.isFavorite,
            onIconClick = {},
            onFavoriteClick = {}
        )

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatsCard(
                views = media.views,
                likes = media.likes,
                downloads = media.downloads
            )

            MediaInfoCard(
                title = "${media.type.value} #${media.id}",
                subtitle = when (media.type) {
                    MediaType.VIDEO -> {
                        "Duration: ${media.duration}s"
                    }
                    else -> {
                        "${media.width} × ${media.height}"
                    }
                }
            )

            TagsSection(tags = media.tags)

            UserInfoCard(
                userImageUrl = media.userImageUrl,
                userName = media.userName,
                userRole = "Photographer"
            )
        }
    }
}