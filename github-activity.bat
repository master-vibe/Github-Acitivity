@echo off

set "sourceDir=src"
set "outputDir=classes"
set "mainClass=github_activity"  **Replace with your main class name (without extension)**

setlocal enabledelayedexpansion

:: Check number of arguments
if "%~1"=="" (
    echo Error: No arguments provided.
    exit /b 1
)

:: Handle arguments
if "%~1"=="-r" (
    if "%~2"=="" (
        echo Error: No argument provided after -r.
        exit /b 1
    )
    set "additionalArgs=%~2"
    set res=true
) else (
    set "additionalArgs=%*"
)

:: Create the output directory if it doesn't exist
if not exist "%outputDir%" mkdir "%outputDir%"

:: Compile if needed (check for missing directory or specific class files)
if not exist "%outputDir%" set res=true
if not exist "%outputDir%\%mainClass%.class" set res=true
@REM if not exist "%outputDir%\TaskManager.class" set res=true
@REM if not exist "%outputDir%\Tasks.class" set res=true

if defined res (
    echo Initializing...
    javac -cp "src\json.jar" -d "%outputDir%" "!sourceDir!\%mainClass%.java" 
    if !errorlevel! neq 0 (
        echo Error: Compilation failed.
        exit /b 1
    )
)

:: Run the Java main class with additional arguments
java -cp "%outputDir%;src\json.jar;" "%mainClass%" %additionalArgs%
if !errorlevel! neq 0 (
    echo Error: Application execution failed.
    exit /b 1
)

endlocal
