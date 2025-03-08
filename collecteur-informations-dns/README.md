# Collecteur d'Informations DNS

## Description
Ce projet est un collecteur d'informations DNS qui interroge les enregistrements DNS pour un domaine donné en utilisant l'API DNS de Google. Il prend en charge les types d'enregistrements A, NS, CNAME, MX et ANY.

## Installation
Pour installer et exécuter ce projet, suivez les étapes ci-dessous :

1. Clonez le dépôt :
    ```bash
    git clone https://gitlab.com/Seb501/collecteur-informations-dns.git
    cd collecteur-informations-dns
    ```

2. Compilez les fichiers Java :
    ```bash
    javac src/*.java -d bin
    ```

3. Exécutez le programme :
    ```bash
    java -cp bin DNSCollector
    ```

## Utilisation
Lorsque vous exécutez le programme, il vous sera demandé d'entrer un domaine. Le programme interrogera alors les enregistrements DNS pour ce domaine et affichera les résultats.

Exemple :
```bash
Entrez le domaine à interroger: example.com
Informations DNS pour example.com:
93.184.216.34
ns1.example.com
ns2.example.com
```

## Contribuer
Les contributions sont les bienvenues ! Si vous souhaitez contribuer à ce projet, veuillez suivre les étapes ci-dessous :

1. Forkez le dépôt.
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`).
3. Commitez vos modifications (`git commit -m 'Add some AmazingFeature'`).
4. Poussez votre branche (`git push origin feature/AmazingFeature`).
5. Ouvrez une Pull Request.
