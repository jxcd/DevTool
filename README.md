
# DevTool

## 工具和环境 Tools and Environment

1. AndroidStudio
2. [graalvm 21](https://www.graalvm.org/downloads/#)

## 介绍 Describe

一个使用 Kotlin Multiplatform 开发跨平台GUI的开发者工具, 包含常用的开发辅助功能.

A developer tool for developing cross-platform GUIs using Kotlin Multiplatform, including commonly used development assistance functions.

## 功能 Function

* [X] JsonTool, json工具, 压缩, 美化等
* [X] TimeTool, 时间工具, 时间戳转换, 格式化等
* [X] RegexTool, 正则工具, 正则表达式的匹配和结果展示
* [ ] ... 


* [X] JsonTool, json tool, compression, beautification, etc.
* [X] TimeTool, time tool, timestamp conversion, formatting, etc.
* [X] RegexTool, regular expression tool, regular expression matching and result display
* [ ] ...


----------------------------------

This is a Kotlin Multiplatform project targeting Android, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
