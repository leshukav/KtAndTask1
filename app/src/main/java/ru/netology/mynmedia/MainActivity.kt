package ru.netology.mynmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import ru.netology.mynmedia.databinding.ActivityMainBinding

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
                if (post.liked) post.like++ else post.like--
                likeCount.text = logic(post.like)      //post.like.toString()

            }
            share?.setOnClickListener {
                post.shared++
                shareCount.text = logic(post.shared) //post.shared.toString()

            }

        }
 setContentView(binding.like)
        binding.apply {

        }
    }

    fun logic(clik: Int): String {
        return when (clik) {
            in 0..999 -> clik.toString()
            in 1000..1099 -> "1K"
            in 1100..1199 -> "1.1K"
            in 1200..1299 -> "1.2K"
            in 1300..1399 -> "1.3K"
            in 1400..1499 -> "1.4"
            in 1500..1599 -> "1.5"
            in 1600..1699 -> "1.6"
            in 1700..1799 -> "1.7"
            in 1800..1899 -> "1.8"
            in 1900..1999 -> "1.9"
            else -> "и так далее если я правильно сделал эту логику?"
        }
    }

}
