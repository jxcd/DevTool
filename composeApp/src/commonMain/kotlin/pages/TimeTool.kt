package pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cronutils.model.CronType
import com.cronutils.model.definition.CronDefinitionBuilder
import com.cronutils.model.time.ExecutionTime
import com.cronutils.parser.CronParser
import compose.InputText
import compose.SelectOptions
import util.TimeUtil
import java.time.LocalDateTime
import java.time.ZonedDateTime
import kotlin.jvm.optionals.getOrNull

/**
 * 转换 时间戳和时间
 */
@Composable
fun TimeTool() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TimestampAndDateTime()

        Divider(modifier = Modifier.height(2.dp).fillMaxWidth())

        Cron()
    }
}

@Composable
fun TimestampAndDateTime() {
    var timeText by remember { mutableStateOf(TimeUtil.formatDateTime(LocalDateTime.now())) }
    var timestampText by remember { mutableStateOf("0") }

    val setToNow = {
        val temp = LocalDateTime.now()
        timeText = TimeUtil.formatDateTime(temp)
        timestampText = TimeUtil.getLocalDateTimeMill(temp).toString()
    }

    LaunchedEffect(null) {
        setToNow()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        InputText(
            label = "时间戳",
            value = timestampText,
            onValueChange = { timestampText = it })

        val toLocalDateTime = {
            val temp = TimeUtil.parse(timestampText.toLongOrNull() ?: 0)
            timeText = TimeUtil.formatDateTime(temp)
        }
        Button(onClick = toLocalDateTime) { Text(" --> 日期") }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        InputText(label = "日期时间", value = timeText, onValueChange = { timeText = it })

        Button(onClick = {
            val temp = TimeUtil.parse(timeText)
            timestampText = TimeUtil.getLocalDateTimeMill(temp).toString()
        }) { Text(" --> 时间戳") }

        Button(onClick = setToNow, colors = ButtonDefaults.outlinedButtonColors()) {
            Text("现在")
        }

    }
}

@Composable
fun Cron() {
    val cronTypes = listOf("SPRING", "UNIX", "QUARTZ", "CRON4J", "SPRING53")
    var cronType by remember { mutableStateOf(cronTypes.first()) }
    var cronExpression by remember { mutableStateOf("0 * * * * ?") }
    var cronTimes by remember { mutableStateOf("5") }
    var cronOutput by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputText(
            label = "Cron表达式",
            value = cronExpression,
            onValueChange = { cronExpression = it })

        Button(onClick = {
            cronOutput = cronParse(cronExpression, cronTimes.toIntOrNull() ?: 5, cronType)
        }) {
            Text("解析")
        }

        SelectOptions(
            label = "类型",
            value = cronType,
            cronTypes,
            onValueChange = { cronType = it })

        OutlinedTextField(
            value = cronTimes, onValueChange = { cronTimes = it },
            modifier = Modifier.width(60.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    OutlinedTextField(
        value = cronOutput,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Cron表达式未来 $cronTimes 次执行时间") },
        readOnly = true,
        minLines = 5,
    )
}

fun cronParse(cronExpression: String, times: Int = 10, cronType: String): String {
    // 创建 Cron 解析器
    val cronParser =
        CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.valueOf(cronType)))

    // 解析 Cron 表达式
    val cron = cronParser.parse(cronExpression)

    // 创建 ExecutionTime 对象
    val executionTime = ExecutionTime.forCron(cron)

    // 获取当前时间
    var currentTime = ZonedDateTime.now()

    // 获取下一 5 次的执行时间
    var output = ""
    for (i in 1..times) {
        val nextExecutionTime = executionTime.nextExecution(currentTime).getOrNull()
        output += "#$i at: ${
            nextExecutionTime?.toLocalDateTime()?.let { TimeUtil.formatDateTime(it) }
        }\n"
        nextExecutionTime?.let { currentTime = it }
    }
    return output
}