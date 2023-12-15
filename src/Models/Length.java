package Models;

public enum Length {
    BRIEF("Brief", 50),
    SHORT("Short", 100),
    MEDIUM("Medium", 200),
    LONG("Long", 500),
    VERBOSE("Verbose", 1000);

    private final String displayName;
    private final int length;

    Length(String displayName, int length) {
        this.displayName = displayName;
        this.length = length;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLength() {
        return length;
    }

    public static String[] names() {
        Length[] states = values();
        String[] names = new String[states.length];

        for (int i = 0; i < states.length; i++) {
            names[i] = states[i].getDisplayName();
        }

        return names;
    }
}
