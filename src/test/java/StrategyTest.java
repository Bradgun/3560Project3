import org.junit.jupiter.api.Test;
import strategy.AcademicStrat;
import strategy.CreativeStrat;
import strategy.ProfessionalStrat;
import strategy.WritingStrategy;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    @Test
    void creativeTexts() {
        WritingStrategy s = new CreativeStrat();
        assertTrue(s.process("Yes").contains("Yes"));
    }

    @Test
    void professionalTexts() {
        WritingStrategy s = new ProfessionalStrat();
        assertTrue(s.process("Yes").contains("Yes"));
    }

    @Test
    void academicTexts() {
        WritingStrategy s = new AcademicStrat();
        assertTrue(s.process("Yes").contains("Yes"));
    }

    @Test
    void creativeOutputFalse() {
        WritingStrategy s = new CreativeStrat();
        assertFalse(s.process("Yes").isBlank());
    }

    @Test
    void professionalOutputFalse() {
        WritingStrategy s = new ProfessionalStrat();
        assertFalse(s.process("Yes").isBlank());
    }

    @Test
    void academicOutputFalse() {
        WritingStrategy s = new AcademicStrat();
        assertFalse(s.process("Yes").isBlank());
    }
}
