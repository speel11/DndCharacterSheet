package dungeons_and_dragons.util.temp;

import dungeons_and_dragons.entity.Spells;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sean Peel
 */
public class DatabaseConnection {

    Statement stmt;

    public DatabaseConnection() {
        try {
            String userName = "speel";
            String password = "jesus123";

            String url = "jdbc:mysql://ec2-52-91-249-32.compute-1.amazonaws.com:3306/dungeons_and_dragons";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");

            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit(Spells s) throws InstantiationException, IllegalAccessException, SQLException {

        if (s.getDescription().contains("\"")) {
            s.setDescription(s.getDescription().replace("\"", "'"));
        }
                stmt.execute("INSERT INTO Spells (name, level, school, time, components, duration, class_, description) "
                        + "VALUES (\"" + s.getName() + "\", \"" + s.getLevel() +
                        "\", \"" + s.getSchool() + "\", \"" + s.getTime() + "\", \"" + s.getComponents() + "\", \"" +
                        s.getDuration() + "\", \"" + s.getClass_() + "\", \"" + s.getDescription() + "\")" );
        System.out.println(s.getName() + " - " + s.getClass_());

    }
}
