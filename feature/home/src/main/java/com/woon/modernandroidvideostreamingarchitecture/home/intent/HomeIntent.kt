package com.woon.modernandroidvideostreamingarchitecture.home.intent

sealed class HomeIntent {
    data class Search(val query: String) : HomeIntent()
}