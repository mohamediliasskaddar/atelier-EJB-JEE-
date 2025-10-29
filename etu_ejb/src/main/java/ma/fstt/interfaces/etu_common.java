package ma.fstt.interfaces;

import jakarta.ejb.Remote;
import ma.fstt.models.*;
import ma.fstt.models.Module ;
import java.util.List;

@Remote
public interface etu_common {

    void ajouterEtudiant(Etudiant e);
    void modifierEtudiant(Etudiant e);
    void supprimerEtudiant(Long id);
    Etudiant chercherEtudiant(Long id);
    List<Etudiant> listerEtudiants();

    void ajouterModule(Module m);
    void modifierModule(Module m);
    void supprimerModule(Long id);
    Module chercherModule(Long id);
    List<Module> listerModules();

    void ajouterSuivie(Suivie s);
    void modifierSuivie(Suivie s);
    void supprimerSuivie(Long id);
    Suivie chercherSuivie(Long id);
    List<Suivie> listerSuivies();
    List<Suivie> listerSuiviesParEtudiant(Long etudiantId);
    List<Suivie> listerSuiviesParModule(Long moduleId);
}
