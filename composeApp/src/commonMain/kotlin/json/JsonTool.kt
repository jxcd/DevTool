package json

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

@Composable
fun JsonTool() {
    var input by remember { mutableStateOf("{}") }
    var output by remember { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(0.95f).padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val areaModifier = Modifier.fillMaxHeight().weight(0.4f)

            OutlinedTextField(
                input, { input = it }, label = { Text("输入") },
                modifier = areaModifier
            )

            Column(
                modifier = Modifier.weight(0.1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button({ output = pretty(input) }) {
                    Text("美化")
                }


                Button({ output = compress(input) }) {
                    Text("简化")
                }

                val clipboardManager = LocalClipboardManager.current
                val copyToClipboard = {
                    clipboardManager.setText(AnnotatedString(output))

                }

                Button(copyToClipboard) {
                    Text("复制")
                }
            }

            OutlinedTextField(
                output, {}, label = { Text("输出") },
                modifier = areaModifier
            )

        }
    }
}

private val mapper = ObjectMapper()
private val mapperWithPretty = ObjectMapper()
    .enable(SerializationFeature.INDENT_OUTPUT)
private val mapperWithCompress = ObjectMapper()
    .disable(SerializationFeature.INDENT_OUTPUT)
    .enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)


private fun pretty(json: String): String {
    return try {
        mapperWithPretty.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(json))
    } catch (e: Exception) {
        e.message ?: "Error"
    }
}

private fun compress(json: String): String {
    return try {
        mapperWithCompress.writeValueAsString(mapper.readTree(json))
    } catch (e: Exception) {
        e.message ?: "Error"
    }
}