package ru.netology.mynmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
          like = 0,
          liked = false
          )
      with(binding) {
          author.text = post.author
          publish.text = post.published
          content.text = post.content
          likeCount.text = like.toString()
          if (post.liked) {
              like?.setImageResource(R.drawable.ic_liked_24)
          }
          like?.setOnClickListener{
              post.liked = !post.liked
              like.setImageResource(
                  if (post.liked)
                      R.drawable.ic_liked_24 else R.drawable.ic_like_24
              )
              if (post.liked) post.like++  else post.like--
              likeCount.text = post.like.toString()

              }
          }
      }

    }
