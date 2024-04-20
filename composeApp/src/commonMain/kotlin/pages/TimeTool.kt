package pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import compose.InputNumber
import compose.InputText
import util.TimeUtil
import java.time.LocalDateTime

/**
 * 转换 时间戳和时间
 */
@Composable
fun TimeTool() {
    var timeText by remember { mutableStateOf(TimeUtil.formatDateTime(LocalDateTime.now())) }
    var timestampText by remember { mutableStateOf("0") }

    Card(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputText(
                label = "时间戳",
                value = timestampText,
                onValueChange = { timestampText = it })

            InputText(label = "日期时间", value = timeText, onValueChange = { timeText = it })

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    val temp = TimeUtil.parse(timeText)
                    timestampText = TimeUtil.getLocalDateTimeMill(temp).toString()
                }) { Text("日期转时间戳") }

                Button(onClick = {
                    val temp = TimeUtil.parse(timestampText.toLongOrNull() ?: 0)
                    timeText = TimeUtil.formatDateTime(temp)
                }) { Text("时间戳转日期") }
            }

        }
    }


}