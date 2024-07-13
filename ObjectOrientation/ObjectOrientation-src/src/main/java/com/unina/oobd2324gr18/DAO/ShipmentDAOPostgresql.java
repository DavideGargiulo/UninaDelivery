package com.unina.oobd2324gr18.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.unina.oobd2324gr18.DTO.CourierDTO;
import com.unina.oobd2324gr18.DTO.CourierVehicleDTO;
import com.unina.oobd2324gr18.DTO.OrderDTO;
import com.unina.oobd2324gr18.DTO.ShipmentDTO;
import com.unina.oobd2324gr18.DAO.WarehouseDAO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;
import com.unina.oobd2324gr18.DAO.DatabaseConnectionDAO;

public class ShipmentDAOPostgresql implements ShipmentDAO{

  private ShipmentDTO populateShipmentFromResultSet(final ResultSet resultSet) throws SQLException {
    // CourierVehicleDAO courierVehicle = new CourierVehicleDAO();
    // WarehouseDAO warehouse = new WarehouseDAO();

    // return new ShipmentDTO(
    //     resultSet.getInt("shipmentId"),
    //     resultSet.getDate("shipmentDate").toLocalDate(),
    //     resultSet.getWa
    return null;
  }

  @Override
  public ShipmentDTO findByTrackingCode(OrderDTO order, ShipmentDTO shipment) throws SQLException {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;

    try {
      conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      String sql = "SELECT * FROM shipments WHERE shipmentId = ? AND orderId = ?";
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, shipment.getShipmentId());
      stmt.setInt(2, order.getOrderId());

      resultSet = stmt.executeQuery();

      if (resultSet.next()) {
        return populateShipmentFromResultSet(resultSet);
      } else {
        return null;
      }
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        conn.close();
      }
    }
  }

  @Override
  public int assignDriver(ShipmentDTO shipment, String driver) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int shipOrder(OrderDTO order, ShipmentDTO shipment) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public List<ShipmentDTO> getCompatibleShipment(OrderDTO order) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int insert(ShipmentDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public List<ShipmentDTO> findAll() throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int update(ShipmentDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int delete(ShipmentDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }
}