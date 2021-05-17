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

public class Login extends GuidaTvBaseController{
    
    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }

    private void action_login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException {
        
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            //aggiungiamo al template un wrapper che ci permette di chiamare la funzione stripSlashes
            //add to the template a wrapper object that allows to call the stripslashes function
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            List<Author> authors = ((NewspaperDataLayer)request.getAttribute("datalayer")).getAuthorDAO().getAuthors();
            request.setAttribute("authors", authors);
            if (article_key > 0) {
                Article article = ((NewspaperDataLayer)request.getAttribute("datalayer")).getArticleDAO().getArticle(article_key);
                if (article != null) {
                    request.setAttribute("article", article);
                    res.activate("write_single.ftl.html", request, response);
                } else {
                    request.setAttribute("message", "Undefined article");
                    action_error(request, response);
                }
            } else {
                //article_key==0 indica un nuovo numero 
                //article_key==0 indicates a new issue
                Article article = ((NewspaperDataLayer)request.getAttribute("datalayer")).getArticleDAO().createArticle();
                request.setAttribute("article", article);
                res.activate("write_single.ftl.html", request, response);
            }
        } catch (DataException ex) {
            request.setAttribute("message", "Data access exception: " + ex.getMessage());
            action_error(request, response);
        }
    }
    

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        int k;
        try {
            k = SecurityLayer.checkNumeric(request.getParameter("k"));
            action_login(request, response, k);
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Login not specified");
            action_error(request, response);
        } catch (IOException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);

        } catch (TemplateManagerException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Render article servlet";
    }// </editor-fold>
}
