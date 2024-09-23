package com.unina.oobd2324gr18.control;

import com.unina.oobd2324gr18.DAO.AccountDAO;
import com.unina.oobd2324gr18.DAO.AccountDAOPostgresql;
import com.unina.oobd2324gr18.DAO.CourierDAO;
import com.unina.oobd2324gr18.DAO.CourierDAOPostgresql;
import com.unina.oobd2324gr18.DAO.CourierVehicleDAO;
import com.unina.oobd2324gr18.DAO.CourierVehicleDAOPostgresql;
import com.unina.oobd2324gr18.DAO.OrderDAO;
import com.unina.oobd2324gr18.DAO.OrderDAOPostgresql;
import com.unina.oobd2324gr18.DAO.ShipmentDAO;
import com.unina.oobd2324gr18.DAO.ShipmentDAOPostgresql;
import com.unina.oobd2324gr18.DAO.WarehouseDAO;
import com.unina.oobd2324gr18.DAO.WarehouseDAOPostgresql;
import com.unina.oobd2324gr18.DTO.CourierDTO;
import com.unina.oobd2324gr18.DTO.CourierVehicleDTO;
import com.unina.oobd2324gr18.DTO.DriverDTO;
import com.unina.oobd2324gr18.DTO.OrderDTO;
import com.unina.oobd2324gr18.DTO.SavedAddressDTO;
import com.unina.oobd2324gr18.DTO.ShipmentDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public final class OrderHandlingControl extends NonLoginControl {

  private OrderDAO orderdao = new OrderDAOPostgresql();

  private ShipmentDAO shipmentdao = new ShipmentDAOPostgresql();

  private WarehouseDAO warehousedao = new WarehouseDAOPostgresql();

  private CourierDAO courierdao = new CourierDAOPostgresql();

  private CourierVehicleDAO couriervehicledao =
    new CourierVehicleDAOPostgresql();

  private AccountDAO accountdao = new AccountDAOPostgresql();

  private static OrderHandlingControl instance;

  private OrderHandlingControl(final String pageName) {
    super(pageName);
  }

  public static OrderHandlingControl getInstance() {
    if (instance == null) {
      instance = new OrderHandlingControl("Order");
    }
    return instance;
  }

  public void goToShipmentPage() {
    // TODO
  }

  private boolean warehouseHaveEnoughProduct(final OrderDAO order) {
    // TODO
    return false;
  }

  public void goToOrderPage() {
    // TODO
  }

  public boolean isEmailValid(final String email) {
    // TODO
    return false;
  }

  public OrderDAO getOrder() {
    return orderdao;
  }

  public void shipSelectedOrder(final ShipmentDAO shipment) {
    // TODO
  }

  public void shipSelectedOrder(final OrderDTO order) {
    // TODO
  }

  private ShipmentDTO createShipment(final OrderDTO order) {
    // TODO
    return null;
  }

  public void insertShipment(final ShipmentDTO shipment) {
    // TODO
  }

  public void shipOrder(final OrderDTO order) {
    // TODO
  }

  public void assignDriverToCourier(
    final DriverDTO driver,
    final CourierDTO courier
  ) {
    // TODO
  }

  private String createConfirmationMessage(final LocalDate shippingDate) {
    // TODO
    return null;
  }

  private String createConfirmationMessage(final ShipmentDAO shipment) {
    // TODO
    return null;
  }

  private boolean isShipmentConfirmed(final ShipmentDAO shipment) {
    // TODO
    return false;
  }

  private boolean isShipmentConfirmed(final LocalDate date) {
    // TODO
    return false;
  }

  private void showSuccessModal() {
    // TODO
  }
}
