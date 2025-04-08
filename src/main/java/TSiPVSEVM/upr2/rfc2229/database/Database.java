package TSiPVSEVM.upr2.rfc2229.database;

public enum Database {
    EXCLAMATION("\"Find first\""),
    ASTERISK("\"Find all\""),
    IT_BR("\"Italian Brainrot\""),
    ANIME_CHARS("\"Anime Characters (powered by animecharactersdatabase.com)\""),
    PDEX("\"Pokedex (powered by ex.traction.one/pokedex)\"");

    private final String name;

    Database(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
