package com.vipuljha.jetnews.features.news.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.vipuljha.jetnews.R
import com.vipuljha.jetnews.core.navgraph.DetailsRoute
import com.vipuljha.jetnews.core.navgraph.SearchRoute
import com.vipuljha.jetnews.core.theme.Dimens.mediumPadding1
import com.vipuljha.jetnews.core.theme.LogoRed
import com.vipuljha.jetnews.features.news.domain.models.Article
import com.vipuljha.jetnews.features.news.presentation.common.ArticlesList
import com.vipuljha.jetnews.features.news.presentation.common.SearchBar

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(R.string.app_name),
                style = TextStyle(fontSize = 26.sp),
                fontWeight = FontWeight.Medium,
                color = LogoRed
            )
        }
        Spacer(modifier = Modifier.height(mediumPadding1))
        SearchBar(
            text = "",
            readOnly = true,
            onClick = {},
            onValueChange = {},
            onSearch = {
                navigateToSearch()
            },
        )
        Spacer(modifier = Modifier.height(mediumPadding1))
        ArticlesList(
            articles = articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}