# GRE 词汇学习应用

这是一个专为帮助用户掌握 GRE 词汇而设计的 Android 应用程序，利用艾宾浩斯遗忘曲线进行高效的间隔重复复习。

## 功能特性

- **卡片式学习界面**：简洁直观的卡片 UI，用于单词学习。
- **智能滑动交互**：
  - **左滑（记得）**：标记单词为“记得”，并根据艾宾浩斯曲线安排下一次复习。
  - **右滑（忘记）**：标记单词为“忘记”，重置其进度，并将其添加到“生词本”中以便重点复习。
- **间隔重复系统**：自动计算每个单词的最佳复习时间，以最大化记忆保持率。
- **生词本**：一个专门的板块，用于复习你感到困难的单词（即右滑过的单词）。
- **进度追踪**：通过卡片翻转和动画提供视觉反馈。
- **离线支持**：所有数据使用 Room 数据库存储在本地。

## 技术栈

- **语言**：Kotlin
- **架构**：MVVM (Model-View-ViewModel)
- **Android Jetpack 组件**：
  - **Room**：用于数据持久化的本地数据库。
  - **ViewModel & LiveData**：UI 状态管理。
  - **RecyclerView**：高效的列表显示。
  - **ViewBinding**：更安全的视图交互。
- **并发**：Kotlin Coroutines & Flow。
- **动画**：用于卡片翻转的 ObjectAnimator 和用于滑动交互的自定义 ItemTouchHelper。

## 快速开始

### 前置要求

- Android Studio Iguana 或更新版本。
- JDK 17 或更新版本。
- Android SDK API Level 24 (Min SDK) 或更高。

### 安装步骤

1.  克隆仓库：
    ```bash
    git clone https://github.com/yourusername/WordsAndroid.git
    ```
2.  在 **Android Studio** 中打开项目。
3.  同步 Gradle 文件。
4.  在模拟器或真机上运行应用。

## 使用说明

1.  **主屏幕**：你会看到一叠单词卡片。
2.  **学习**：
    - 点击卡片翻转查看中文释义。
    - 如果你认识这个词，向 **左** 滑动。
    - 如果你不认识这个词，向 **右** 滑动。
3.  **复习**：
    - 点击右上角的菜单图标。
    - 选择 **生词本 (Vocabulary Book)** 查看需要复习的单词列表（即你向右滑动的单词）。

## 项目结构

```
com.example.wordsandroid
├── data/               # Room 实体, DAO, Repository, 和数据模型
├── ui/                 # 适配器和 UI 相关类
├── utils/              # 工具类 (例如：艾宾浩斯算法逻辑)
├── MainActivity.kt     # 主单词卡片屏幕
├── VocabularyBookActivity.kt # 生词本屏幕
├── MainViewModel.kt    # 用于管理应用数据的 ViewModel
└── WordsApplication.kt # Application 类
```

## 许可证

本项目采用 MIT 许可证 - 详情请参阅 [LICENSE](LICENSE) 文件。
