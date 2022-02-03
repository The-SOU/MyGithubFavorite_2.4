package com.sou.mygithubfavorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sou.mygithubfavorite.data.database.DatabaseProvider
import com.sou.mygithubfavorite.data.entity.GithubOwner
import com.sou.mygithubfavorite.data.entity.GithubRepoEntity
import com.sou.mygithubfavorite.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val repositoryDao by lazy { DatabaseProvider.provideDB(applicationContext).repositoryDao() }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        launch {
            addMockData()
            val githubRepositories = loadGithubRepositories()
            withContext(coroutineContext) {
                Log.e("repositories", githubRepositories.toString())
            }
        }
    }

    private fun initViews() = with(binding) {
        searchButton.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, SearchActivity::class.java)
            )

        }
    }

    private suspend fun addMockData() = withContext(Dispatchers.IO) {
        val mockData = (0 until 10).map {
            GithubRepoEntity(
                name = "repo $it",
                fullName = "name $it",
                owner = GithubOwner(
                    "login",
                    "avatarUrl"
                ),
                description = null,
                language = null,
                updatedAt = Calendar.getInstance().toString(),
                stargazersCount = it
            )
        }

        repositoryDao.insertAll(mockData)
    }

    private suspend fun loadGithubRepositories() = withContext(Dispatchers.IO) {
        val repositories = repositoryDao.getHistory()
        return@withContext repositories
    }
}