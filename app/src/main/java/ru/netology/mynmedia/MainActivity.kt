package ru.netology.mynmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.mynmedia.databinding.ActivityMainBinding
import java.math.BigDecimal.ROUND_DOWN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  setContentView(R.layout.activity_main)
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет профессий  вававррапрапрапрапра",
            published = "20 июля 10:00",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            liked = false
        )
        with(binding) {
            author.text = post.author
            publish.text = post.published
            content.text = post.content
            likeCount.text = logic(post.like)
            shareCount.text = logic(post.shared)
            viewCount.text = post.id.toString()
            if (post.liked) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            like?.setOnClickListener {
                post.liked = !post.liked
                like.setImageResource(
                    if (post.liked)
                        R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.liked) {
                    post.like++
                    likeCount.text = logic(post.like)
                } else {
                    post.like--
                    likeCount.text = logic(post.like)
                }
            }
            share?.setOnClickListener {
                post.shared++
                binding.shareCount.text = logic(post.shared)

            }

        }

    }

    fun logic(count: Int): String {
        return when (count) {
            in 1000..999999 -> {
                ((count / 1000.0).toBigDecimal().setScale(1, ROUND_DOWN).toString() + "K")
            }
            in 1000000..10000000 -> {
                (count / 1_000_000.0).toBigDecimal().setScale(1, ROUND_DOWN).toString() + "M"
            }
            else -> {
                count.toString()
            }
        }
    }

}
