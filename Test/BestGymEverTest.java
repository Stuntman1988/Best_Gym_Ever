import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BestGymEverTest {

    BestGymEver testGym = new BestGymEver();


    @Test
    void readSsnTest() {
        String test = "8906138493, Ida Idylle";
        assert (testGym.readSsn(test).equals("8906138493"));
        assert !(testGym.readSsn(test).equals("9902149834"));
        assert !(testGym.readSsn(test).equals("Ida Idylle"));
    }

    @Test
    void readNameTest() {
        String test = "5711121234, Hilmer Heur";
        assert (testGym.readName(test).equals("Hilmer Heur"));
        assert !(testGym.readName(test).equals("Jicky Juul"));
        assert !(testGym.readName(test).equals("5711121234"));
    }

    @Test
    void activeYearCardTest() {
        LocalDate dateTestActive = LocalDate.now().minusMonths(9);
        System.out.println(dateTestActive);
        LocalDate dateTestExpired = LocalDate.now().minusMonths(14);
        System.out.println(dateTestExpired);
        assert (testGym.activeYearCard(String.valueOf(dateTestActive)) == true);
        assert !(testGym.activeYearCard(String.valueOf(dateTestActive)) == false);
        assert (testGym.activeYearCard(String.valueOf(dateTestExpired)) == false);
        assert !(testGym.activeYearCard(String.valueOf(dateTestExpired)) == true);
    }
}
