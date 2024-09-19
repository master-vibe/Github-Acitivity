# GitHub Activity Tool

A command-line tool to fetch and display GitHub user activities using the GitHub API. This tool retrieves recent events from a specified GitHub user's account and displays them in a readable format.

## Features

- Fetches and displays GitHub events for a given user.
- Supports event types such as `CreateEvent`, `PushEvent`, and `DeleteEvent`,etc.
- Provides details on repository name and event timestamps.

## Prerequisites

- Java 11 or higher
- `json.jar` library for JSON parsing (included in the `src` directory)
- **Command-Line Interface (CLI):** Use a terminal (macOS/Linux) or Command Prompt (Windows).

## Getting Started

Clone the repository to your local machine:

```bash
git clone https://github.com/master-vibe/Github-Acitivity.git
```

## Setup

1. Clone the repository or download the source code to your local machine.
2. Ensure that you have the batch script (`github-activity.bat` for Windows) or shell script (`github-activity.sh` for macOS/Linux) included in the project directory.

## Usage

The provided batch or shell script will handle both the compilation and execution of the Java program. To use the Task CLI, follow these steps based on your operating system:

### Note

You need to navigate to the root folder of the project where the batch (`github-activity.bat`) or shell script (`github-activity.sh`) is located. Alternatively, you can add the script to your system's PATH environment variable to run it from any location.

### For Windows:

Open Command Prompt, navigate to the project root directory, and run:

  1. Run the Script:
      ```bash
      github-activity <options> [username]
      ```

### For MACOS:

Open Terminal, navigate to the project root directory, and run:

  1. Change Permissions:
      ```bash
      chmod +x github-activity.sh
      ```
  2. Run the Script:
      ```bash
      ./github-activity.sh <options> [username]
