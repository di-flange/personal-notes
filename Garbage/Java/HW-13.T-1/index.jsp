<%-- 
    Author:     Anton Ishkov
    Group:      RDIR42
    Task:       Homework 13

                Создайте XML файл для хранения информации об автомобилях. Создайте
                web-приложение, где у пользователя есть возможность выбрать марку,
                модель автомобиля, после чего отображаются данные автомобилей по
                выбранным критериям.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>

<%@page import="javax.xml.validation.*"%>
<%@page import="javax.xml.transform.*"%>
<%@page import="javax.xml.transform.stream.StreamSource"%>
<%@page import="javax.xml.parsers.*"%>
<%@page import="javax.xml.xpath.*"%>

<%@page import="org.xml.sax.SAXException"%>
<%@page import="org.w3c.dom.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
    // Trriger - thtas all ok
    boolean fine = true;
    
    try
    {
        /**
         * Perform XML validation by comparing it with the scheme.
         */
        SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema")
            .newSchema(new File(application.getRealPath("/") + "xml/cars.xsd")).newValidator()
                .validate(new StreamSource(application.getRealPath("/") + "xml/cars.xml"));
    }
    catch(Exception e)
    {
        out.println("ERROR: Problem while work with XML(STAGE-1).");
        out.println(e.getMessage());

        fine = false;
    }

    /**
     * Let's parse it.
     * Create DOM and search all mark and models nods, but before we need to
     * understand what data we already have.
     */
    ArrayList<String> marks  = new ArrayList<String>();
    ArrayList<String> models = new ArrayList<String>();

    String currentMark  = null;
    String currentModel = null;
    String currentText  = null;

    Document doc = null;

    if(fine)
    {
        try
        {
            // Load DOM of this document.
            doc = DocumentBuilderFactory
                    .newInstance()
                        .newDocumentBuilder()
                            .parse(application.getRealPath("/") + "xml/cars.xml");

            // Get all marks as node list
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("mark");

            // Get name of marks into the nodes
            for(int i = 0; i < nList.getLength(); i++)
            {
                Element eElement = (Element) nList.item(i);

                marks.add(eElement.getAttribute("name"));
            }
        }
        catch(Exception e)
        {
            out.println("ERROR: Problem while work with XML(STAGE-2).");
            out.println(e.getMessage());

            fine = false;
        }
    }

    // New fine becouse we open DOM and this status can be rewrited
    if(fine)
    {
         if(request.getParameter("mark") != null && request.getParameter("mark").length() > 1)
         {
            // Serach posted parameter mark in the valid marks
            for(int i = 0; i < marks.size(); i++)
            {
                if(marks.get(i).equalsIgnoreCase(request.getParameter("mark")))
                {
                    currentMark = marks.get(i);
                    break;
                }
            }

            // So if we have any value in current mark we can prepair models list
            NodeList nList = doc.getElementsByTagName("mark");

            // Get name of marks into the nodes
            for(int i = 0; i < nList.getLength(); i++)
            {
                // One more ugly line =) I can't do anythink with it =)))
                if(((Element) nList.item(i)).getAttribute("name").equalsIgnoreCase(currentMark))
                {
                    // if this is selected mark node we compile models list
                    Element markNode = (Element) nList.item(i);
                    nList = markNode.getElementsByTagName("model");

                    // we go down to the rabbit hole =)
                    for(i = 0; i < nList.getLength(); i++)
                    {
                        Element eElement = (Element) nList.item(i);

                        models.add(eElement.getAttribute("name"));

                        // Search current model if it is
                        if(models.get(i).equalsIgnoreCase(request.getParameter("model")))
                        {
                            currentModel = models.get(i);

                            currentText = eElement
                                            .getElementsByTagName("description")
                                                .item(0)
                                                    .getChildNodes()
                                                        .item(0)
                                                            .getNodeValue();
                        }
                   }
                }
            }
         }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cars Info</title>
    </head>
    <body>
        <h1>Cars Info</h1>
        <form name="mark" method="post">
            <select name="mark">
                <%
                // Here we draw comboboxes
                if(fine && marks.size() > 0)
                {
                    if(currentMark == null)
                    {
                        out.println("<option selected>Not Selected</option>");
                    }
                    else
                    {
                        out.println("<option>Not Selected</option>");
                    }
                    
                    for(int i = 0; i < marks.size(); i++)
                    {
                        if(marks.get(i).equalsIgnoreCase(currentMark))
                        {
                            out.println("<option value='" + marks.get(i) + "' selected>" + marks.get(i) + "</option>");
                        }
                        else
                        {
                            out.println("<option value='" + marks.get(i) + "'>" + marks.get(i) + "</option>");
                        }
                    }
                }
                else
                {
                    out.println("<option>Not Found</option>");
                }
                %>
            </select><br />
            <select name="model">
                <%
                if(fine && models.size() > 0)
                {
                    if(currentModel == null)
                    {
                        out.println("<option selected>Not Selected</option>");
                    }
                    else
                    {
                        out.println("<option>Not Selected</option>");
                    }

                    for(int i = 0; i < models.size(); i++)
                    {
                        if(models.get(i).equalsIgnoreCase(currentModel))
                        {
                            out.println("<option value='" + models.get(i) + "' selected>" + models.get(i) + "</option>");
                        }
                        else
                        {
                            out.println("<option value='" + models.get(i) + "'>" + models.get(i) + "</option>");
                        }
                    }
                }
                else
                {
                    out.println("<option>Select mark</option>");
                }
                %>
            </select><br />
            <input type="submit" value="Select">
        </form>

        <pre>
            <%
            // Print content if it need
            if(fine && currentMark != null && currentModel != null)
            {
                // XPath runing
                String nodes = (String) XPathFactory
                                                .newInstance()
                                                    .newXPath()
                                                        .compile("/cars/mark[name='audi']/model[name='TT']/description/text()")
                                                            .evaluate(doc, XPathConstants.STRING);

               out.print(currentText);
               // for (int i = 0; i < nodes.getLength(); i++)
                //{
                //    System.out.println("ads" + nodes.item(i).getNodeValue());
                //}

            }
            else
            {
                out.println("Select car");
            }
            %>
        </pre>
    </body>
</html>