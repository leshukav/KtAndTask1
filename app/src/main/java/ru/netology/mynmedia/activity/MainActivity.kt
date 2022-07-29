package ru.netology.mynmedia.activity


import PostsAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.mynmedia.R
import ru.netology.mynmedia.databinding.ActivityMainBinding
import ru.netology.mynmedia.viewmodel.PostViewModel
import androidx.activity.viewModels
import ru.netology.mynmedia.databinding.CardPostBinding
import ru.netology.mynmedia.repository.PostRepositoryInMemoryImpl

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostsAdapter{
            viewModel.likeById(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts


        }

    }
}
