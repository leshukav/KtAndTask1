package ru.netology.mynmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.mynmedia.Post
import ru.netology.mynmedia.R
import ru.netology.mynmedia.databinding.CardPostBinding
import ru.netology.mynmedia.repository.PostRepositoryInMemoryImpl

interface OnInteractionListener {
    fun onLike(post: Post)
    fun onEdit(post: Post)
    fun onRemove(post: Post)
    fun onShare(post: Post)
}

class PostsAdapter(
    private val interactionListener: OnInteractionListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val interactionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            publish.text = post.published
            content.text = post.content
            likeCount.text = PostRepositoryInMemoryImpl().logic(post.like)
            shareCount.text = PostRepositoryInMemoryImpl().logic(post.shared)
            viewCount.text = post.id.toString()
            liked.setImageResource(if (post.likedMy) R.drawable.ic_liked_24 else R.drawable.ic_like_24)

            share.setOnClickListener {
                interactionListener.onShare(post)
            }
            liked.setOnClickListener {
                interactionListener.onLike(post)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.option_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                interactionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                interactionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }

                    }
                }.show()
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}