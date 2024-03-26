import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import json.JsonTool
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        JsonTool()
    }
}