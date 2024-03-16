package glacialExpedition.models.explorers;

import static glacialExpedition.common.ExceptionMessages.EXPLORER_INVALID_TYPE;

public class ExplorerFactory {
    public static Explorer createExplorer(String type, String explorerName) {

        Explorer explorer = null;

        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        return explorer;
    }
}