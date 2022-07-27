package ru.netology.mynmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.mynmedia.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}