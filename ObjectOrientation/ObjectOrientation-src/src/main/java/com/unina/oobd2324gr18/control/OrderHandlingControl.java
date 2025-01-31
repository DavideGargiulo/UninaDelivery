package com.unina.oobd2324gr18.control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

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
import com.unina.oobd2324gr18.DTO.CourierVehicleDTO;
import com.unina.oobd2324gr18.DTO.DriverDTO;
import com.unina.oobd2324gr18.DTO.OperatorDTO;
import com.unina.oobd2324gr18.DTO.ShipmentDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public final class OrderHandlingControl extends NonLoginControl {

  private OrderDAO orderdao = new OrderDAOPostgresql();
  private ShipmentDAO shipmentdao = new ShipmentDAOPostgresql();
  private WarehouseDAO warehousedao = new WarehouseDAOPostgresql();
  private CourierDAO courierdao = new CourierDAOPostgresql();
  private CourierVehicleDAO couriervehicledao = new CourierVehicleDAOPostgresql();
  private AccountDAO accountdao = new AccountDAOPostgresql();

  private static final Logger logger = Logger.getLogger(OrderHandlingControl.class.getName());

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

  public void goToOrdersPage() {
    try {
      setScene("Orders");
    } catch (Exception e) {
      handleGeneralError(e);
    }
  }

  // Metodo shipSelectedOrder con ShipmentDTO
  public void shipSelectedOrder(final ShipmentDTO shipment) {
    try {
      if (!confirmShipment(shipment)) {
        return;  // Esci se la spedizione non è confermata
      }

      processShipment(shipment);  // Effettua la spedizione
      showSuccessModal();  // Mostra messaggio di successo

    } catch (SQLException e) {
      handleDatabaseError(e);
    } catch (Exception e) {
      handleGeneralError(e);
    }
  }

  // Metodo shipSelectedOrder con parametri per creazione spedizione
  public void shipSelectedOrder(final LocalDate shippingDate, final WarehouseDTO warehouse, final CourierVehicleDTO vehicle, final DriverDTO driver) {
    try {
      // Crea la spedizione
      ShipmentDTO shipment = createShipment(shippingDate, warehouse, vehicle);

      // Conferma e processa la spedizione
      if (confirmShipment(shippingDate)) {
        processShipment(shipment);
        assignDriver(shipment, driver);
        showSuccessModal();
      }
    } catch (SQLException e) {
      handleDatabaseError(e);
    } catch (Exception e) {
      handleGeneralError(e);
    }
  }

private ShipmentDTO createShipment(final LocalDate shippingDate, final WarehouseDTO warehouse, final CourierVehicleDTO vehicle) {
    if (warehouse == null || vehicle == null) {
        throw new IllegalArgumentException("Magazzino o veicolo non validi per la spedizione");
    }

    // Recupera l'operatore attualmente loggato
    OperatorDTO operator = Session.getOperatorLogged();

    // Verifica la presenza di un operatore valido
    if (operator == null) {
        throw new IllegalStateException("Nessun operatore loggato per la spedizione");
    }

    // Usa il costruttore corretto per ShipmentDTO
    return new ShipmentDTO(
        shippingDate,      // Data di spedizione
        operator,          // Operatore che gestisce la spedizione
        warehouse,         // Magazzino di partenza
        vehicle            // Veicolo del corriere
    );
}


  // Conferma la spedizione
  private boolean confirmShipment(ShipmentDTO shipment) {
    // Conferma la spedizione e controlla le date
    if (!isShipmentConfirmed(shipment)) {
      return false;
    }
    return true;
  }

  private boolean confirmShipment(LocalDate shippingDate) {
    return isShipmentConfirmed(shippingDate);
  }

  // Processa la spedizione
  private void processShipment(ShipmentDTO shipment) throws SQLException {
    // Spedisce l'ordine e aggiorna il database
    shipmentdao.shipOrder(Session.getSelectedOrder(), shipment);
  }

  // Assegna il conducente al corriere
  private void assignDriver(ShipmentDTO shipment, DriverDTO driver) throws SQLException {
    shipmentdao.assignDriver(shipment, driver);
  }

  // Conferma con l'utente e controlla le date di spedizione
  private boolean isShipmentConfirmed(final ShipmentDTO shipment) {
    String message = createConfirmationMessage(shipment);
    return confirmWithUser(message) && validateShippingDate(shipment);
  }

  private boolean isShipmentConfirmed(final LocalDate date) {
    String message = createConfirmationMessage(date);
    return confirmWithUser(message);
  }

  private boolean confirmWithUser(String message) {
    Optional<ButtonType> modalResponse = showAlert(
      Alert.AlertType.CONFIRMATION, "Conferma", "Conferma la spedizione", message
    );
    return modalResponse.isPresent() && modalResponse.get() == ButtonType.OK;
  }

  private boolean validateShippingDate(ShipmentDTO shipment) {
    LocalDate shippingDate = shipment.getShipmentDate();
    LocalDate expectedDeliveryDate = Session.getSelectedOrder().getDeliveryDate();

    if (shippingDate.isAfter(expectedDeliveryDate)) {
      showAlert(
        Alert.AlertType.ERROR, "Errore", "Data di spedizione non valida",
        "La data di spedizione non può essere successiva alla data di consegna prevista"
      );
      return false;
    }
    return true;
  }

  // Messaggio di conferma per la spedizione
  private String createConfirmationMessage(final ShipmentDTO shipment) {
    return "Confermi la spedizione dell'ordine " +
           shipment.getShipmentId() + " a " + shipment.getShipmentDate() + "?";
  }

  private String createConfirmationMessage(final LocalDate shippingDate) {
    return "Confermi la spedizione per il giorno " + shippingDate + "?";
  }

  // Inserisci la spedizione nel database
  public void insertShipment(final ShipmentDTO shipment) throws SQLException {
    if (shipment == null || shipment.getShipmentId() == 0) {
      throw new IllegalArgumentException("Spedizione non valida o già inserita");
    }
    shipment.setShipmentId(shipmentdao.insert(shipment));
  }

  // Gestione degli errori SQL
  private void handleDatabaseError(SQLException e) {
    logger.severe("Errore durante la spedizione dell'ordine: " + e.getMessage());
    showAlert(Alert.AlertType.ERROR, "Errore", "Errore durante la spedizione", "Errore con il database. Riprova più tardi.");
  }

  // Gestione degli errori generici
  private void handleGeneralError(Exception e) {
    logger.severe("Errore generale durante la spedizione: " + e.getMessage());
    showAlert(Alert.AlertType.ERROR, "Errore", "Errore durante la spedizione", "Si è verificato un errore durante la spedizione. Riprova più tardi.");
  }

  // Mostra il messaggio di successo
  private void showSuccessModal() {
    ButtonType orderButtonType = new ButtonType("Gestisci ordini");
    ButtonType homeButtonType = new ButtonType("Torna alla home");

    Optional<ButtonType> modalResponse = showAlert(
      Alert.AlertType.INFORMATION,
      "Successo",
      "Spedizione completata",
      "La spedizione è stata completata con successo",
      orderButtonType,
      homeButtonType
    );

    modalResponse.ifPresent(
      response -> {
        if (response == orderButtonType) {
          goToOrdersPage();
        } else {
          setPageName("Orders");
        }
      }
    );
  }
}