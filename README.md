# Simplebot Fishing Task Script

## Introduction

This repository provides a simple fishing task script for the Simplebot API. The script is designed to use the task system to break the task of fishing into smaller, more manageable tasks(based on the [simplebot-task-script-template](https://github.com/the-reminisce/simplebot-task-script-template) repository).

## Overview

The script is centered around the `FisherScript` class, which is responsible for initializing the task system and handling paint and mouse events. The `FisherConstants` class holds any constants that the script uses to define specific values or locations in the game world that the script needs to interact with. The `FisherSettings` class holds the settings for the script. The `FisherState` class holds the state variables for the script. This includes information such as the current task, the time that the script started, and any other variables that need to be tracked throughout the execution of the script. The `FisherTask` class is a template for a task that can be added to the task system. It provides access to the script instance, settings, and state, allowing you to perform actions and make decisions based on the current state of the script. The `FishingSpot` enum holds the different types of fishing spots that the script can use.

## Classes

### `FisherScript`

The main class of the script that sets up the script and initializes the task system. It handles paint and mouse events. The paint event is used to display information about the script on the screen, such as its current status, runtime statistics, and other relevant information. The mouse event is used to handle any mouse interactions that the script may need, such as clicking on objects or interacting with the game world.

### `FisherConstants`

Holds any constants that the script uses to define specific values or locations in the game world that the script needs to interact with. This can include things like the names of fishing tools, specific coordinates, or other values that are used throughout the script.

### `FisherSettings`

Holds the settings for the script. These include whether to use power fishing or progressive fishing, and the fishing spot to use.

### `FisherState`

Holds the state variables for the script. This includes information such as the current task, the time that the script started, and any other variables that need to be tracked throughout the execution of the script. This class also has a method for generating random numbers in a normal distribution, which can be useful for adding some randomness to your script.

### `FisherTask`

A template for a task that can be added to the task system. It provides access to the script instance, settings, and state, allowing you to perform actions and make decisions based on the current state of the script.

### `FishingSpot`

An enum that holds the different types of fishing spots that the script can use.

## Conclusion

The Simplebot Fishing Task Script provides a solid foundation for building a fishing script using the Simplebot API. The script is designed to be easily extendable, making it a great starting point for those new to scripting in Simplebot. With the script's task system, constants, and state management, you can easily build on the existing code to create a powerful fishing script.
