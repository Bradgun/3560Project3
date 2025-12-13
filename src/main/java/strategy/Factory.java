package strategy;
import model.WritingMode;

public class Factory {
    public static WritingStrategy create(WritingMode mode) {
        return switch (mode) {
            case creative -> new CreativeStrat();
            case professional ->  new ProfessionalStrat();
            case academic ->  new AcademicStrat();
        };
    }
}
