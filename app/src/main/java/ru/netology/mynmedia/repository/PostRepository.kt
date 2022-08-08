package ru.netology.mynmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.mynmedia.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    //   fun editById(id: Long)
    fun save(post: Post)
}