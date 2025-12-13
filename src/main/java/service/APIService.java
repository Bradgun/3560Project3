package service;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import model.WritingMode;
import strategy.Factory;
import strategy.WritingStrategy;

public class APIService {
    private final OpenAIClient client;
    private final String model;

    public APIService(String model) {
        this.client = OpenAIOkHttpClient.fromEnv();
        this.model = model;
    }

    public String generate(WritingMode mode, String input) throws Exception {
        WritingStrategy strategy = Factory.create(mode);

        ResponseCreateParams params = ResponseCreateParams.builder()
                .model(model)
                .input(strategy.process(input))
                .build();

        Response response = client.responses().create(params);
        return response.output().get(0).asMessage().content().get(0).asOutputText().text();
    }
}