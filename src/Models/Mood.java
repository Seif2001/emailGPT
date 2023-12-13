package Models;

public enum Mood {
    AGGRESSIVE,
    FORGIVING,
    LOVING,
    HATE,
    FORMAL,
    CASUAL,
    FRIENDLY;
    public static String[] names() {
        Mood[] states = values();
        String[] names = new String[states.length];

        for (int i = 0; i < states.length; i++) {
            names[i] = states[i].name();
        }

        return names;
    }
}
