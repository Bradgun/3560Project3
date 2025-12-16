package model.domain;

import model.WritingMode;
import java.time.LocalDateTime;

public class SaveWritingSession {

    private final WritingMode writingMode;
    private final String inputText;
    private final String outputText;
    private final String dateAndTime;

    //Session will save the type of writing mode, input/output text, and the time the query was run
    public SaveWritingSession(WritingMode mode, String inputText, String outputText) {
        this.writingMode = mode;
        this.inputText = inputText;
        this.outputText = outputText;
        this.dateAndTime = LocalDateTime.now().toString();
    }

    public WritingMode getWritingMode() {
        return writingMode;
    }

    public String getInputText() {
        return inputText;
    }

    public String getOutputText() {
        return outputText;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return writingMode + " session from " + dateAndTime;
    }
}
