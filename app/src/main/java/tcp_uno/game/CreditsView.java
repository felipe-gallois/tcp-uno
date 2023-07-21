package tcp_uno.game;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class CreditsView implements View {
    private static final List<String> NAMES = Collections.unmodifiableList(
        Arrays.asList(
       "Diego Cardoso Nunes",
            "Felipe Gallois",
            "Matheus Rodrigues Fonseca",
            "Vinicius Jose Fritzen"));

    public List<String> getNames() {
        return NAMES;
    }

    public AppState display() {
        return AppState.CREDITS;
    }
}
