package com.example.github_repos

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RepoDetailsActivity : AppCompatActivity() {
    companion object {
        const val REPO_NAME_EXTRA = "name"
        const val REPO_URL_EXTRA = "url"
        const val REPO_DESCRIPTION_EXTRA = "description"
        const val REPO_LANGUAGE_EXTRA = "language"
    }

    private lateinit var repoNameTextView: TextView
    private lateinit var repoUrlTextView: TextView
    private lateinit var repoDescriptionTextView: TextView
    private lateinit var repoLanguageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repoNameTextView = findViewById(R.id.repo_name_text_view)
        repoUrlTextView = findViewById(R.id.repo_url_text_view)
        repoDescriptionTextView = findViewById(R.id.repo_description_text_view)
        repoLanguageTextView = findViewById(R.id.repo_language_text_view)

        val repoName = intent.getStringExtra(REPO_NAME_EXTRA)
        val repoUrl = intent.getStringExtra(REPO_URL_EXTRA)
        val repoDescription = intent.getStringExtra(REPO_DESCRIPTION_EXTRA)
        val repoLanguage = intent.getStringExtra(REPO_LANGUAGE_EXTRA)

        repoNameTextView.text = "Repository Name"+repoName
        repoUrlTextView.text = repoUrl
        repoDescriptionTextView.text = repoDescription
        repoLanguageTextView.text = repoLanguage
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
