# Verificateur de force de mot de passe

## Description
Ce projet est un vérificateur de force de mot de passe écrit en Java. Il vérifie si un mot de passe respecte certains critères de sécurité.

## Fonctionnalités
Le programme vérifie les critères suivants pour un mot de passe :
- Au moins un chiffre
- Au moins une lettre minuscule
- Au moins une lettre majuscule
- Au moins un caractère spécial parmi `@#$%^&+=`
- Pas d'espaces blancs
- Au moins 12 caractères de longueur

## Utilisation
Pour utiliser ce programme, exécutez la méthode `main` de la classe `ValidateurMP`. Vous serez invité à entrer un mot de passe, et le programme vous dira si le mot de passe est valide ou non.

## Explications des fonctions

### `estValid(String password)`
Cette méthode vérifie si le mot de passe respecte tous les critères de sécurité mentionnés ci-dessus.


### `rentrePassword()`
Cette méthode demande à l'utilisateur d'entrer un mot de passe et affiche si le mot de passe est valide ou non.


## Installation
Clonez le dépôt et compilez le fichier Java.
```sh
git clone https://gitlab.com/Seb501/verificateur-de-force-de-mot-de-passe.git
cd verificateur-de-force-de-mot-de-passe
javac src/ValidateurMP.java
java src/ValidateurMP
```