# simplebot-fhsing-task-script
## Introduction
This repository is based on the [simplebot-task-script-template](https://github.com/the-reminisce/simplebot-task-script-template) repository

## Detailed Description

This template is intended to provide a starting point for developing a script for the Simplebot API that utilizes the task system. The task system is a powerful tool that allows you to structure your script into smaller, more manageable tasks.

The main class of the script, `FisherScript`, sets up the script and initializes the task system. It also handles paint and mouse events. The paint event is used to display information about the script on the screen, such as its current status, runtime statistics, and other relevant information. The mouse event is used to handle any mouse interactions that the script may need, such as clicking on objects or interacting with the game world.

The `FisherConstants` class holds any constants that the script uses to define specific values or locations in the game world that the script needs to interact with. This can include things like the names of offensive prayers, specific coordinates, or other values that are used throughout the script. By defining these values as constants in a separate class, they can be easily reused and referenced throughout the script, making the code more maintainable and readable.

The `FisherSettings` class holds the settings for the script.

The `FisherState` class holds the state variables for the script. This includes information such as the current task, the time that the script started, and any other variables that need to be tracked throughout the execution of the script. The `FisherState` class also has a method for generating random numbers in a normal distribution, which can be useful for adding some randomness to your script.

The `FisherTask` class is a template for a task that can be added to the task system. It provides access to the script instance, settings, and state, allowing you to perform actions and make decisions based on the current state of the script.

The `FisherSpot` enum holds the different types of fishing spots that the script can use.

The `PaintHelper` class provides a customizable paint for the script. It can be modified to add custom lines, shapes, and other features to the script's display. This can be useful for displaying information about the script's progress or for debugging purposes.
