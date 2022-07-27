package ru.netology.mynmedia

import ru.netology.mynmedia.repository.PostRepositoryInMemoryImpl
import ru.netology.mynmedia.activity.MainActivity

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Int = 17599,
    var shared: Int = 10500,
    val likedMy: Boolean
)
