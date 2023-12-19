//TAREK KASSAB - 900213491
//Seifeldin Elshabshiri - 900202310
package Models;

public enum Mood {
    AGGRESSIVE("Aggressive"),
    FORGIVING("Forgiving"),
    LOVING("Loving"),
    HATE("Hate"),
    FORMAL("Formal"),
    CASUAL("Casual"),
    FRIENDLY("Friendly");
    private final String displayName;

    Mood(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String[] names() {
        Mood[] states = values();
        String[] names = new String[states.length];

        for (int i = 0; i < states.length; i++) {
            names[i] = states[i].getDisplayName();
        }

        return names;
    }
}
