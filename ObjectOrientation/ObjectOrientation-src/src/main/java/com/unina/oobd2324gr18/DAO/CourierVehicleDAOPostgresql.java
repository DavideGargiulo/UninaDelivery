package com.unina.oobd2324gr18.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.unina.oobd2324gr18.DTO.CourierDTO;
import com.unina.oobd2324gr18.DTO.CourierVehicleDTO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;


public class CourierVehicleDAOPostgresql implements CourierVehicleDAO {

  private CourierVehicleDTO populateCourierVehicleFromResultSet(ResultSet rs) throws SQLException {
    return new CourierVehicleDTO(
      rs.getInt("courierVehicleId"),
      rs.getDouble("occupiedSpace"),
      rs.getBoolean("isAvailable"),
      rs.getString("licensePlate"),
      new CourierDTO(rs.getString("courierOwnerName"), rs.getString("courierOwnerVat")),
      null
    );
  }

  @Override
  public int insert(final CourierVehicleDTO courierVehicle) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public List<CourierVehicleDTO> findAll() throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int update(final CourierVehicleDTO courierVehicle) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int delete(final CourierVehicleDTO courierVehicle) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public CourierVehicleDTO getCourierVehicleById(int id) throws SQLException {
    String sql = "SELECT * FROM CourierVehicle WHERE courierVehicleId = ?";
    CourierVehicleDTO vehicle = null;

    try (Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        vehicle = populateCourierVehicleFromResultSet(rs);
      }
    } catch (SQLException e) {
      throw new SQLException("Errore durante l'accesso al database: " + e.getMessage());
    }
    return vehicle;
  }

  @Override
  public List<CourierVehicleDTO> getCourierVehicleByWarehouse(WarehouseDTO warehouse) throws SQLException {
    String sql = "SELECT * FROM CourierVehicle WHERE warehouseId = ?";
    List<CourierVehicleDTO> vehicles = new ArrayList<>();

    try (Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, warehouse.getId());
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        CourierVehicleDTO vehicle = new CourierVehicleDTO(
          rs.getInt("courierVehicleId"),
          rs.getDouble("occupiedSpace"),
          rs.getBoolean("isAvailable"),
          rs.getString("licensePlate"),
          new CourierDTO(rs.getString("courierOwnerName"), rs.getString("courierOwnerVat")),
          warehouse
        );
        vehicles.add(vehicle);
      }
    } catch (SQLException e) {
      throw new SQLException("Errore durante l'accesso al database: " + e.getMessage());
    }
    return vehicles;
  }

  @Override
  public List<CourierVehicleDTO> getCompatibleVehicles(double occupiedSpace, WarehouseDTO warehouse, LocalDate data) throws SQLException {
    String sql = "SELECT * FROM CourierVehicle WHERE occupiedSpace >= ? AND warehouseId = ? AND isAvailable = true";
    List<CourierVehicleDTO> vehicles = new ArrayList<>();

    try (Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setDouble(1, occupiedSpace);
      pstmt.setInt(2, warehouse.getId());
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        CourierVehicleDTO vehicle = new CourierVehicleDTO(
          rs.getInt("courierVehicleId"),
          rs.getDouble("occupiedSpace"),
          rs.getBoolean("isAvailable"),
          rs.getString("licensePlate"),
          new CourierDTO(rs.getString("courierOwnerName"), rs.getString("courierOwnerVat")),
          warehouse
        );
        vehicles.add(vehicle);
      }
    } catch (SQLException e) {
      throw new SQLException("Errore durante l'accesso al database: " + e.getMessage());
    }
    return vehicles;
  }

}