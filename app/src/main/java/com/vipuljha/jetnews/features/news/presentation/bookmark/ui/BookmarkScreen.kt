package com.vipuljha.jetnews.features.news.presentation.bookmark.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vipuljha.jetnews.R
import com.vipuljha.jetnews.core.theme.Dimens.mediumPadding1
import com.vipuljha.jetnews.features.news.domain.models.Article
import com.vipuljha.jetnews.features.news.presentation.bookmark.state.BookmarkState
import com.vipuljha.jetnews.features.news.presentation.common.ArticlesList
import com.vipuljha.jetnews.features.news.presentation.common.HeaderBar

@Composable
fun BookmarkScreen(
    innerPadding: PaddingValues,
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(innerPadding),
    ) {
        HeaderBar(title = stringResource(R.string.bookmarks))
        Spacer(modifier = Modifier.height(mediumPadding1))
        ArticlesList(articles = state.articles, onClick = { navigateToDetails(it) })
    }

}