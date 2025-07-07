# 🎬 BulkFileRename

<div align="center">

![Java](https://img.shields.io/badge/Java-24-orange?style=for-the-badge&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-GUI-blue?style=for-the-badge&logo=java)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)

*A JavaFX-based GUI application for bulk renaming media files with visual preview capabilities*

</div>

---

## ✨ Features

<div align="center">

| Feature | Description |
|---------|-------------|
| 🖼️ **Visual Media Preview** | View images and videos before renaming |
| 📁 **Bulk Rename** | Rename multiple files efficiently & quickly |
| 🎯 **Manual Control** | Gives you full control over each and every individual file |
| ✅ **Input Validation** | Prevents invalid filenames and special characters |
| 🎥 **Supported Formats** | Images (.gif, .jpg, .png, .jpeg) & Videos (.mp4) |
| 🔄 **Auto-Conversion** | Will automatically convert .mov files |
| 🗑️ **File Deletion** | Remove files that are no longer relevant |

</div>

## 🚀 Getting Started

### 📋 Prerequisites

```bash
☑️ Java SDK 24 or higher
☑️ JavaFX runtime
☑️ JUnit (developers)
```


### 🛠️ Installation

```shell script
# 1. Clone the repository
git clone https://github.com/yourusername/BulkFileRename.git

# 2. Open the project in your IDE
# 3. Add the following to your runner's VM options (supress unrelated warnings):
   #--enable-native-access=ALL-UNNAMED
   #--sun-misc-unsafe-memory-access=allow
# 4. Config your run configuration:
   #Build with the cmd line `clean javafx:run`
```


> 💡 **Note**: Plans to build an installer are in the works

## 🎮 Usage

<div align="center">

```
📂 Select → 👀 Preview → ✏️ Rename → ✅ Validate → 🔄 Change ↓ 🗑️ Delete (Optional)
```


</div>

1. **📂 Select Directory**: Choose a folder containing media files to rename
2. **👀 Preview**: View the current file in the media viewer
3. **✏️ Rename**: Enter a new filename in the text field
4. **✅ Validate**: The application will check for:
   - ❌ Empty names
   - ❌ File extensions (auto-handled)
   - ❌ Special characters
   - ❌ Problematic characters (that cause issues in file explorers)
   - ❌ Name length (6-70 characters)
5. **🔄 File Change**: Changes the file name in the directory selected
6. **🗑️ Delete**: If you decide the file is no longer relevant to your needs, deletion is possible

## 🏗️ Project Structure

```
📦 BulkFileRename
├── 🎮 MasterFrameController.java    # Main application controller
├── 📁 DirectoryBrowser.java         # Directory selection functionality
├── 🔧 ExtensionHandler.java         # File type detection and conversion
└── 🔄 DirectoryIterator.java        # File iteration utilities
```


## 🎯 Validation Rules

<div align="center">

| Rule | Status | Description |
|------|--------|-------------|
| 📝 **Name Length** | `6-70 chars` | Optimal filename length |
| 🚫 **Special Chars** | `Blocked` | Prevents file system issues |
| 📄 **Extensions** | `Auto-handled` | No need to include `.jpg`, `.mp4`, etc. |
| 💡 **Smart Feedback** | `Visual Cues` | Clear error notifications |

</div>

## 🔮 Future Enhancements

<div align="center">

| Feature | Description |
|---------|-------------|
| 🎨 **Pattern-Based Naming** | Custom naming patterns |
| ⚡ **Batch Processing** | Process multiple files simultaneously |
| 📱 **Additional Formats** | Expanded file format support |
| ↩️ **Undo/Redo** | Mistake-proof operations (specifically with the delete button) |
| 📦 **Installer** | Easy installation package |
| 🎭 **Visual Styles** | Enhanced UI themes |
| 🌙 **Dark Mode** | Easy on the eyes |
| ❓ **The Sky's The Limit** | Submit pull requests or issue for your ideas |

</div>


## 🤝 Contributing

I welcome contributions! Feel free to:

- 🐛 Report bugs
- 💡 Suggest features
- 🔧 Submit pull requests
- 📖 Improve documentation

## 📜 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">

**Made with ❤️ and JavaFX**

*Star ⭐ this repo if you found it helpful!*

</div>
```

