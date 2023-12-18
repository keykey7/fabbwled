package ch.bbw.fabbwled.lands.character;

public enum ProfessionEnum {
    WAYFARER("Wayfarer"),
    WARRIOR("Warrior"),
    MAGE("Mage"),
    ROGUE("Rogue"),
    PRIEST("Priest"),
    TROUBADOUR("Troubadour");

    private final String name;
    ProfessionEnum(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
