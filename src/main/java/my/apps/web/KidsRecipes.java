package my.apps.web;
import my.apps.db.RecipeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/kidsRecipes")
public class KidsRecipes extends HttpServlet {

    private int counter;

    private RecipeRepository recipeRepository = new RecipeRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        //get input as string
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String instructions = request.getParameter("instructions");
        String duration = request.getParameter("duration");

        Recipes recipe = new Recipes(type, name, ingredients, instructions, duration);



       // System.out.println(name + type);
        // write results to response
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        addStyle(out);

        out.println("<head>");
        out.println("<title><b>CookBook</b></title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
        out.println("</head>");

        out.println("<div id='container'>");
        out.println("<h2>Cook Book</h2>");
        out.println("<div id='recipeContent'>");

        try {
          out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/style.css\">");
          out.println("<h3>New recipe</h3>");
          addStyle(out);

          out.println("<p>Type: <b>" + type  + "</b><br/></p>");
          out.println("<p>Name: <b>" + name + "</b><br/></p>");
          out.println("<p>Ingredients: <b>" + ingredients + "</b><br/></p>");
          out.println("<p>Instructions: <b>" + instructions + "</b><br/></p>");
          out.println("<p>Duration: <b>" + duration + "</b><br/></p>");

         recipeRepository.insert(recipe);
        } catch (ClassNotFoundException e) {
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e){
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        }
        addGoBack(out);

        // finished writing, send to browser
        out.close();
    }

    private void addGoBack(PrintWriter out){
        out.println("<a href='/'>Go Back</a>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        String type = req.getParameter("type");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<title> Get Recipes </title>");
        addStyle(out);
        out.println("</head>");

        try {
            out.println("<h3>Get Recipes</h3><h3>For "+type+"</h3>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Type</th>");
            out.println("<th>Name</th>");
            out.println("<th>Ingredients</th>");
            out.println("<th>Instructions</th>");
            out.println("<th>Duration</th>");
            out.println("</tr>");

            List<Recipes> recipes;
            if("all".equals(type)) {
                recipes = recipeRepository.read();
            } else {
                recipes = recipeRepository.read(type);
            }

            for (Recipes recipe : recipes){
                out.println("<tr>");
                out.println("<td>"+recipe.getId()+"</td>");
                out.println("<td>"+recipe.getType()+"</td>");
                out.println("<td>"+recipe.getName()+"</td>");
                out.println("<td>"+recipe.getIngredients()+"</td>");
                out.println("<td>"+recipe.getInstructions()+"</td>");
                out.println("<td>"+recipe.getDuration()+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (ClassNotFoundException e){
            out.println("<div class='error'><b>Unable initialize database connection<b></div>");
        } catch (SQLException e){
            out.println("<div class='error'><b>Unable to write to database! " +  e.getMessage() +"<b></div>");
        }
        addGoBack(out);
        out.close();
    }

    private void addStyle (PrintWriter out){
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called. Counter is: " + counter);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
    }
}