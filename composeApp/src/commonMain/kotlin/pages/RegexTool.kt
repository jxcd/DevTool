package pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegexTool() {
    var text by remember { mutableStateOf("正则表达式可以应用于各种编程语言和文本处理工具中, 如 Kotlin、Java、JavaScript 等。") }
    var regexText by remember { mutableStateOf("\\w+") }
    var ignoreCase by remember { mutableStateOf(true) }

    var output by remember { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val modifierTextArea = Modifier.fillMaxWidth(0.9f)

            TextField(value = text,
                onValueChange = { text = it },
                modifier = modifierTextArea,
                label = { Text("测试文本") })
            TextField(
                value = regexText,
                onValueChange = { regexText = it },
                modifier = modifierTextArea,
                label = { Text("规则") })

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { output = parse(text, regexText, ignoreCase) }) {
                    Text("解析")
                }
                Checkbox(checked = ignoreCase, onCheckedChange = { ignoreCase = it })
                Text("忽略大小写")
            }

            TextField(
                value = output,
                onValueChange = {},
                modifier = modifierTextArea,
                label = { Text("输出") },
                readOnly = true
            )
        }

    }

}

fun parse(text: String, regexText: String, ignoreCase: Boolean): String {
    val regex = if (ignoreCase) Regex(regexText, RegexOption.IGNORE_CASE) else Regex(regexText)

    val isMatch = regex.matches(text)
    val containsMatch = regex.containsMatchIn(text)
    val matches = regex.findAll(text)
    var groups = ""

    matches.forEachIndexed { index, it -> groups += "$index . ${it.value}\n" }

    return """
完全匹配: $isMatch
部分匹配: $containsMatch

$groups
""".trimIndent()
}