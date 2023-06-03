package com.malik.rentindacademy.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.malik.rentindacademy.data.ClassRepository
import com.malik.rentindacademy.ui.ClassViewModel
import com.malik.rentindacademy.ui.ViewModelFactory
import com.malik.rentindacademy.ui.components.ClassCard
import com.malik.rentindacademy.ui.components.SearchBar
import com.malik.rentindacademy.ui.theme.RentindAcademyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ClassViewModel = viewModel(factory = ViewModelFactory(ClassRepository())),
    navigateToDetail: (Int) -> Unit,
) {
    val classData by viewModel.classes.collectAsState()
    val query by viewModel.query

    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 18.dp)
    ) {
        SearchBar(
            modifier = Modifier
                .padding(bottom = 16.dp),
            query = query,
            onQueryChange = viewModel::search,
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            classData.forEach { (_, classes) ->
                items(classes, key = { it.id }) { cls ->
                    ClassCard(
                        id = cls.id,
                        classImage = cls.image,
                        className = cls.name,
                        modifier = Modifier
                            .animateItemPlacement(
                                tween(durationMillis = 100)
                            ),
                        navigateToDetail = navigateToDetail
                    )
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_3A, showBackground = true)
@Composable
fun HomeScreenPreview() {
    RentindAcademyTheme {
        HomeScreen(
            navigateToDetail = {}
        )
    }
}