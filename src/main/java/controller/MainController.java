package controller;
import model.WritingMode;
import model.AppModel;
import javax.swing.SwingWorker;
import service.APIService;
import java.util.concurrent.CancellationException;

//Controls actions between the UI and the API service
public class MainController {
    private final APIService api = new APIService("gpt-4.1-mini");

    //AppModel is used for Observer pattern
    private final AppModel model;

    //controller receives model for updates
    public MainController(AppModel model) {
        this.model = model;
    }
    //reference to background task so it could be cancelled
    private SwingWorker<String, Void> worker;

    //Validates user input and forwards request to the API service
    private String generate(WritingMode mode, String input) throws Exception{
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input is null or empty - Please Enter Text.");
        }
        return api.generate(mode, input);
    }

    //text generation happens in background so it doesn't freeze UI
    public void generateInBackground(WritingMode mode, String input) {
        model.setErrorText(null);
        model.setBusy(true);
        model.setStatusText("Running");

        worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                return generate(mode, input);
            }

            @Override
            protected void done() {
                try {
                    if (isCancelled()) {
                        model.setStatusText("Cancelled");
                        return;
                    }

                    //successful response
                    String result = get();
                    model.setOutputText(result);
                    model.setStatusText("Done");
                }
                catch (CancellationException e) {
                    //cancelled task while it was running
                    model.setStatusText("Cancelled");
                }
                catch (Exception e) {
                    //error is reported to the model
                    model.setErrorText(e.getMessage());
                    model.setStatusText("Error");
                }
                finally {
                    //UI models go back up when busy = false
                    model.setBusy(false);
                }
            }
        };
        worker.execute();
    }

    //let user stop a running request
    public void cancel() {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);
        }
    }
}
