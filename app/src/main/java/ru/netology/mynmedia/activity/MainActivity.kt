package ru.netology.mynmedia.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.mynmedia.R
import ru.netology.mynmedia.databinding.ActivityMainBinding
import ru.netology.mynmedia.viewmodel.PostViewModel
import androidx.activity.viewModels
import ru.netology.mynmedia.Post
import ru.netology.mynmedia.repository.PostRepositoryInMemoryImpl

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
                likeCount.text = PostRepositoryInMemoryImpl().logic(post.like)
                shareCount.text = PostRepositoryInMemoryImpl().logic(post.shared)
                viewCount.text = post.id.toString()
                if (post.likedMy) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            }
        }

        binding.liked.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()
        }

    }


}
