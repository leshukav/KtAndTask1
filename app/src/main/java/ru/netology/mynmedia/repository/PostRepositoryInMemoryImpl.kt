package ru.netology.mynmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.mynmedia.Post
import ru.netology.mynmedia.R
import ru.netology.mynmedia.activity.MainActivity
import java.math.BigDecimal

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
        id = 1,
        author = "Нетология. Университет интернет профессий  вававррапрапрапрапра",
        published = "21 июля 10:00",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        likedMy = false
    ),
        Post(
            id = 2,
            author = "Нетология. Университет интернет профессий  вававррапрапрапрапра",
            published = "22 июля 11:00",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedMy = false
        ),
        Post(
            id = 3,
            author = "Нетология. Университет интернет профессий  вававррапрапрапрапра",
            published = "23 июля 11:00",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedMy = false
        ),
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeByID(id: Long) {
        posts = posts.map {
            if (it.id != id) it.copy(like = it.like - 1) else it.copy(like = it.like + 1, likedMy = !it.likedMy)
        }
  //        if (!post.likedMy) {
   //           post = post.copy(like = post.like + 1, likedMy = !post.likedMy)
    //      } else {
    //          post = post.copy(like = post.like - 1, likedMy = !post.likedMy)
    //      }
        data.value = posts
    }

    override fun share() {
      //  post = post.copy(shared = post.shared + 1)
      //  data.value = post
    }

    fun logic(count: Int): String {
        return when (count) {
            in 1000..9999 -> {
                ((count / 1000.0).toBigDecimal().setScale(1, BigDecimal.ROUND_DOWN)
                    .toString() + "K")
            }
            in 10_000..999_999 -> {
                ((count / 1000.0).toBigDecimal().setScale(0, BigDecimal.ROUND_DOWN)
                    .toString() + "K")
            }
            in 1_000_000..10_000_000 -> {
                (count / 1_000_000.0).toBigDecimal().setScale(0, BigDecimal.ROUND_DOWN)
                    .toString() + "M"
            }
            else -> {
                count.toString()
            }
        }
    }
}