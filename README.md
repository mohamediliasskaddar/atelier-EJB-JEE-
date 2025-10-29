# Gestion des Étudiants, Modules & Notes (Suivie) – Atelier JEE

> **Application JEE distribuée** avec **EJB Remote**, **JPA**, **Servlets**, **JSP**, **MVC2**, **WildFly 37.0.1.Final**  
> **Base de données MySQL** – **Architecture modulaire** – **Déploiement manuel JAR + WAR**

---

## Fonctionnalités Développées

| Fonctionnalité | Description |
|----------------|-----------|
| **Gestion des Étudiants** | Ajouter, modifier, supprimer, lister |
| **Gestion des Modules** | Ajouter, modifier, supprimer, lister |
| **Gestion des Notes (Suivie)** | Ajouter, modifier, supprimer, lister |
| **Filtrage dynamique** | Par étudiant ou par module |
| **Relations JPA** | `@ManyToOne` entre `Suivie` → `Etudiant` & `Module` |
| **EJB Remote** | `@EJB(lookup = "...")` – Communication JAR ↔ WAR |
| **Sécurité des formulaires** | Champs obligatoires, validation côté serveur |
| **Interface responsive** | CSS propre, boutons, tableaux clairs |
| **Déploiement distribué** | 1 seul WildFly → 2 modules déployés |

---

## Architecture du Projet

```text
JAVA workspace/
├── etu_ejb/                 ← EJB (JAR)
│   ├── src/main/java/
│   │   ├── ma.fstt.ejb/
│   │   ├── ma.fstt.models/  ← Entités JPA
│   │   └── ma.fstt.interfaces/
│   └── pom.xml
│
├── etu_web/                 ← Web (WAR)
│   ├── src/main/java/
│   │   └── ma.fstt.servlet/ ← 3 Servlets
│   ├── src/main/webapp/
│   │   ├── *.jsp            ← 9 pages JSP
│   │   └── WEB-INF/
│   └── pom.xml
│
└── README.md                ← CE FICHIER
```

## Workflow de Développement

| Étape | Action |
|------|--------|
| 1 | Création de la base MySQL (`etudiant`, `module`, `suivie`) |
| 2 | Modélisation JPA avec `@ManyToOne` |
| 3 | Développement EJB Remote (`etu_common`, `GestionEtudiants`) |
| 4 | Création des POJO dans `etu_web` |
| 5 | Développement des 3 Servlets (`EtudiantServlet`, `ModuleServlet`, `SuivieServlet`) |
| 6 | Création des 9 JSP (list, add, edit) |
| 7 | Configuration `jboss-deployment-structure.xml` |
| 8 | Déploiement manuel : `etu_ejb.jar` → puis `etu_web.war` |
| 9 | Résolution des erreurs : JNDI, ordre de déploiement, `@EJB(lookup)` |
| 10 | Tests complets : CRUD + filtres |

---

## Technologies Utilisées

| Technologie | Version |
|-----------|--------|
| **Java** | 21 |
| **WildFly** | 37.0.1.Final |
| **MySQL** | 8.0+ |
| **Maven** | 3.9+ |
| **JPA / Hibernate** | Inclus dans WildFly |
| **Jakarta EE** | 10 |
| **Lombok** | 1.18.30 |
| **PowerShell / CMD** | Déploiement manuel |

---

## Déploiement (Commandes)

```powershell
# 1. Démarrer WildFly
cd C:\dev\wildfly-37.0.1.Final\bin
.\standalone.bat

# 2. Build EJB
cd "C:\Users\imk\IdeaProjects\JAVA workspace\etu_ejb"
mvn clean package

# 3. Build Web
cd "C:\Users\imk\IdeaProjects\JAVA workspace\etu_web"
mvn clean package

# 4. Nettoyer les déploiements
Remove-Item 'C:\dev\wildfly-37.0.1.Final\standalone\deployments\*' -Force -ErrorAction SilentlyContinue

# 5. Déployer EJB (en premier)
Copy-Item 'target\etu_ejb-1.0-SNAPSHOT.jar' 'C:\dev\wildfly-37.0.1.Final\standalone\deployments\' -Force
Start-Sleep -Seconds 15

# 6. Déployer Web
Copy-Item 'target\etu_web-1.0-SNAPSHOT.war' 'C:\dev\wildfly-37.0.1.Final\standalone\deployments\etu_web.war' -Force

URLs de l’Application

```

## PageURL:

**Liste Étudiants** : http://localhost:8080/etu_web/etudiantsListe.

**Modules**: http://localhost:8080/etu_web/modulesListe.

**Notes**:  http://localhost:8080/etu_web/suivies.



## 📧 Contact

For support, contact [Mohamed Iliass Kaddar](mailto:moahmediliassk@gmail.com).