package TSiPVSEVM.upr2.rfc2229.database;

import TSiPVSEVM.upr2.rfc2229.server.handler.Strategy;
import com.sun.net.httpserver.HttpHandler;

import java.util.HashMap;
import java.util.List;

public abstract class WordRepository implements HttpHandler {

    protected Database db;

    protected Strategy strat;

    protected String word;

    private WordRepository(Database db, Strategy strat, String word) {
        this.db = db;
        this.word = word;
        this.strat = strat;
    }

    public abstract HashMap<String, List<String>> getDefinitions();

    public WordRepositoryBuilder builder() {
        return new WordRepositoryBuilder();
    }

    public static class WordRepositoryBuilder {

        private WordRepositoryBuilder() {

        }

    }
}
