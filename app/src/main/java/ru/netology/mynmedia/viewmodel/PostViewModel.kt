package ru.netology.mynmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.mynmedia.repository.PostRepository
import ru.netology.mynmedia.repository.PostRepositoryInMemoryImpl


class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}