package service;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import model.WritingMode;
import strategy.Factory;
import strategy.WritingStrategy;

import java.time.Duration;

//deals with requests and responses
public class APIService {
    private final OpenAIClient client;
    private final String model;

    //initialize the client using API key from env var
    public APIService(String model) {

        //System prevents query from running nonstop
        OpenAIClient baseClient = OpenAIOkHttpClient.builder().fromEnv().timeout(Duration.ofSeconds(60)).build();
        this.model = model;
        this.client = baseClient;
    }

    public String generate(WritingMode mode, String input) throws Exception {
        //select the writing mode based on user input on the comboBox
        WritingStrategy strategy = Factory.create(mode);

        //build request parameters for API
        ResponseCreateParams params = ResponseCreateParams.builder().model(model).input(strategy.process(input)).build();

        Response response = client.responses().create(params);

        //extract generated text from API
        return response.output().get(0).asMessage().content().get(0).asOutputText().text();
    }
}