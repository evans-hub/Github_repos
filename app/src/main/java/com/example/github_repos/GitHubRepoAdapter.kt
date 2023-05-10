package com.example.github_repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GitHubRepoAdapter : RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>() {
    private var items: List<GitHubRepo> = emptyList()
    private var onItemClickListener: ((GitHubRepo) -> Unit)? = null
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_github_repo, parent, false)
    return ViewHolder(view)
}
    fun setOnItemClickListener(listener: (GitHubRepo) -> Unit) {
        onItemClickListener = listener
    }
override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = items[position]
    holder.bind(item)
    holder.itemView.setOnClickListener {
        onItemClickListener?.invoke(item)
    }
}

override fun getItemCount(): Int {
    return items.size
}

fun setItems(items: List<GitHubRepo>) {
    this.items = items
    notifyDataSetChanged()
}

inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView = view.findViewById(R.id.name_text_view)
    private val urlTextView: TextView = view.findViewById(R.id.url_text_view)

    fun bind(item: GitHubRepo) {
        nameTextView.text = item.name
        urlTextView.text = item.html_url
    }
}

}