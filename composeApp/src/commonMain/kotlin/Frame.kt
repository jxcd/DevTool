import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pages.JsonTool
import pages.TimeTool

@Composable
fun Frame() {
    val pages = listOf("JsonTool", "TimeTool", "Debug")
    Card {
        var page by remember { mutableStateOf(pages.first()) }

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                pages.forEach { pageName ->
                    Button(onClick = { page = pageName }) {
                        Text(pageName)
                    }
                }
            }

            when (page) {
                "JsonTool" -> JsonTool()
                "TimeTool" -> TimeTool()
                else -> Debug()
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