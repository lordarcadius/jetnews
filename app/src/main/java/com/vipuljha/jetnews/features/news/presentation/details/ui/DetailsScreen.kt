package com.vipuljha.jetnews.features.news.presentation.details.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vipuljha.jetnews.R
import com.vipuljha.jetnews.core.theme.Dimens.articleImageHeight
import com.vipuljha.jetnews.core.theme.Dimens.mediumPadding1
import com.vipuljha.jetnews.features.news.domain.models.Article
import com.vipuljha.jetnews.features.news.presentation.details.ui.components.DetailsTopBar
import com.vipuljha.jetnews.features.news.presentation.details.states.DetailsEvent

@Composable
fun DetailsScreen(
    paddingValues: PaddingValues,
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 10.dp),
    ) {
        DetailsTopBar(
            onBrowseClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = mediumPadding1,
                end = mediumPadding1,
                top = mediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = article.urlToImage, contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(articleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(mediumPadding1))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(R.color.text_title)
                )
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.body)
                )
            }
        }
    }
}