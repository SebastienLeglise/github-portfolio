# Scanner de ports basique

## Description
Ce projet est un scanner de ports basique écrit en Java. Il permet de scanner une plage de ports sur une machine cible pour déterminer quels ports sont ouverts.

## Fonctionnalités
- Scanner une plage de ports spécifiée par l'utilisateur.
- Définir un délai d'attente pour chaque tentative de connexion.
- Afficher les ports ouverts et fermés.

## Installation
Pour exécuter ce projet, vous devez avoir Java installé sur votre machine. Clonez ce dépôt et compilez le fichier `PortScanner.java`.

```bash
git clone https://gitlab.com/Seb501/scanner-de-ports-basique.git
cd scanner-de-ports-basique
javac src/PortScanner.java
```

## Utilisation
Pour utiliser le scanner de ports, exécutez la classe `PortScanner` :

```bash
java -cp src PortScanner
```

Vous serez invité à entrer les informations suivantes :
- Host (par défaut : localhost)
- Port de départ (par défaut : 1)
- Port de fin (par défaut : 65535)
- Timeout (par défaut : 200 ms)

Le programme affichera ensuite les ports ouverts et fermés.

## Explication des fonctions

### `initializeScanner()`
Cette fonction initialise le scanner en demandant à l'utilisateur de saisir les informations nécessaires (host, port de départ, port de fin, timeout). Si l'utilisateur ne saisit pas de valeur, des valeurs par défaut sont utilisées.

### `scanPorts()`
Cette fonction scanne les ports de la plage spécifiée et retourne une liste des ports ouverts. Pour chaque port, elle tente de se connecter et ajoute le port à la liste des ports ouverts si la connexion réussit.

### `affichePortsOuverts(List<Integer> openPorts)`
Cette fonction affiche les ports ouverts. Si aucun port n'est ouvert, elle affiche un message indiquant qu'aucun port n'est ouvert.

### `lanceScanner()`
Cette fonction lance le processus de scan en initialisant le scanner, en scannant les ports et en affichant les ports ouverts.

## Auteurs et remerciements
Merci à tous ceux qui ont contribué à ce projet.
