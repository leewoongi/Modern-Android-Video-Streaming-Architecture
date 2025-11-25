package com.woon.modernandroidvideostreamingarchitecture.detail.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.woon.modernandroidvideostreamingarchitecture.core.ext.findActivity
import com.woon.modernandroidvideostreamingarchitecture.detail.DetailViewModel
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent

@Composable
fun detailViewModel(
    id: Long,
    type: String
): DetailViewModel {

    val viewModelStoreOwner = remember {
        object : ViewModelStoreOwner {
            override val viewModelStore: ViewModelStore = ViewModelStore()
        }
    }

    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current.findActivity(),
        DetailViewModelFactoryProvider::class.java
    ).detailViewModelFactory()

    val viewModel: DetailViewModel = viewModel(
        viewModelStoreOwner = viewModelStoreOwner,
        factory = provideDetailViewModelFactory(
            assistedFactory = factory,
            id = id,
            type = type
        )
    )

    return viewModel
}

@AssistedFactory
interface Factory {
    fun create(id: Long, type: String): DetailViewModel
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface DetailViewModelFactoryProvider {
    fun detailViewModelFactory(): Factory
}

fun provideDetailViewModelFactory(
    assistedFactory: Factory,
    id: Long,
    type: String
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
        return assistedFactory.create(
            id = id,
            type = type
        ) as VM
    }
}