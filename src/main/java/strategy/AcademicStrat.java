package strategy;

public class AcademicStrat implements WritingStrategy {
    @Override
    public String process(String input) {
        return "Academic Mode\n" + input;
    }
}
