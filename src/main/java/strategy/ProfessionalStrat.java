package strategy;

public class ProfessionalStrat implements WritingStrategy {
    @Override
    public String process(String input) {
        return "Professional Mode\n" + input;
    }
}
