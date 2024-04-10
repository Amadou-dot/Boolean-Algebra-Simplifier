import org.junit.jupiter.api.Test;

import com.simplifier.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testDistribute() {
        // Test Distribution Law 1
        assertEquals("AB+AC", Main.Distribute("A(B+C)"), "Distribution Law 1 failed for A(B+C)");

        // Test Distribution Law 2
        assertEquals("AB+AC", Main.Distribute("ABC"), "Distribution Law 3 failed for ABC");

        // Test Distribution Law 3
        assertEquals("BA+BD+BB+CB", Main.Distribute("B(AD+B)+CB"), "Distribution Law 3 failed for ABC");
    }

    @Test
    public void testOneVariable() {
        // Test case 1
        assertEquals("A", Main.Idempotent("AA"), "Idempotent failed for AA");

        // Test case 2
        assertEquals("AB", Main.Idempotent("AAB"), "Idempotent failed for AAB");

        // Test case 3
        assertEquals("ABC", Main.Idempotent("ABC"), "Idempotent failed for ABC");

        assertEquals("B(AD+B)+CB", Main.Idempotent("B(AD+BB)+CB"), "Idempotent failed for ABC");
    }

    @Test
    public void testAbsorb() {

        // Test Absorption Law 1
        assertEquals("A", Main.Absorb("A+AB"), "Absorb failed for A+AB");
        // Test Absorption Law 2
        assertEquals("C", Main.Absorb("C+CD"), "Absorb failed for C+CD");
        // Test Absorption Law 3
        assertEquals("A", Main.Absorb("AB+A"), "Absorb failed for C+CD");
    }

    @Test
    public void testSimplifyExpression() {
        // Test case 1
        assertEquals("B", Main.SimplifyExpression("B(AD+BB)+CB"), "SimplifyExpression failed for B(AD+BB)+CB");

        // Test case 2
        assertEquals("A", Main.SimplifyExpression("A+AB"), "SimplifyExpression failed for A+AB");

        // Test case 3
        assertEquals("C", Main.SimplifyExpression("C+CD"), "SimplifyExpression failed for C+CD");

        // Test case 3
        assertEquals("A", Main.SimplifyExpression("BA+(AA+CA)"), "SimplifyExpression failed for C+CD");
    }
}