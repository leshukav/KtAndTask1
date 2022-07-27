package ru.netology.mynmedia.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import  ru.netology.mynmedia.repository.PostRepositoryInMemoryImpl
import ru.netology.mynmedia.R
import ru.netology.mynmedia.databinding.ActivityMainBinding
import ru.netology.mynmedia.viewmodel.PostViewModel
import java.math.BigDecimal

import ru.netology.mynmedia.Post

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                publish.text = post.published
                content.text = post.content
                likeCount.text = logic(post.like)
                shareCount.text = logic(post.shared)
                viewCount.text = post.id.toString()
                if (post.likedMy) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            }
        }

            binding.liked.setOnClickListener {
                viewModel.like()
                binding.likeCount.text = logic(post.like)
            }
            binding.share.setOnClickListener {
                viewModel.share()
                binding.shareCount.text = logic(post.shared)

            }

        }


fun logic(count: Int): String {
    return when (count) {
        in 1000..9999 -> {
            ((count / 1000.0).toBigDecimal().setScale(1, BigDecimal.ROUND_DOWN).toString() + "K")
        }
        in 10_000..999_999 -> {
            ((count / 1000.0).toBigDecimal().setScale(0, BigDecimal.ROUND_DOWN).toString() + "K")
        }
        in 1_000_000..10_000_000 -> {
            (count / 1_000_000.0).toBigDecimal().setScale(1, BigDecimal.ROUND_DOWN).toString() + "M"
        }
        else -> {
            count.toString()
        }
    }
}

}
