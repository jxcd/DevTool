import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pages.JsonTool
import pages.RegexTool
import pages.TimeTool

const val mainPage = "MainPage"
val pages = listOf(mainPage, "JsonTool", "TimeTool", "RegexTool", "Debug")


@Composable
fun Frame() {
    var page by remember { mutableStateOf(mainPage) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(page) },
                navigationIcon = {
                    IconButton(onClick = { page = mainPage }, enabled = page != mainPage) {
                        Icon(
                            imageVector =
                            if (page == mainPage) Icons.AutoMirrored.Filled.List
                            else Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            when (page) {
                "MainPage" -> MainPage { page = it }
                "JsonTool" -> JsonTool()
                "TimeTool" -> TimeTool()
                "RegexTool" -> RegexTool()
                else -> Debug()
            }
        }
    }
}

@Composable
fun MainPage(setPage: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        pages.filter { it != mainPage }.forEach { pageName ->
            item {
                OutlinedButton(
                    onClick = { setPage(pageName) },
                    modifier = Modifier.width(24.dp).padding(8.dp)
                ) {
                    Text(pageName)
                }
            }
        }
    }
}

@Composable
fun Debug() {

    Card {
        Text("debug page...")
    }

}