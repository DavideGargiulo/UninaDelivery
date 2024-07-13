package com.unina.oobd2324gr18.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public final class DatabaseConnectionDAO {
  private static DatabaseConnectionDAO dbconn = null;

  private static Connection conn = null;

  private DatabaseConnectionDAO() {
    // Costruttore privato
  }

  static DatabaseConnectionDAO getInstance() {
    if (dbconn == null) {
      dbconn = new DatabaseConnectionDAO();
    }
    return dbconn;
  }

  static Connection getConnectionBySchema(final String schema) {
    String password = null;
    BufferedReader buffer = null;

    if (Objects.equals(schema, "")) {
      throw new RuntimeException("Schema name is empty");
    }
    try {
      if (conn == null || conn.isClosed()) {
        InputStream is = DatabaseConnectionDAO.class.getResourceAsStream("/password.txt");
        if (is == null) {
          throw new FileNotFoundException("File password.txt not found");
        }
        buffer = new BufferedReader(new InputStreamReader(is));
        password = buffer.readLine();
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/" + schema;
        conn = DriverManager.getConnection(url, "postgres", password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("File password.txt not found", e);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (buffer != null) {
        try {
          buffer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return conn;
  }
}