package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hasado
 */
public class GenerateTable extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Generation tables</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form action='' method='get'>");
            out.println("Header: <input type='text' name='header' size='10' /><br />");
            out.println("Rows: <input type='text' name='rows' size='10' /><br />");
            out.println("Columns: <input type='text' name='columns' size='10' /><br />");
            out.println("Color: <input type='text' name='color' size='10' /><br />");
            out.println("<input type='submit' value='Generate' /><br />");
            out.println("</form>");
            out.println("<h1>" + request.getParameterValues("header")[0] + "</h2>");

            out.println("<table bgcolor='" + request.getParameterValues("color")[0] + "'>");
            for (int i = 0; i < Integer.valueOf(request.getParameterValues("rows")[0]); i++)
            {
                out.println("<tr>");
                for (int j = 0; j < Integer.valueOf(request.getParameterValues("columns")[0]); j++)
                {     
                    out.println("<td>");
                    out.println(i + " - " + j);
                    out.println("</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e)
        {
        
        }

        out.close();
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }
}