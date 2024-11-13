package es.albarregas.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alberto
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(".").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = "";
        
        
        // Compruebo si el botón de Menú Principal ha sido pulsado
        if (request.getParameter("volver") != null) {
            url = ".";
        } else {
            Locale locales[] = SimpleDateFormat.getAvailableLocales();
            // Compruebo si no se ha seleccionado un país
            if (request.getParameter("pais") == null) {
                url = ".";
            } else {
                // Obtengo el valor del selector(Ej: España(es))
                String paisElegido = request.getParameter("pais");
                int inicio = paisElegido.indexOf('(') +1;
                int fin = paisElegido.indexOf(')');
                int finPais = paisElegido.indexOf('(');
                // Obtengo el nombre del pais elegido --> Ej: España
                String paisNombre = paisElegido.substring(0,finPais);
                // Obtengo el código del país elegido --> Ej: es
                String codigo = paisElegido.substring(inicio,fin);
                // Recorro los locales disponibles
                for (Locale pais : locales) {
                    if (pais.getDisplayCountry().equals(paisNombre)) {
                        // Paso el nombre,código del pais y su código de lenguaje                      
                        request.setAttribute("nombrePais", pais.getDisplayCountry());
                        request.setAttribute("codigoPais", pais.getCountry());
                        
                       
                        // Compruebo que se pase a la vista el codigo que ha seleccionado el usuario
                        if (pais.getLanguage().equals(codigo)) {
                             request.setAttribute("codigoLenguaje", pais.getLanguage());
                             request.setAttribute("lenguaje",pais.getDisplayLanguage());
                        }
                    }
                }
                // Declaro una variable con la fecha de hoy
                Date fecha = new Date();
                // Paso la fecha de hoy por atributo de petición
                request.setAttribute("fecha", fecha);                
                url = "/JSP/verPaisVista.jsp";
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}