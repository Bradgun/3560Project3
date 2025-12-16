import model.WritingMode;
import org.junit.jupiter.api.Test;
import strategy.Factory;
import strategy.WritingStrategy;
import strategy.CreativeStrat;
import strategy.ProfessionalStrat;
import strategy.AcademicStrat;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {
    @Test
    void creativeStratReturn() {
        WritingStrategy s = Factory.create(WritingMode.creative);
        assertNotNull(s);
        assertInstanceOf(CreativeStrat.class, s);
    }

    @Test
    void professionalStratReturn() {
        WritingStrategy s = Factory.create(WritingMode.creative);
        assertNotNull(s);
        assertInstanceOf(ProfessionalStrat.class, s);
    }

    @Test
    void academicStratReturn() {
        WritingStrategy s = Factory.create(WritingMode.creative);
        assertNotNull(s);
        assertInstanceOf(AcademicStrat.class, s);
    }
}
