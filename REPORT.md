# Project Report

## Challenges I faced
Problem: Initially, Swing was not returning once I clicked generate and would continue to run without generating a prompt.
Solution: I added the API key as an environmental variable so that the system can access that key without it being hardcoded in my files.
Lesson: I learned how to work around with environment variables to make sure that I'm the only one that has access to this key, and that we should never hardcode these values into Github for potentially anyone else to see and come across. If someone does, then many problems can follow such as getting your data stolen, or getting your tokens used up.

Problem: After clicking generate, the UI would freeze up for several moments.
Solution: I moved the text generation to a background thread using the generateInBackground method in the MainController.java. The API call runs inside doInBackground() and the UI updates once the task is complete.
Lesson: I learned that we should not keep long or heavy tasks on the UI thread, because that could hurt user experience, whether people aren't sure of how long to wait or even close the app before it finished processing.

Problem: The app would sometimes crash or get stuck in the generating screen when the text was empty.
Solution: Input validation was added in the generate method to make sure empty inputs aren't allowed. I also made sure my try/catch statements would catch exceptions and give me messages as to why code wouldn't work through error messages.
Lesson: I learned proper use of error handling especially when scaled with larger projects dealing with AI.

Problem: When I tested the save/load features, I realized that my sessions would always return an error when saving, even though I had no sessions saved but a file called sessions.json was created. I went back to the loadSessions() method and figured out that the method is only checking one case. The method only checked if the file existed, and because it did, the next step was to try and parse an empty file, which led to the error.
Solution: Instead, the method was updated to handle an empty file before attempting to parse them, by adding if (file.length() == 0).
Lesson: I learned that files can exist in invalid states, which is empty or even corrupted, and I need to make sure my programming accounts for those possibilities. I learned files aren't always going to be valid just because they exist in the directory.

## Design Pattern Justifications
- Strategy Pattern: Creative, Professional, and Academic strategies are all separated. This keeps the prompt generation logic clean while being able to switch back and forth between writing styles. This also helps if the project were to be expanded in the future. Implementing other writing styles would be easy to implement and would not change the main code.
- Factory Pattern: Factory is used to make sure the correct writing strategy was returned based on what the user chose in the dropdown.
- Observer Pattern: The UI listens for changes, whether it's output, status, or errors. The UI changes accordingly when it receives these changes/updates. This is very helpful with the text generation happening in the background, so that once it finishes, the UI receives that change and updates the main UI for the user to see.

## OOP Four Pillars
- Encapsulation: Encapsulation was used through making fields private in classes such as AppModel.java. These fields can only be accessed through getters and setters which makes sure that users can't directly access variables.
- Inheritance: Inheritance was used by having the different writing strategies implement the WritingStrategy interface. It lets the writing strategies have a common structure while providing slightly different roles.
- Abstraction: Using interfaces such as WritingStrategy and SessionRepository helps us with abstraction, as it hides implementation details that just define what the program can do instead of presenting how it does it.
- Polymorphism: Polymorphism was used through how the different writing strategies call process() at runtime. The stories and suggestions to the AI change depending on which writing mode is chosen. This is done without the code having to change.

## AI Usage
One use of AI was in MainController.java. At first, I logged on to the wrong account, so I generated a key that didn't end up having any tokens. That also was part of the reasons why I had some problems stated above. I asked AI to help me figure out a way to send me an error message as to why specifically the chatbot wasn't returning anything. It gave me the catch statement that I needed, which was e.printStackTrace(), and then at the bottom, I got my error message stating that I had no tokens left, which indicated that I generated the key on the wrong account.
Another one of the problems I did not state above was the fact that I wasn't sure how to get the response of the chatbot on to the UI. So in this case, I did use AI and asked for a code excerpt which was on line 28 in APIService.java, due to me being stuck on that for a long time.
Another use of AI was through my tests. I asked AI how the tests in Factory.java could be better, and it responded by saying that what I currently had, which was 
    assertNotNull(Factory.create(WritingMode.creative));
returns a non-null object. That meant it would pass even if Factory returned the wrong strategy. AI suggested to implement assertInstanceOf which checks to see if the correct strategy was returned. Then I updated my tests to match that suggestion.
Another issue that I had was that I needed help understanding how I would extract the AI generated text. I asked the AI to help me create a method chain for accessing the text. They suggested this line of code, return response.output().get(0).asMessage().content().get(0).asOutputText().text();, which I implemented, and confirmed it was working properly.
