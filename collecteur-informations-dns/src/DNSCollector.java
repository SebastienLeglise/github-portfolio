import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DNSCollector {

    private final String[] types = {"A", "NS", "CNAME", "MX", "ANY"};


        /**
         * La fonction collectDNSInfo récupère les informations DNS pour un domaine donné.
         * 
         * Étapes de la fonction :
         * 1. Construire l'URL de l'API DNS en utilisant le domaine fourni.
         * 2. Établir une connexion HTTP à l'API DNS.
         * 3. Envoyer une requête GET à l'API.
         * 4. Vérifier si la réponse de l'API est réussie (code de réponse 200).
         * 5. Si la réponse est réussie, lire les données de la réponse.
         * 6. Analyser les données brutes de la réponse pour extraire les enregistrements DNS.
         * 7. Retourner la liste des enregistrements DNS formatés.
         * 
         * @param domain Le domaine pour lequel récupérer les informations DNS.
         * @return Une liste de chaînes de caractères représentant les enregistrements DNS.
         */

    public ArrayList<String> collectDNSInfo(String domain) {
        ArrayList<String> dnsRecords = new ArrayList<>();
        try {

            for (String type : types) {   //on construit pour chaque type
                //Construire l'URL de l'API
                String apiUrl = "https://dns.google/resolve?name=" + domain + "&type="+type;
                System.out.println("URL de l'API: " + apiUrl); 
                //Établir une connexion HTTP à l'API DNS et envoyer une requête GET
                HttpURLConnection connection = createConnection(apiUrl);
            
                // Vérifier si la réponse est réussie
                int responseCode = connection.getResponseCode();
                System.out.println("Code de réponse: " + responseCode); 
                if (responseCode == 200) {
                    //lire les données de la réponse
                    String response = getResponse(connection);
                    //System.out.println("Réponse de l'API: " + response); 
                    //Analyser les données brutes de la réponse et extraire les enregistrements DNS
                    dnsRecords.addAll((new ParseDNSRecords(response)).getRecords());

                } else {
                    System.out.println("erreur lors de la recuperation d'informations du domain: " + domain);
                }
            }
            if(dnsRecords.isEmpty())
                dnsRecords.add("Aucun enregistrement DNS trouvé.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dnsRecords;
    }

    /*--------------------------------------------------------------- */

    private HttpURLConnection createConnection(String apiUrl) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    private String getResponse(HttpURLConnection connection) throws Exception {
        StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                    response.append(inputLine);
            }
        return response.toString();
    }

    /*--------------------------------------------------------------- */

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez le domaine à interroger: ");
            String domain = scanner.nextLine().trim();

            DNSCollector collector = new DNSCollector();

            try {
                ArrayList<String> dnsInfo = collector.collectDNSInfo(domain);
                System.out.println("Informations DNS pour " + domain + ":\n" + String.join("\n", dnsInfo));
            } catch (Exception e) {
                System.err.println("Erreur lors de la collecte des informations DNS: " + e.getMessage());
            }
        }
    
    }
}