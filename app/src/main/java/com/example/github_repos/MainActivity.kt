package com.example.github_repos
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GitHubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search_view)
        recyclerView = findViewById(R.id.recycler_view)
        adapter = GitHubRepoAdapter()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickListener { repo ->
            val intent = Intent(this, RepoDetailsActivity::class.java).apply {
                putExtra("name", repo.name)
                putExtra("url", repo.html_url)
                putExtra("description", repo.description)
                putExtra("language", repo.language)
            }
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { _, insets ->
            recyclerView.setPadding(0, 0, 0, insets.systemWindowInsetBottom)
            insets
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                query?.let { searchRepositories(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchRepositories(username: String) {
        GitHubClient.service.listRepos(username).enqueue(object : Callback<List<GitHubRepo>> {
            override fun onResponse(
                call: Call<List<GitHubRepo>>,
                response: Response<List<GitHubRepo>>
            ) {
                if (response.isSuccessful) {
                    adapter.setItems(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
