package ru.netology.mynmedia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.mynmedia.Post
import ru.netology.mynmedia.R
import ru.netology.mynmedia.databinding.CardPostBinding
import ru.netology.mynmedia.repository.PostRepositoryInMemoryImpl

typealias OnLikeListener  = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter (private val onLikeListener: OnLikeListener,
                    private val onShareListener: OnShareListener
): RecyclerView.Adapter<PostViewHolder>(){
    var list = emptyList<Post>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size

}
 class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {  author.text = post.author
            publish.text = post.published
            content.text = post.content
            likeCount.text = PostRepositoryInMemoryImpl().logic(post.like)
            shareCount.text = PostRepositoryInMemoryImpl().logic(post.shared)
            viewCount.text = post.id.toString()
            liked.setImageResource(if (post.likedMy) R.drawable.ic_liked_24 else R.drawable.ic_like_24)

            share.setOnClickListener {
                onShareListener(post)
            }
            liked.setOnClickListener {
                onLikeListener(post)
            }

        }
    }
}