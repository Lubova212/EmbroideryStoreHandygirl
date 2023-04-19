package database;


import org.apache.commons.configuration.PropertiesConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionToDB {

    private static Connection connection;

    static PropertiesConfiguration databaseProperties = new PropertiesConfiguration();
    public static Connection getConnection() {
        try {
            databaseProperties.load("database.properties");
            String pass = databaseProperties.getString("database.password");
            String user = databaseProperties.getString("database.user");
            String host = databaseProperties.getString("database.host");
            String port = databaseProperties.getString("database.port");
            String dbName = databaseProperties.getString("database.name");

            String connectionUrl = host + ":" + port + "/" + dbName;

            connection = DriverManager.getConnection(connectionUrl, user, pass);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnections(Connection connection, ResultSet resultSet, PreparedStatement statement) {
        try {
            if (connection != null) connection.close();
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
