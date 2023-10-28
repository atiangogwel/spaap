import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.spaapp.model.domain.SpaStaff;

public class SpaStaffTest {
    private SpaStaff spaStaff;

    @Before
    public void setUp() {
        spaStaff = new SpaStaff("John Doe", "Massage Therapist");
    }

    @Test
    public void testGetStaffName() {
        assertEquals("John Doe", spaStaff.getStaffName());
    }

    @Test
    public void testGetStaffRole() {
        assertEquals("Massage Therapist", spaStaff.getStaffRole());
    }

    @Test
    public void testToString() {
        assertEquals("SpaStaff: John Doe", spaStaff.toString());
    }
}
