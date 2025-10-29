package ma.fstt.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.interfaces.etu_common;
import ma.fstt.models.Module;

import java.io.IOException;
import java.util.List;

@WebServlet("/modules")
public class ModuleServlet extends HttpServlet {

    @EJB(lookup = "java:global/etu_ejb-1.0-SNAPSHOT/GestionEtudiants!ma.fstt.interfaces.etu_common")
    private etu_common ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listModules(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteModule(request, response);
                break;
            default:
                listModules(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id_module = request.getParameter("id_module");
        String nomModule = request.getParameter("nomModule");

        Module m = new Module();
        m.setNomModule(nomModule);

        if (id_module != null && !id_module.isEmpty()) {
            m.setId_module(Long.parseLong(id_module));
            ejb.modifierModule(m);
        } else {
            ejb.ajouterModule(m);
        }

        response.sendRedirect("modules");
    }

    private void listModules(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Module> liste = ejb.listerModules();
        request.setAttribute("modules", liste);
        request.getRequestDispatcher("/listModules.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/addModule.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id_module = Long.parseLong(request.getParameter("id_module"));
        Module m = ejb.chercherModule(id_module);
        if (m != null) {
            request.setAttribute("module", m);
        }
        request.getRequestDispatcher("/editModule.jsp").forward(request, response);
    }

    private void deleteModule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id_module = Long.parseLong(request.getParameter("id_module"));
        ejb.supprimerModule(id_module);
        response.sendRedirect("modules");
    }
}