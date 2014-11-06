package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hasado
 */
public class CountrysWatch extends HttpServlet {
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Countrys Watch</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Countrys watch</h1>");

            out.println("<form method='get'>");
            out.println("Continent: <input type='text' name='Continent' size='10' /><br />");
            out.println("<input type='submit' value='Watch' /><br />");
            out.println("</form>");

            String continent = request.getParameter("Continent");
            if(continent != null)
            {
                out.println(this.getTable(continent));
            }
            else
            {
                out.println(this.getTable());
            }
            out.println("</body>");
            out.println("</html>");
        }
        finally
        {
            out.close();
        }
    } 
    
    private String getTable() throws SQLException, ClassNotFoundException
    {
	ResultSet rs = SetConnect.getKolledzConnection().createStatement().executeQuery("SELECT name, continent FROM country ORDER BY name ASC;");

        int i;
        String result = "<table>\n";
        for(i = 0; rs.next(); i++)
	{
            result += "<tr>\n<td>"+rs.getString("name")+"</td>\n";
            result += "<tr>\n<td>"+rs.getString("continent")+"</td>\n";
	}
        result += "</table>\n";
        result += "Count: " + i + "\n";

        rs.close();
        return result;
    }

    private String getTable(String continent) throws SQLException, ClassNotFoundException
    {
	ResultSet rs = SetConnect.getKolledzConnection().createStatement().executeQuery("SELECT name, continent FROM country WHERE continent LIKE '" + continent + "' ORDER BY name ASC;");

        int i;
        String result = "<table>\n";
        for(i = 0; rs.next(); i++)
	{
            result += "<tr>\n<td>"+rs.getString("name")+"</td>\n";
            result += "<tr>\n<td>"+rs.getString("continent")+"</td>\n";
	}
        result += "</table>\n";
        result += "Count: " + i + "\n";

        rs.close();
        return result;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CountrysWatch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CountrysWatch.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CountrysWatch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CountrysWatch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}