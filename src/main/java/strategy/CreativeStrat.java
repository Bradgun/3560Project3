package strategy;

public class CreativeStrat implements WritingStrategy {
    @Override
    public String process(String input) {
        return "Creative Mode\n" + input;
    }
}
