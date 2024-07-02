package com.unina.oobd2324gr18.DTO;

import java.time.LocalDate;

/**
 * Classe che rappresenta un ordine, contenente dettagli come l'ID dell'ordine,
 * la data dell'ordine, il cliente, la quantità, il costo totale, lo stato di consegna,
 * la data di consegna, il prezzo unitario, il tasso di tassazione e lo stato di consegna espressa.
 *
 * @author DavideGargiulo
 */
public class OrderDTO {
  private int orderId;
  private LocalDate orderDate;
  private AccountDTO customer;
  private int quantity;
  private double totalCost;
  private boolean isDelivered;
  private LocalDate deliveryDate;
  private ProductDTO product;

  private static final int standardDeliveryTime = 5; // Tempo di consegna standard in giorni
  private static final int expressDeliveryTime = 2; // Tempo di consegna espresso in giorni

  private double unitPrice; // Prezzo per unità
  private double taxRate; // Tasso di tassazione
  private boolean isExpressDelivery; // Stato della consegna espressa

  /**
   * Costruttore della classe OrderDTO.
   *
   * @param orderId ID dell'ordine.
   * @param orderDate Data dell'ordine.
   * @param customer Cliente che ha effettuato l'ordine.
   * @param quantity Quantità di prodotti ordinati.
   * @param unitPrice Prezzo per unità del prodotto.
   * @param taxRate Tasso di tassazione applicato.
   * @param isExpressDelivery Indica se la consegna è espressa.
   * @param isDelivered Indica se l'ordine è stato consegnato.
   * @param deliveryDate Data di consegna dell'ordine.
   */
  public OrderDTO(final int orderId, final LocalDate orderDate, final AccountDTO customer, final int quantity, final double unitPrice, final double taxRate, final boolean isExpressDelivery, final boolean isDelivered, final LocalDate deliveryDate) {
    this.orderId = orderId;
    this.orderDate = orderDate;
    this.customer = customer;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.taxRate = taxRate;
    this.isExpressDelivery = isExpressDelivery;
    this.isDelivered = isDelivered;
    this.deliveryDate = deliveryDate;
    this.totalCost = calculateTotalCost();
  }

  /**
   * Calcola il costo totale dell'ordine.
   *
   * @return Il costo totale dell'ordine.
   */
  private double calculateTotalCost() {
    double productCost = unitPrice * quantity;
    double tax = productCost * taxRate;
    double deliveryCharge = isExpressDelivery ? 15.0 : 5.0;
    return productCost + tax + deliveryCharge;
  }

  // Getter e Setter

  public int getOrderId() {
    return orderId;
  }

  public ProductDTO getProduct() {
    return product;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  public AccountDTO getCustomer() {
    return customer;
  }

  public void setCustomer(AccountDTO customer) {
    this.customer = customer;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public boolean isDelivered() {
    return isDelivered;
  }

  public void setDelivered(boolean delivered) {
    isDelivered = delivered;
  }

  public LocalDate getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public double getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(double taxRate) {
    this.taxRate = taxRate;
  }

  public boolean isExpressDelivery() {
    return isExpressDelivery;
  }

  public void setExpressDelivery(boolean expressDelivery) {
    isExpressDelivery = expressDelivery;
  }

  /**
   * Restituisce una rappresentazione in formato stringa dell'oggetto OrderDTO.
   *
   * @return Una stringa che rappresenta l'oggetto OrderDTO.
   */
  @Override
  public String toString() {
    return "OrderDTO{" +
            "orderId=" + orderId +
            ", orderDate=" + orderDate +
            ", customer=" + customer +
            ", quantity=" + quantity +
            ", totalCost=" + totalCost +
            ", isDelivered=" + isDelivered +
            ", deliveryDate=" + deliveryDate +
            ", unitPrice=" + unitPrice +
            ", taxRate=" + taxRate +
            ", isExpressDelivery=" + isExpressDelivery +
            '}';
  }
}