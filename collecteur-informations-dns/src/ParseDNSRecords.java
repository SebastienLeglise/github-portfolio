import java.util.ArrayList;

public class ParseDNSRecords {
    private final ArrayList<String> recordsList = new ArrayList<>();
    
    public ParseDNSRecords(String data) {
        
    
        int answerIndex = data.indexOf("\"Answer\":");
        if (answerIndex == -1) {
            return ; 
        }
    
        // Extraire la partie contenant les réponses DNS
        String answerPart = data.substring(answerIndex);
        String[] entries = answerPart.split("\\{");
    
        for (String entry : entries) {
            if (entry.contains("\"data\":") && entry.contains("\"type\":")) {
                // Trouver la valeur du champ "type"
                int typeIndex = entry.indexOf("\"type\":") + 7;
                int typeEnd = entry.indexOf(",", typeIndex);
                int recordType = Integer.parseInt(entry.substring(typeIndex, typeEnd).trim());
    
                // Trouver la valeur de "data"
                int dataIndex = entry.indexOf("\"data\":");
                int startQuote = entry.indexOf("\"", dataIndex + 7);
                int endQuote = entry.indexOf("\"", startQuote + 1);
    
                if (startQuote != -1 && endQuote != -1) {
                    String record = entry.substring(startQuote + 1, endQuote);
                    
                    // Filtrer les types d'enregistrement intéressants
                    if (recordType == 1 || recordType == 2 || recordType == 5 || recordType == 15) { // A, NS, CNAME, MX
                        recordsList.add(record);
                    }
                }
            }
        }
    
        if (recordsList.isEmpty()) {
            recordsList.add("Aucun enregistrement intéressant trouvé.");
        }
    }

    public ArrayList<String> getRecords() {
        return this.recordsList;
    }
    

}
