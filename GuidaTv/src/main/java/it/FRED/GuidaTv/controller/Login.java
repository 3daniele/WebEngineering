package it.FRED.GuidaTv.controller;

import it.FRED.GuidaTv.controller.GuidaTvBaseController;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataException;
import it.FRED.GuidaTv.data.dao.GuidaTvDataLayer;
import it.FRED.GuidaTv.framework.result.FailureResult;
import it.FRED.GuidaTv.framework.result.SplitSlashesFmkExt;
import it.FRED.GuidaTv.framework.result.TemplateManagerException;
import it.FRED.GuidaTv.framework.result.TemplateResult;
import it.FRED.GuidaTv.framework.security.SecurityLayer;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends GuidaTvBaseController{
    
    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }
  
    private void action_default(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException, IOException, ServletException {
        
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            res.activate("login.ftl.html", request, response);
        } catch (Exception ex) {
            System.out.println("errore");
            /*
            request.setAttribute("message", "Data access exception: " + ex.getMessage());
            action_error(request, response);
            */
        }
        
    }

    private void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException {
        /*
        String email="";
        String password="";
        
        try {
            
            List<Utente> utenti = ((GuidaTvDataLayer)request.getAttribute("datalayer")).getUtenteDAO().getUtenti();
            
            email = request.getParameter("email");
            password = request.getParameter("password");
            
            System.out.println(email);
            System.out.println(password);
        } catch (DataException ex) {
            request.setAttribute("message", "Data access exception: " + ex.getMessage());
            action_error(request, response);
        }
*/
    }
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        
        try {
            action_default(request, response);

        } catch (IOException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);

        } catch (TemplateManagerException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);

        }
    }

    
    @Override
    public String getServletInfo() {
        return "Render login servlet";
    }
}
