package ma.fstt.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.interfaces.etu_common;
import ma.fstt.models.Etudiant;
import ma.fstt.models.Module;
import ma.fstt.models.Suivie;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/suivies")
public class SuivieServlet extends HttpServlet {

    @EJB(lookup = "java:global/etu_ejb-1.0-SNAPSHOT/GestionEtudiants!ma.fstt.interfaces.etu_common")
    private etu_common ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listSuivies(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteSuivie(request, response);
                break;
            default:
                listSuivies(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id_suivie");
        String idEtudiantStr = request.getParameter("idEtudiant");
        String idModuleStr = request.getParameter("idModule");
        String noteStr = request.getParameter("note");

        Suivie s = new Suivie();

        // Récupère les objets depuis l'EJB
        Etudiant etudiant = ejb.chercherEtudiant(Long.parseLong(idEtudiantStr));
        Module module = ejb.chercherModule(Long.parseLong(idModuleStr));

        s.setEtudiant(etudiant);
        s.setModule(module);
        s.setNote(Double.parseDouble(noteStr));
        s.setDate(LocalDate.now());

        if (idStr != null && !idStr.isEmpty()) {
            s.setId_suivie(Long.parseLong(idStr));
            ejb.modifierSuivie(s);
        } else {
            ejb.ajouterSuivie(s);
        }

        response.sendRedirect("suivies");
    }

    private void listSuivies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filterEtudiant = request.getParameter("filterEtudiant");
        String filterModule = request.getParameter("filterModule");

        List<Suivie> suivies;
        if (filterEtudiant != null && !filterEtudiant.isEmpty()) {
            suivies = ejb.listerSuiviesParEtudiant(Long.parseLong(filterEtudiant));
        } else if (filterModule != null && !filterModule.isEmpty()) {
            suivies = ejb.listerSuiviesParModule(Long.parseLong(filterModule));
        } else {
            suivies = ejb.listerSuivies();
        }

        request.setAttribute("suivies", suivies);
        request.setAttribute("etudiants", ejb.listerEtudiants());
        request.setAttribute("modules", ejb.listerModules());
        request.getRequestDispatcher("/listSuivies.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("etudiants", ejb.listerEtudiants());
        request.setAttribute("modules", ejb.listerModules());
        request.getRequestDispatcher("/addSuivie.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id_suivie = Long.parseLong(request.getParameter("id_suivie"));
        Suivie s = ejb.chercherSuivie(id_suivie);
        if (s != null) {
            request.setAttribute("suivie", s);
            request.setAttribute("etudiants", ejb.listerEtudiants());
            request.setAttribute("modules", ejb.listerModules());
        }
        request.getRequestDispatcher("/editSuivie.jsp").forward(request, response);
    }

    private void deleteSuivie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id_suivie = Long.parseLong(request.getParameter("id_suivie"));
        ejb.supprimerSuivie(id_suivie);
        response.sendRedirect("suivies");
    }
}