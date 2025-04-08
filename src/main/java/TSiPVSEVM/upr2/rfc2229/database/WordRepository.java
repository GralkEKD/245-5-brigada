package TSiPVSEVM.upr2.rfc2229.database;

import java.util.HashMap;
import java.util.List;

public interface WordRepository {

    List<HashMap<String, String>> getDefinitions();
}
