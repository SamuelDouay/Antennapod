# AntennaPod

AntennaPod est une application Java pour la gestion et l'écoute de podcasts.

## À propos du projet

AntennaPod est une application développée en Java avec JavaFX qui vous permet de vous abonner à des flux RSS de
podcasts, de télécharger des épisodes et de les écouter.

## Fonctionnalités

- Abonnement à des flux RSS de podcasts
- Téléchargement et gestion d'épisodes
- Interface utilisateur intuitive avec JavaFX
- Stockage local des données avec SQLite

## Prérequis

- Java 22 ou supérieur
- Maven pour la compilation et la gestion des dépendances

## Installation

### Cloner le dépôt

```bash
git clone https://github.com/SamuelDouay/Antennapod.git
cd Antennapod
```

### Compiler le projet

```bash
mvn clean package
```

Cette commande génère un fichier JAR exécutable avec toutes les dépendances incluses.

### Exécuter l'application

```bash
java -jar target/AntennaPod-0.0.1-jar-with-dependencies.jar
```

## Technologies utilisées

- Java 22
- JavaFX 25 pour l'interface utilisateur
- SQLite pour la base de données
- Log4j2 pour la journalisation
- JUnit 5 pour les tests
- Jackson pour le traitement JSON
- RSSReader pour l'analyse des flux RSS
- Ikonli pour les icônes dans l'interface

## Développement

### Structure du projet

Le projet suit la structure standard d'un projet Maven :

```
AntennaPod/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── podcast/
│   │   │           └── antennapod/
│   │   │               └── Main.java
│   │   └── resources/
│   └── test/
│       └── java/
└── pom.xml
```

### Tests

Pour exécuter les tests :

```bash
mvn test
```

## Licence

Ce projet est sous licence MIT - voir ci-dessous pour plus de détails :

```
MIT License

Copyright (c) 2025 AntennaPod

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```