package ru.netology.mynmedia.activity


import android.content.Intent
import ru.netology.mynmedia.adapter.PostsAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import ru.netology.mynmedia.databinding.ActivityMainBinding
import ru.netology.mynmedia.viewmodel.PostViewModel
import androidx.activity.viewModels
import androidx.core.view.isVisible
import ru.netology.mynmedia.Post
import ru.netology.mynmedia.activity.NewPostActivity
import ru.netology.mynmedia.R.string.*
import ru.netology.mynmedia.adapter.OnInteractionListener
import ru.netology.mynmedia.util.AndroidUtils
import androidx.activity.ComponentActivity
import androidx.activity.result.launch

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()

    val newPostContract = registerForActivityResult(NewPostActivity.Contract) { text ->
        if (text.isNullOrBlank()) {
            Toast.makeText(
                this@MainActivity,
                "Content can't be empty",
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.changeContent(text.toString())
        viewModel.save()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = PostsAdapter(object : OnInteractionListener {


            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val intentChooser = Intent.createChooser(intent, getString(chooser_share_post))
                startActivity(intentChooser)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.content.setOnClickListener {
            binding.save.visibility = VISIBLE
        }

        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                binding.save.visibility = VISIBLE
                binding.group.visibility = VISIBLE
                binding.editContent.text = it.content
                binding.content.requestFocus()
                binding.content.setText(it.content)
            }
        }

        binding.cancelButton.setOnClickListener {
            with(binding.content) {
                setText("")
                viewModel.cancelEdit()
                binding.save.visibility = INVISIBLE
                binding.group.visibility = INVISIBLE
                binding.group.visibility = View.GONE
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        binding.content.setOnFocusChangeListener { _, hasFocus ->
            binding.save.isVisible = hasFocus
        }

        binding.edd.setOnClickListener{
            newPostContract.launch()
        }

        binding.save.setOnClickListener {

   //         with(binding.content) {
    //            if (text.isNullOrBlank()) {
    //                Toast.makeText(
    //                    this@MainActivity,
    //                    "Content can't be empty",
     //                   Toast.LENGTH_SHORT
     //               ).show()
     //               return@setOnClickListener
      //          }
      //          viewModel.changeContent(text.toString())
      //          viewModel.save()
      //          setText("")
      //          binding.save.visibility = INVISIBLE
     //           binding.group.visibility = INVISIBLE
     //           binding.group.visibility = View.GONE
      //          clearFocus()
      //          AndroidUtils.hideKeyboard(this)
            }
        }

    }

