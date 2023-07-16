package tcp_uno;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
    @Test
    public void endOk() {
        assertEquals(0, 0);
    }

    @Test
    public void endNotOk() {
        assertEquals(0, -1);
    }
}
