package com.unina.oobd2324gr18.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.unina.oobd2324gr18.utils.MethodNotImplemented;
import com.unina.oobd2324gr18.DTO.AccountDTO;
import com.unina.oobd2324gr18.DTO.OrderDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;
import com.unina.oobd2324gr18.DAO.DatabaseConnectionDAO;
import com.unina.oobd2324gr18.DTO.SavedAddressDTO;

public class WarehouseDAOPostgresql implements WarehouseDAO {

  @Override
  public int insert(WarehouseDTO entity) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public List<WarehouseDTO> findAll() throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int update(WarehouseDTO entity) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int delete(WarehouseDTO entity) throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Metodo per trovare un magazzino in base al suo ID.
   *
   * @param id L'ID del magazzino da trovare.
   * @return Un oggetto WarehouseDTO che rappresenta il magazzino trovato, oppure null se non viene trovato alcun magazzino.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  @Override
  public WarehouseDTO findWarehouseById(int id) throws SQLException {
    Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    String query = "SELECT * FROM warehouses WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new WarehouseDTO.Builder()
          .withId(rs.getInt("id"))
          .withOccupiedSpace(rs.getFloat("occupied_space"))
          .withMaxCapacity(rs.getFloat("max_capacity"))
          .withAddress(new SavedAddressDTO(rs.getString("city"), rs.getString("province"), rs.getInt("zipCode"), rs.getString("street"), rs.getInt("civicNumber"), rs.getString("state")))
          .build();
      }
    }
    return null;
  }

  /**
   * Metodo per trovare tutti i magazzini in base alla città.
   *
   * @param city La città in cui cercare i magazzini.
   * @return Una lista di oggetti WarehouseDTO che rappresentano i magazzini trovati nella città specificata.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  @Override
  public List<WarehouseDTO> findWarehousesByCity(String city) throws SQLException {
    Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    String query = "SELECT * FROM warehouses WHERE city = ?";
    List<WarehouseDTO> warehouses = new ArrayList<>();
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, city);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        warehouses.add(new WarehouseDTO.Builder()
        .withId(rs.getInt("id"))
        .withOccupiedSpace(rs.getFloat("occupied_space"))
        .withMaxCapacity(rs.getFloat("max_capacity"))
        .withAddress(new SavedAddressDTO(rs.getString("city"), rs.getString("province"), rs.getInt("zipCode"), rs.getString("street"), rs.getInt("civicNumber"), rs.getString("state")))
        .build());
      }
    }
    return warehouses;
  }

  /**
   * Metodo per trovare tutti i magazzini in base alla regione.
   *
   * @param region La regione in cui cercare i magazzini.
   * @return Una lista di oggetti WarehouseDTO che rappresentano i magazzini trovati nella regione specificata.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  @Override
  public List<WarehouseDTO> findWarehousesByRegion(String region) throws SQLException {
    Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    String query = "SELECT * FROM warehouses WHERE region = ?";
    List<WarehouseDTO> warehouses = new ArrayList<>();
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, region);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        warehouses.add(new WarehouseDTO.Builder()
        .withId(rs.getInt("id"))
        .withOccupiedSpace(rs.getFloat("occupied_space"))
        .withMaxCapacity(rs.getFloat("max_capacity"))
        .withAddress(new SavedAddressDTO(rs.getString("city"), rs.getString("province"), rs.getInt("zipCode"), rs.getString("street"), rs.getInt("civicNumber"), rs.getString("state")))
        .build());
      }
    }
    return warehouses;
  }

  /**
   * Metodo per trovare tutti i magazzini in base al paese.
   *
   * @param country Il paese in cui cercare i magazzini.
   * @return Una lista di oggetti WarehouseDTO che rappresentano i magazzini trovati nel paese specificato.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  @Override
  public List<WarehouseDTO> findWarehousesByCountry(String country) throws SQLException {
    Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    String query = "SELECT * FROM warehouses WHERE country = ?";
    List<WarehouseDTO> warehouses = new ArrayList<>();
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, country);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        warehouses.add(new WarehouseDTO.Builder()
        .withId(rs.getInt("id"))
        .withOccupiedSpace(rs.getFloat("occupied_space"))
        .withMaxCapacity(rs.getFloat("max_capacity"))
        .withAddress(new SavedAddressDTO(rs.getString("city"), rs.getString("province"), rs.getInt("zipCode"), rs.getString("street"), rs.getInt("civicNumber"), rs.getString("state")))
        .build());
      }
    }
    return warehouses;
  }

  /**
   * Metodo per trovare tutti i magazzini in base alla capacità.
   *
   * @param capacity La capacità minima richiesta per i magazzini.
   * @return Una lista di oggetti WarehouseDTO che rappresentano i magazzini con capacità maggiore o uguale a quella specificata.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  @Override
  public List<WarehouseDTO> findWarehousesByCapacity(int capacity) throws SQLException {
    Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    String query = "SELECT * FROM warehouses WHERE capacity >= ?";
    List<WarehouseDTO> warehouses = new ArrayList<>();
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, capacity);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        warehouses.add(new WarehouseDTO.Builder()
        .withId(rs.getInt("id"))
        .withOccupiedSpace(rs.getFloat("occupied_space"))
        .withMaxCapacity(rs.getFloat("max_capacity"))
        .withAddress(new SavedAddressDTO(rs.getString("city"), rs.getString("province"), rs.getInt("zipCode"), rs.getString("street"), rs.getInt("civicNumber"), rs.getString("state")))
        .build());
      }
    }
    return warehouses;
  }

  /**
   * Metodo per trovare tutti i magazzini compatibili per una certa spedizione.
   *
   * @param date La data in cui cercare i magazzini.
   * @return Una lista di oggetti WarehouseDTO che rappresentano i magazzini disponibili nella data specificata.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  @Override
  public List<WarehouseDTO> getCompatibleDeposits(OrderDTO order, LocalDate date) throws SQLException {
    // Connection conn = DatabaseConnectionDAO.getInstance().getConnectionBySchema("uninadelivery");
    // String query = "SELECT * FROM warehouses WHERE capacity >= ? AND city = ?";
    // List<WarehouseDTO> warehouses = new ArrayList<>();
    // try (PreparedStatement stmt = conn.prepareStatement(query)) {
    //   stmt.setInt(1, order.getRequiredCapacity());
    //   stmt.setString(2, order.getCity());
    //   ResultSet rs = stmt.executeQuery();
    //   while (rs.next()) {
    //     warehouses.add(new WarehouseDTO.Builder()
    //     .withId(rs.getInt("id"))
    //     .withOccupiedSpace(rs.getFloat("occupied_space"))
    //     .withMaxCapacity(rs.getFloat("max_capacity"))
    //     .withAddress(new SavedAddressDTO(rs.getString("city"), rs.getString("province"), rs.getInt("zipCode"), rs.getString("street"), rs.getInt("civicNumber"), rs.getString("state")))
    //     .build());
    //   }
    // }
    // return warehouses;
    throw new MethodNotImplemented();
  }
}