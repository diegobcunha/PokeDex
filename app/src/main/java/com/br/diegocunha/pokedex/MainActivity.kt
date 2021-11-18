package com.br.diegocunha.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.br.diegocunha.pokedex.ui.home.HomeViewModel
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

    when(viewState.currentStatus()) {
        GetStatus.SUCCESS -> {
            viewState.success.forEach {
                Log.e("PokemonHome", it.name)
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