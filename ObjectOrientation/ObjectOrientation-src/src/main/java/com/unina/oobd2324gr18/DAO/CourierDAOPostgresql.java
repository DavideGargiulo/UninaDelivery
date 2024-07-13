package com.unina.oobd2324gr18.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unina.oobd2324gr18.DTO.CourierDTO;
import com.unina.oobd2324gr18.DTO.CourierVehicleDTO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;

/**
 * Implementazione dell'interfaccia CourierDAO per PostgreSQL.
 * Gestisce le operazioni CRUD sui corrieri e i loro veicoli nel database.
 *
 * @autor DavideGargiulo
 */
public class CourierDAOPostgresql implements CourierDAO {

  /**
   * Popola un oggetto CourierDTO a partire da un ResultSet.
   *
   * @param rs il ResultSet da cui ottenere i dati del corriere
   * @return un oggetto CourierDTO con i dati del corriere
   * @throws SQLException se si verifica un errore durante l'accesso al ResultSet
   */
  private CourierDTO populateCourierFromResultSet(ResultSet rs) throws SQLException {
    return new CourierDTO(
      rs.getString("name"),
      rs.getString("vat")
    );
  }

  /**
   * Inserisce un nuovo corriere nel database.
   *
   * @param courier l'oggetto CourierDTO da inserire
   * @return l'ID del corriere inserito
   * @throws SQLException se si verifica un errore durante l'inserimento
   */
  @Override
  public int insert(final CourierDTO courier) throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Recupera tutti i corrieri dal database.
   *
   * @return una lista di oggetti CourierDTO
   * @throws SQLException se si verifica un errore durante il recupero
   */
  @Override
  public List<CourierDTO> findAll() throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Elimina un corriere dal database.
   *
   * @param courier l'oggetto CourierDTO da eliminare
   * @return il numero di righe interessate
   * @throws SQLException se si verifica un errore durante l'eliminazione
   */
  @Override
  public int delete(final CourierDTO courier) throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Aggiorna un corriere nel database.
   *
   * @param courier l'oggetto CourierDTO con i nuovi dati
   * @return il numero di righe interessate
   * @throws SQLException se si verifica un errore durante l'aggiornamento
   */
  @Override
  public int update(final CourierDTO courier) throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Trova un corriere in base alla partita IVA.
   *
   * @param VAT la partita IVA del corriere da trovare
   * @return l'oggetto CourierDTO corrispondente alla partita IVA
   * @throws SQLException se si verifica un errore durante il recupero
   */
  @Override
  public CourierDTO findByVAT(String VAT) throws SQLException {
    String sql = "SELECT * FROM Courier WHERE vat = ?";
    CourierDTO courier = null;

    try (Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, VAT);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        courier = populateCourierFromResultSet(rs);
      }
    } catch (SQLException e) {
      throw new SQLException("Errore durante l'accesso al database: " + e.getMessage());
    }
    return courier;
  }

  /**
   * Recupera tutti i veicoli associati a un corriere specifico.
   *
   * @param courierID l'ID del corriere
   * @return una lista di oggetti CourierVehicleDTO
   * @throws SQLException se si verifica un errore durante il recupero
   */
  @Override
  public List<CourierVehicleDTO> getVehiclesByCourier(int courierID) throws SQLException {
    String sql = "SELECT * FROM CourierVehicle WHERE courierOwnerId = ?";
    List<CourierVehicleDTO> vehicles = new ArrayList<>();

    try (Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, courierID);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        CourierVehicleDTO vehicle = new CourierVehicleDTO(
          rs.getInt("courierVehicleId"),
          rs.getDouble("occupiedSpace"),
          rs.getBoolean("isAvailable"),
          rs.getString("licensePlate"),
          new CourierDTO(rs.getString("courierOwnerName"), rs.getString("courierOwnerVat")),
          // Assumendo che WarehouseDTO sia già stato recuperato e creato
          null // sostituire con il codice reale per ottenere WarehouseDTO
        );
        vehicles.add(vehicle);
      }
    } catch (SQLException e) {
      throw new SQLException("Errore durante l'accesso al database: " + e.getMessage());
    }
    return vehicles;
  }
}
