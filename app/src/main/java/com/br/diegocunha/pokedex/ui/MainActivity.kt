package com.br.diegocunha.pokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.diegocunha.pokedex.ui.components.CardLoadingComponent
import com.br.diegocunha.pokedex.ui.components.InfiniteListHandler
import com.br.diegocunha.pokedex.ui.components.PlaceholderFullScreen
import com.br.diegocunha.pokedex.ui.home.HomeViewModel
import com.br.diegocunha.pokedex.ui.state.GetCrossfade
import com.br.diegocunha.pokedex.ui.state.GetStatus
import com.br.diegocunha.pokedex.ui.theme.PokeDexAppTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDexAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val viewModel = getViewModel<HomeViewModel>()
    val viewState by viewModel.stateFlow.collectAsState()

    GetCrossfade(state = viewState,
        initial = {
            PlaceholderFullScreen {
                CardLoadingComponent()
            }
        },
        failure = {

        },
        success = {})
    when (viewState.currentStatus()) {
        GetStatus.SUCCESS -> {
            val listState = rememberLazyListState()

            LazyColumn(
                state = listState
            ) {
                items(viewState.success.items) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), text = it.name
                    )
                }

                item {
                    if (viewState.success.paginationLoading) {
                        CircularProgressIndicator()
                    }
                }
            }

            InfiniteListHandler(listState = listState) {
                viewModel.loadMore()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokeDexAppTheme {
        Greeting("Android")
    }
}