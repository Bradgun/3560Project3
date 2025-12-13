# Writing Assistant Project

## Set Up
1. Get API key from the OpenAI Website
2. Set the API key as an environment variable
   3. Top of Intellij, click run, edit configurations, edit environment variables, then add the name and the value of the key

## Features
- Creative writing mode
- Professional writing mode
- Academic writing mode
- Save/load not implemented
  - Due to time constraints, I decided to skip on implementing this feature

## Design Patterns
- Strategy pattern
  - drop down allows for multiple different types of writing styles quickly
- Factory pattern
  - Used to create and return the writing strategy based on the selected mode


## Demo: https://drive.google.com/file/d/1HIdN9UIzE19R3as890soTlXkyXOGiVke/view?usp=sharing