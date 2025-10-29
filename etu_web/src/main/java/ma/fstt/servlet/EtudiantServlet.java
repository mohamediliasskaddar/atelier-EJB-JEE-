package ma.fstt.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.interfaces.etu_common;
import ma.fstt.models.Etudiant;

import java.io.IOException;
import java.util.List;

@WebServlet("/etudiants")
public class EtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/etu_ejb-1.0-SNAPSHOT/GestionEtudiants!ma.fstt.interfaces.etu_common")
    private etu_common ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteEtudiant(request, response);
                break;
            default:
                listEtudiants(request, response);
                break;
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/addEtudiant.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id_etudiant");
        String nom = request.getParameter("nom");
        String niveau = request.getParameter("niveau");

        Etudiant e = new Etudiant();
        e.setNom(nom);
        e.setNiveau(niveau);

        if (idStr != null && !idStr.isEmpty()) {
            e.setId_etudiant(Long.parseLong(idStr));
            ejb.modifierEtudiant(e);
        } else {
            ejb.ajouterEtudiant(e);
        }

        response.sendRedirect("etudiants");
    }

    private void listEtudiants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Etudiant> liste = ejb.listerEtudiants();
        request.setAttribute("etudiants", liste); // ✅ bon nom ici
        request.getRequestDispatcher("/listEtudiants.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id_etudiant"));
        Etudiant e = ejb.chercherEtudiant(id);

        if (e == null) {
            response.sendRedirect("etudiants");
            return;
        }

        request.setAttribute("etudiant", e);
        request.getRequestDispatcher("/editEtudiant.jsp").forward(request, response);
    }

    private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id_etudiant"));
        ejb.supprimerEtudiant(id);
        response.sendRedirect("etudiants");
    }
}
