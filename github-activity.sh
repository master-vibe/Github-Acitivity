#!/bin/bash

sourceDir="src"
outputDir="classes"
mainClass="github_activity"  # Replace with your main class name (without extension)

# Initialize the res variable to false
res=false

# Check if the -r flag is provided
for arg in "$@"; do
    if [ "$arg" == "-r" ]; then
        res=true
        shift  # Remove -r from the arguments list
        break
    fi
done

# Capture all additional arguments (after removing -r if present)
additionalArgs="$*"

# Create the output directory if it doesn't exist
if [ ! -d "$outputDir" ]; then
    mkdir "$outputDir"
fi

# Compile if needed (check for missing directory or specific class files)
if [ "$res" = true ] || [ ! -f "$outputDir/github_activity.class" ]; then
    echo "Initializing..."
    javac -cp "$sourceDir\json.jar" -d "$outputDir" "$sourceDir\github_activity.java"

    if [ $? -ne 0 ]; then
        echo "Error: Compilation failed."
        exit 1
    fi
fi

# Run the Java main class with additional arguments
java -cp "$outputDir;$sourceDir\json.jar;" "$mainClass" $additionalArgs
if [ $? -ne 0 ]; then
    echo "Error: Application execution failed."
    exit 1
fi
