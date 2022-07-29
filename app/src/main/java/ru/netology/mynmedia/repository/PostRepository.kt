package ru.netology.mynmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.mynmedia.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeByID(id: Long)
    fun share()
}