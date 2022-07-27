package ru.netology.mynmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.mynmedia.Post
import ru.netology.mynmedia.R
import ru.netology.mynmedia.activity.MainActivity
import java.math.BigDecimal

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет профессий  вававррапрапрапрапра",
        published = "20 июля 10:00",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        likedMy = false
    )

    private  val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun share() {
        post.shared++
    }

    override fun like() {
        post = post.copy(likedMy = !post.likedMy)

    //    setImageResource(
      //      if (post.likedMy)
      //          R.drawable.ic_liked_24 else R.drawable.ic_like_24
      //  )
        if (post.likedMy) {
            post.like++
       //     likeCount.text = logic(post.like)
        } else {
            post.like--
        //    likeCount.text = logic(post.like)
        }
        data.value = post
    }

}