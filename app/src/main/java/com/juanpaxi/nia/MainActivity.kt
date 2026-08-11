package com.juanpaxi.nia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.juanpaxi.nia.core.network.NiaNetworkDataSource
import com.juanpaxi.nia.ui.theme.NiATheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkDataSource: NiaNetworkDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                val topics = networkDataSource.getTopics()
                Log.d("GIST_TEST", "Success! Fetched ${topics.size} topics from Gist:")
                topics.forEach { topic ->
                    Log.d("GIST_TEST", "Topic: ${topic.name}")
                }
            } catch (e: Exception) {
                Log.e("GIST_TEST", "Error fetching Gist data", e)
            }
        }

        enableEdgeToEdge()
        setContent {
            NiATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}
