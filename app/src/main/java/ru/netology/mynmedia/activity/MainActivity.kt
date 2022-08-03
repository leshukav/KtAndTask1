package ru.netology.mynmedia.activity


import ru.netology.mynmedia.adapter.PostsAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.mynmedia.databinding.ActivityMainBinding
import ru.netology.mynmedia.viewmodel.PostViewModel
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {

      val viewModel: PostViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostsAdapter( {
            viewModel.likeById(it.id)},
            {viewModel.shareById(it.id)
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)


        }

    }
}
