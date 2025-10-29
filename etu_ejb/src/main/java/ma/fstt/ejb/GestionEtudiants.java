package ma.fstt.ejb;

import jakarta.ejb.Stateless;
import jakarta.ejb.Remote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ma.fstt.interfaces.etu_common;
import ma.fstt.models.Etudiant;
import ma.fstt.models.Module;
import ma.fstt.models.Suivie;

import java.util.List;

@Stateless
@Remote(etu_common.class)
public class GestionEtudiants implements etu_common {


    @PersistenceContext(unitName = "cnx")
    private EntityManager em;

    // ======================

    @Override
    public void ajouterEtudiant(Etudiant e) {
        em.persist(e); // INSERT
    }

    @Override
    public void modifierEtudiant(Etudiant e) {
        em.merge(e); // UPDATE
    }

    @Override
    public void supprimerEtudiant(Long id) {
        Etudiant e = em.find(Etudiant.class, id);
        if (e != null) {
            em.remove(e); // DELETE
        }
    }

    @Override
    public Etudiant chercherEtudiant(Long id) {
        return em.find(Etudiant.class, id);
    }

    @Override
    public List<Etudiant> listerEtudiants() {
        return em.createQuery("SELECT e FROM Etudiant e", Etudiant.class)
                .getResultList();
    }

    // ======================

    @Override
    public void ajouterModule(Module m) {
        em.persist(m);
    }

    @Override
    public void modifierModule(Module m) {
        em.merge(m);
    }

    @Override
    public void supprimerModule(Long id) {
        Module m = em.find(Module.class, id);
        if (m != null) {
            em.remove(m);
        }
    }

    @Override
    public Module chercherModule(Long id) {
        return em.find(Module.class, id);
    }

    @Override
    public List<Module> listerModules() {
        return em.createQuery("SELECT m FROM Module m", Module.class)
                .getResultList();
    }

    // ======================

    @Override
    public void ajouterSuivie(Suivie s) {
        em.persist(s);
    }

    @Override
    public void modifierSuivie(Suivie s) {
        em.merge(s);
    }

    @Override
    public void supprimerSuivie(Long id) {
        Suivie s = em.find(Suivie.class, id);
        if (s != null) {
            em.remove(s);
        }
    }

    @Override
    public Suivie chercherSuivie(Long id) {
        return em.find(Suivie.class, id);
    }

    @Override
    public List<Suivie> listerSuivies() {
        return em.createQuery("SELECT s FROM Suivie s", Suivie.class)
                .getResultList();
    }

    @Override
    public List<Suivie> listerSuiviesParEtudiant(Long etudiantId) {
        TypedQuery<Suivie> query = em.createQuery(
                "SELECT s FROM Suivie s WHERE s.etudiant.id_etudiant = :etudiantId",
                Suivie.class
        );
        query.setParameter("etudiantId", etudiantId);
        return query.getResultList();
    }

    @Override
    public List<Suivie> listerSuiviesParModule(Long moduleId) {
        TypedQuery<Suivie> query = em.createQuery(
                "SELECT s FROM Suivie s WHERE s.module.id_module = :moduleId",
                Suivie.class
        );
        query.setParameter("moduleId", moduleId);
        return query.getResultList();
    }
}