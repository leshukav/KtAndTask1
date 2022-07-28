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

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun share() {
        post = post.copy(shared = post.shared + 1)
        data.value = post
    }

    override fun like() {

        if (!post.likedMy) {
            post = post.copy(like = post.like + 1, likedMy = !post.likedMy)
            R.drawable.ic_liked_24

        } else {
            post = post.copy(like = post.like - 1, likedMy = !post.likedMy)
            R.drawable.ic_like_24

        }
        data.value = post
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
                (count / 1_000_000.0).toBigDecimal().setScale(1, BigDecimal.ROUND_DOWN)
                    .toString() + "M"
            }
            else -> {
                count.toString()
            }
        }
    }
}