package com.vipuljha.jetnews.features.news.presentation.common

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.vipuljha.jetnews.R
import com.vipuljha.jetnews.core.theme.Dimens.mediumPadding1
import com.vipuljha.jetnews.features.news.domain.models.Article

@Composable
fun ArticlesList(
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(mediumPadding1),
    ) {
        items(count = articles.size) {
            val article = articles[it]
            ArticleCard(article = article, onClick = { onClick(article) })
        }
    }
}

@Composable
fun ArticlesList(
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(article = articles)
    if (handlePagingResult) {
        val titles by remember {
            derivedStateOf {
                if (articles.itemCount > 10) {
                    articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                        .joinToString(
                            prefix = "\uD83D\uDC49 ",
                            separator = "   \uD83D\uDC49 "
                        ) { it.title }
                } else {
                    ""
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding1),
        ) {
            item {
                Text(
                    text = titles,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    fontSize = 12.sp,
                    color = colorResource(R.color.placeholder)
                )
            }
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }
}

@Composable
private fun handlePagingResult(article: LazyPagingItems<Article>): Boolean {
    val loadState = article.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding1)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
                .shimmerEffect()
        )
        repeat(10) {
            ArticleCardShimmerEffect()
        }
    }
}