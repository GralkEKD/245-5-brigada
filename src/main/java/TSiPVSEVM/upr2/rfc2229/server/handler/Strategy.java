package TSiPVSEVM.upr2.rfc2229.server.handler;

public enum Strategy {
    EXACT("\"Match words exactly\""),
    PREFIX("\"Match word prefixes\""),
    SUBSTRING("\"Match substring anywhere in word\""),
    REGEX("\"Match using regular expressions\"");

    private final String name;

    Strategy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
