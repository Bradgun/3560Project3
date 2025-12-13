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

## AI Usage
One use of AI was in MainController.java. At first, I logged on to the wrong account, so I generated a key that didn't end up having any tokens. That also was part of the reasons why I had some problems stated above. I asked AI to help me figure out a way to send me an error message as to why specifically the chatbot wasn't returning anything. It gave me the catch statement that I needed, which was e.printStackTrace(), and then at the bottom, I got my error message stating that I had no tokens left, which indicated that I generated the key on the wrong account.
Another one of the problems I did not state above was the fact that I wasn't sure how to get the response of the chatbot on to the UI. So in this case, I did use AI and asked for a code excerpt which was on line 28 in APIService.java, due to me being stuck on that for a long time.

## Time Spent: 30 hours