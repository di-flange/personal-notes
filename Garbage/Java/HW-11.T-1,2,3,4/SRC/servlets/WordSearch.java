package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
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
public class WordSearch extends HttpServlet {
   
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
            String word = request.getParameter("Word");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Word Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Search word</h1>");

            out.println("<form method='get'>");
            out.println("Word: <input type='text' name='Word' size='10' /><br />");
            out.println("<input type='submit' value='Search' /><br />");
            out.println("</form>");

            if(word != null)
            {
                BufferedReader bf = new BufferedReader(new FileReader(getServletContext().getRealPath("text.txt")));

                out.println("<h2>Search results:</h2>");
                String line;
                int count = 0;
                for (int i = 0; (line = bf.readLine()) != null; i++)
                {
                    int sign = line.indexOf(request.getParameter("Word"));

                    if (sign > -1)
                    {
                         out.println("Founded at line " + i + " started past signe " + sign + "<br />");
                         ++count;
                    }
                }

                if(count == 0)
                {
                    out.println("Not found.");
                }
            }

            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
