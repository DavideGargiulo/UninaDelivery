package com.unina.oobd2324gr18.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import com.unina.oobd2324gr18.DTO.WarehouseDTO.StoredProduct;;

/**
 * Classe che rappresenta una spedizione, contenente dettagli come l'ID della spedizione,
 * la data della spedizione, lo stato di consegna, l'operatore, il magazzino di partenza,
 * gli ordini inclusi nella spedizione, il veicolo utilizzato e lo spazio occupato.
 *
 * @author DavideGargiulo
 */
public class ShipmentDTO {
  private int shipmentId;

  private LocalDate shipmentDate;

  private boolean isDelivered;

  private OperatorDTO operator;

  private WarehouseDTO startingWarehouse;

  private List<OrderDTO> orders = new ArrayList<>();

  private CourierVehicleDTO vehicle;

  private double occupiedSpace;

  /**
   * Costruttore completo della classe ShipmentDTO.
   *
   * @param shipmentId ID univoco della spedizione.
   * @param shipmentDate Data della spedizione.
   * @param operator Operatore che gestisce la spedizione.
   * @param startingWarehouse Magazzino di partenza della spedizione.
   * @param vehicle Veicolo utilizzato per la spedizione.
   * @param occupiedSpace Spazio occupato dalla spedizione nel veicolo.
   */
  public ShipmentDTO(final int shipmentId, final LocalDate shipmentDate, final OperatorDTO operator, final WarehouseDTO startingWarehouse, final CourierVehicleDTO vehicle, final double occupiedSpace) {
    this.shipmentId = shipmentId;
    this.shipmentDate = shipmentDate;
    this.isDelivered = false;
    this.operator = operator;
    this.startingWarehouse = startingWarehouse;
    this.occupiedSpace = occupiedSpace;
    if (!vehicle.getWorksFor().equals(startingWarehouse)) {
      throw new IllegalArgumentException("The vehicle must work for the starting warehouse");
    } else {
      this.vehicle = vehicle;
    }
  }

  public ShipmentDTO(final LocalDate shipmentDate, final OperatorDTO operator, final WarehouseDTO startingWarehouse, final CourierVehicleDTO vehicle) {
    this.shipmentDate = shipmentDate;
    this.isDelivered = false;
    this.operator = operator;
    this.startingWarehouse = startingWarehouse;
    if (!vehicle.getWorksFor().equals(startingWarehouse)) {
      throw new IllegalArgumentException("The vehicle must work for the starting warehouse");
    } else {
      this.vehicle = vehicle;
    }
  }

  //quick lookup
  public ShipmentDTO(final int shipmentId, final LocalDate shipmentDate, final WarehouseDTO startingWarehouse, final CourierVehicleDTO vehicle, final double occupiedSpace) {
    this.shipmentId = shipmentId;
    this.shipmentDate = shipmentDate;
    this.isDelivered = false;
    this.startingWarehouse = startingWarehouse;
    this.occupiedSpace = occupiedSpace;
    if (!vehicle.getWorksFor().equals(startingWarehouse)) {
      throw new IllegalArgumentException("The vehicle must work for the starting warehouse");
    } else {
      this.vehicle = vehicle;
    }
  }

  /**
   * Restituisce l'ID della spedizione.
   *
   * @return ID della spedizione.
   */
  public int getShipmentId() {
    return shipmentId;
  }

  /**
   * Imposta l'ID della spedizione.
   *
   * @param shipmentId ID della spedizione da impostare.
   */
  public void setShipmentId(int shipmentId) {
    this.shipmentId = shipmentId;
  }

  /**
   * Restituisce la data della spedizione.
   *
   * @return Data della spedizione.
   */
  public LocalDate getShipmentDate() {
    return shipmentDate;
  }

  /**
   * Imposta la data della spedizione.
   *
   * @param shipmentDate Data della spedizione da impostare.
   */
  public void setShipmentDate(LocalDate shipmentDate) {
    this.shipmentDate = shipmentDate;
  }

  /**
   * Restituisce lo stato di consegna della spedizione.
   *
   * @return Stato di consegna della spedizione.
   */
  public boolean isDelivered() {
    return isDelivered;
  }

  /**
   * Imposta lo stato di consegna della spedizione.
   *
   * @param delivered Stato di consegna della spedizione da impostare.
   */
  public void setDelivered(boolean delivered) {
    isDelivered = delivered;
  }

  /**
   * Restituisce l'operatore che gestisce la spedizione.
   *
   * @return Operatore che gestisce la spedizione.
   */
  public OperatorDTO getOperator() {
    return operator;
  }

  /**
   * Imposta l'operatore che gestisce la spedizione.
   *
   * @param operator Operatore che gestisce la spedizione da impostare.
   */
  public void setOperator(OperatorDTO operator) {
    this.operator = operator;
  }

  /**
   * Restituisce il magazzino di partenza della spedizione.
   *
   * @return Magazzino di partenza della spedizione.
   */
  public WarehouseDTO getStartingWarehouse() {
    return startingWarehouse;
  }

  /**
   * Imposta il magazzino di partenza della spedizione.
   *
   * @param startingWarehouse Magazzino di partenza della spedizione da impostare.
   */
  public void setStartingWarehouse(WarehouseDTO startingWarehouse) {
    this.startingWarehouse = startingWarehouse;
  }

  /**
   * Restituisce la lista degli ordini inclusi nella spedizione.
   *
   * @return Lista degli ordini inclusi nella spedizione.
   */
  public List<OrderDTO> getOrders() {
    return orders;
  }

  /**
   * Imposta la lista degli ordini inclusi nella spedizione.
   *
   * @param orders Lista degli ordini inclusi nella spedizione da impostare.
   */
  public void setOrders(List<OrderDTO> orders) {
    this.orders = orders;
  }

  /**
   * Restituisce il veicolo utilizzato per la spedizione.
   *
   * @return Veicolo utilizzato per la spedizione.
   */
  public CourierVehicleDTO getVehicle() {
    return vehicle;
  }

  /**
   * Imposta il veicolo utilizzato per la spedizione.
   *
   * @param vehicle Veicolo utilizzato per la spedizione da impostare.
   */
  public void setVehicle(CourierVehicleDTO vehicle) {
    this.vehicle = vehicle;
  }

  /**
   * Restituisce lo spazio occupato dalla spedizione nel veicolo.
   *
   * @return Spazio occupato dalla spedizione nel veicolo.
   */
  public double getOccupiedSpace() {
    return occupiedSpace;
  }

  /**
   * Imposta lo spazio occupato dalla spedizione nel veicolo.
   *
   * @param occupiedSpace Spazio occupato dalla spedizione nel veicolo da impostare.
   */
  public void setOccupiedSpace(double occupiedSpace) {
    this.occupiedSpace = occupiedSpace;
  }

  /**
   * Aggiunge un ordine alla spedizione.
   *
   * @param order Ordine da aggiungere alla spedizione.
   */
  public void addOrder(final OrderDTO order) {
    if (order == null) {
      throw new IllegalArgumentException("The order cannot be null");
    }

    if (getOrders().contains(order)) {
      return;
    }

    StoredProduct matchingProduct = findMatchingProduct(order);

    getOrders().add(order);
    matchingProduct.setQuantity(matchingProduct.getQuantity() - order.getQuantity());
  }

  /**
   * Cerca un prodotto corrispondente all'ordine nel magazzino di partenza.
   *
   * @param order Ordine per cui cercare il prodotto corrispondente.
   * @return Il prodotto corrispondente trovato.
   * @throws IllegalArgumentException se non viene trovato nessun prodotto corrispondente.
   */
  private StoredProduct findMatchingProduct(final OrderDTO order) {
    for (StoredProduct storedProduct : getStartingWarehouse().getStoredProducts()) {
      if (storedProduct.getProduct().equals(order.getProduct()) && storedProduct.getQuantity() >= order.getQuantity()) {
        return storedProduct;
      }
    }
    throw new IllegalArgumentException("No matching product found in the starting deposit");
  }

  @Override
  public String toString() {
    return "ShipmentDTO{" +
        "shipmentId=" + shipmentId +
        ", shipmentDate=" + shipmentDate +
        ", isDelivered=" + isDelivered +
        ", operator=" + operator +
        ", startingWarehouse=" + startingWarehouse +
        ", orders=" + orders +
        ", vehicle=" + vehicle +
        ", occupiedSpace=" + occupiedSpace +
        '}';
  }
}