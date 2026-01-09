# Phoenix

## Description

**Phoenix** est un projet de modernisation visant la migration et l’amélioration d’un moteur FN legacy vers une architecture **Spring Boot** moderne.

### Étape 1 — Installer Git

Git est requis pour télécharger le code source.

1. Télécharger Git : https://git-scm.com
2. Installer Git (options par défaut)
3. Ouvrir un terminal (Command Prompt ou PowerShell)

Vérifier l’installation :
```bat
git --version

Étape 2 — Installer Java

Java est requis pour compiler et exécuter l’application.

Java 21 (version requise)

Java 25 (prévu ultérieurement)

Vérifier l’installation :

java -version

Étape 3 — Installer Apache Maven (version 3.9.11)

Maven est l’outil utilisé pour compiler et lancer l’application.

Télécharger Maven : https://maven.apache.org/download.cgi

Extraire l’archive, par exemple :

C:\apache-maven-3.9.11

Étape 4 — Configurer Maven (OBLIGATOIRE)
4.1 Créer la variable MAVEN_HOME

Nom :

MAVEN_HOME


Valeur :

C:\apache-maven-3.9.11

4.2 Ajouter Maven au Path

Ajouter :

%MAVEN_HOME%\bin


Ou, si le Path est sur une seule ligne, ajouter à la fin :

;%MAVEN_HOME%\bin


Fermer toutes les fenêtres et rouvrir le terminal.

Vérifier Maven :

mvn -version


Résultat attendu :

Apache Maven 3.9.11

Étape 5 — Cloner le projet
git clone https://github.com/ndexadmin/phoenix.git
cd phoenix
git checkout develop

⚠️ Configuration requise

Avant de lancer le projet, renseigner les fichiers de configuration Spring Boot :

- `application-test.properties`  
  → utilisé pour l’exécution des tests

- `application.properties`  
  → utilisé pour le démarrage de l’application (base de données)

⚠️ Les paramètres de base de données doivent être correctement configurés dans ces fichiers.

Étape 6 — Compiler le projet
mvn clean install


Résultat attendu :

BUILD SUCCESS

Étape 7 — Lancer l’application

⚠️ Le JAR n’est pas exécutable directement.
Utiliser Maven :

mvn spring-boot:run

Étape 8 — Accéder à l’application

Ouvrir un navigateur et aller à :

http://localhost:8085/phoenix/fullservice/en/gicapitalcorp/login ou
http://localhost:8085/phoenix/${product}/${locale}/${firmId}/login



