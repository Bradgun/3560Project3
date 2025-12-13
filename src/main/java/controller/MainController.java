package controller;
import model.WritingMode;
import javax.swing.SwingWorker;
import service.APIService;

//Controls actions between the UI and the API service
public class MainController {
    private final APIService api = new APIService("gpt-4.1-mini");

    //Validates user input and forwards request to the API service
    public String generate(WritingMode mode, String input) throws Exception{
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input is null or empty - Please Enter Text.");
        }
        return api.generate(mode, input);
    }

    //text generation happens in background so it doesn't freeze UI
    public void generateInBackground(WritingMode mode, String input, java.util.function.Consumer<String> onSuccess, java.util.function.Consumer<String> onError) {
        new SwingWorker<String, Void>() {

            //API call in background
            @Override
            protected String doInBackground() throws Exception {
                return generate(mode, input);
            }

            //result once background thread is complete
            @Override
            protected void done() {
                try {
                    onSuccess.accept(get());
                }
                catch (Exception e) {
                    //for debugging
                    e.printStackTrace();
                    onError.accept(e.toString());
                }
            }
        }.execute();
    }
}
