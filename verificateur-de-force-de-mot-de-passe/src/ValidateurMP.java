import java.util.Scanner;

public class ValidateurMP {
    

    private static final String CHIFFRE = ".*[0-9].*";          // au moins un chiffre
    private static final String LOWERCASE = ".*[a-z].*";      // au moins une lettre minuscule
    private static final String UPPERCASE = ".*[A-Z].*";      // au moins une lettre majuscule
    private static final String SPECIAL_CHAR = ".*[@#$%^&+=].*"; // au moins un caractère spécial
    private static final String NO_SPACE = "\\S+";       // pas d'espaces blancs
    private static final int MIN_LENGTH = 12;           // au moins 12 caractères


    private boolean estValid(String password) {
        if (password == null || password.length() < MIN_LENGTH )
            return false;
        return password.matches(CHIFFRE)
            && password.matches(LOWERCASE)
            && password.matches(UPPERCASE)
            && password.matches(SPECIAL_CHAR)
            && password.matches(NO_SPACE);
    }


    public void rentrePassword(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Veuillez entrer un mot de passe : ");
            String motDePasse = scanner.nextLine();
            System.out.println("Le mot de passe que vous avez entré est : " + motDePasse);
            System.out.println("Le mot de passe \"" + motDePasse + "\" est " + (estValid(motDePasse) ? "valide" : "invalide"));
            scanner.close();
        }
    }

    public static void main(String[] args) {
        ValidateurMP validateur = new ValidateurMP();
        validateur.rentrePassword();
    }

}