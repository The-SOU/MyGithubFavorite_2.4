package com.sou.mygithubfavorite.data.response

import com.sou.mygithubfavorite.data.entity.GithubRepoEntity

data class GithubRepoSearchResponse(
    val totalCount: Int,
    val items: List<GithubRepoEntity>
)
