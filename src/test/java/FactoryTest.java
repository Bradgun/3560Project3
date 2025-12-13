import model.WritingMode;
import org.junit.jupiter.api.Test;
import strategy.Factory;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {
    @Test
    void creativeStratReturn() {
        assertNotNull(Factory.create(WritingMode.creative));
    }

    @Test
    void professionalStratReturn() {
        assertNotNull(Factory.create(WritingMode.professional));
    }

    @Test
    void academicStratReturn() {
        assertNotNull(Factory.create(WritingMode.academic));
    }
}
