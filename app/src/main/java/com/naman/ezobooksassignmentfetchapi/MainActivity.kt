package com.naman.ezobooksassignmentfetchapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.naman.ezobooksassignmentfetchapi.model.Item
import com.naman.ezobooksassignmentfetchapi.ui.theme.EzoBooksAssignmentFetchApiTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzoBooksAssignmentFetchApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Scaffold(topBar = {
                       TopAppBar(
                           modifier = Modifier.padding(start = 24.dp),
                           title = {
                               Text(text = "Shrine", modifier = Modifier.padding(start = 20.dp))
                           },
                           navigationIcon = {
                               Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu")
                           }
                       )
                    },
                    content = { padding ->
                        GridViewItems(viewModel = viewModel, modifier = Modifier.padding(padding))
                    })
                }
            }
        }
    }
}

@Composable
fun GridViewItems(viewModel: MainViewModel, modifier: Modifier){
    val itemsList = viewModel.uiState.collectAsState()
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        when(itemsList.value){
             UIState.Loading -> {
                CircularProgressIndicator()
            }
            UIState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(6.dp)
                ){
                   items(viewModel.data) {
                        ItemCard(item = it)
                   }
                }
            }

        }

    }
}

@Composable
fun ItemCard(item: Item){
    Card(modifier = Modifier
        .padding(6.dp)
        .heightIn(min = 200.dp)

    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp)) {
            AsyncImage(model = item.url, contentDescription = item.itemName, modifier = Modifier
                .fillMaxWidth()
                .height(100.dp), contentScale = ContentScale.FillWidth)
            Text(
                text = item.itemName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = "$${item.itemPrice}",
                style =MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 8.dp)
            )
        }
    }
}