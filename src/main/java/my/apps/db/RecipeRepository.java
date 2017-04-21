package my.apps.db;

import my.apps.web.Recipes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by camelia on 16/02/2017.
 */
public class RecipeRepository {

    // 1. define connection params to db
    final static String URL = "jdbc:postgresql://54.93.65.5/QA6_Camelia";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public void insert(Recipes recipe) throws ClassNotFoundException, SQLException {
        // 1. load the driver
        Class.forName("org.postgresql.Driver");

        // 2. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO recipe ( type, name, ingredients, instructions, duration) VALUES (?,?, ?, ?, ?)");
        pSt.setString(1, recipe.getType());
        pSt.setString(2, recipe.getName());
        pSt.setString(3, recipe.getIngredients());
        pSt.setString(4, recipe.getInstructions());
        pSt.setString(5, recipe.getDuration());

        // 4. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();
        System.out.println("Inserted " + rowsInserted + " rows.");

        // 5. close the objects
        pSt.close();
        conn.close();

    }

    public List<Recipes> read(String type) throws ClassNotFoundException, SQLException {
        //load the driver
        Class.forName("org.postgresql.Driver");

        //obtain connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        //create a query statement
        PreparedStatement st = conn.prepareStatement("SELECT id, type, name, ingredients, instructions, duration FROM recipe where type = ?");
        st.setString(1, type);
        //execute a query
        ResultSet rs = st.executeQuery();

        //iterate the result set and print the values
        List<Recipes> recipes = new ArrayList<>();
        while (rs.next()){
            Recipes recipe = new Recipes(
                    rs.getString("type"),
                    rs.getString("name"),
                    rs.getString("ingredients"),
                    rs.getString("instructions"),
                    rs.getString("duration")

            );
            recipe.setId(rs.getLong("id"));
            recipes.add(recipe);;

        }

        //close the object
        rs.close();
        st.close();
        conn.close();
        return recipes;
    }

    public List<Recipes> read() throws ClassNotFoundException, SQLException {
        //load the driver
        Class.forName("org.postgresql.Driver");

        //obtain connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        //create a query statement
        Statement st = conn.createStatement();

        //execute a query
        ResultSet rs = st.executeQuery("SELECT id, type, name, ingredients, instructions, duration FROM recipe");

        //iterate the result set and print the values
        List<Recipes> recipes = new ArrayList<>();
        while (rs.next()){
            Recipes recipe = new Recipes(
                    rs.getString("type"),
                    rs.getString("name"),
                    rs.getString("ingredients"),
                    rs.getString("instructions"),
                    rs.getString("duration")

            );
            recipe.setId(rs.getLong("id"));
            recipes.add(recipe);;

        }

        //close the object
        rs.close();
        st.close();
        conn.close();
        return recipes;
    }


}
