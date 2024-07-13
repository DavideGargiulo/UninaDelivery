package com.unina.oobd2324gr18.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarehouseDTO {

  private final int warehouseId;
  private float occupiedSpace;
  private float maxCapacity;
  private SavedAddressDTO address;
  private final List<CourierVehicleDTO> transports;
  private final List<StoredProduct> storedProducts;

  private WarehouseDTO(Builder builder) {
    this.warehouseId = builder.warehouseId;
    this.occupiedSpace = builder.occupiedSpace;
    this.maxCapacity = builder.maxCapacity;
    this.address = builder.address;
    this.transports = new ArrayList<>(builder.transports);
    this.storedProducts = new ArrayList<>(builder.storedProducts);
  }

  public static class Builder {
    private int warehouseId;
    private float occupiedSpace;
    private float maxCapacity;
    private SavedAddressDTO address;
    private List<CourierVehicleDTO> transports = new ArrayList<>();
    private List<StoredProduct> storedProducts = new ArrayList<>();

    public Builder withId(int warehouseId) {
      this.warehouseId = warehouseId;
      return this;
    }

    public Builder withOccupiedSpace(float occupiedSpace) {
      this.occupiedSpace = occupiedSpace;
      return this;
    }

    public Builder withMaxCapacity(float maxCapacity) {
      this.maxCapacity = maxCapacity;
      return this;
    }

    public Builder withAddress(SavedAddressDTO address) {
      this.address = address;
      return this;
    }

    public Builder withTransports(List<CourierVehicleDTO> transports) {
      this.transports = new ArrayList<>(transports);
      return this;
    }

    public Builder withStoredProducts(List<StoredProduct> storedProducts) {
      this.storedProducts = new ArrayList<>(storedProducts);
      return this;
    }

    public WarehouseDTO build() {
      return new WarehouseDTO(this);
    }
  }

  // Getter methods...

  public int getId() {
    return warehouseId;
  }

  public float getOccupiedSpace() {
    return occupiedSpace;
  }

  public void setOccupiedSpace(float occupiedSpace) {
    this.occupiedSpace = occupiedSpace;
  }

  public float getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(float maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public SavedAddressDTO getAddress() {
    return address;
  }

  public void setAddress(SavedAddressDTO address) {
    this.address = address;
  }

  public List<CourierVehicleDTO> getTransports() {
    return new ArrayList<>(transports);
  }

  public List<StoredProduct> getStoredProducts() {
    return new ArrayList<>(storedProducts);
  }

  public void addStoredProducts(final ProductDTO sProduct, final int sQuantity) {
    if (sProduct == null || sQuantity <= 0) {
      return;
    }

    for (StoredProduct sp : storedProducts) {
      if (sp.getProduct().equals(sProduct)) {
        sp.setQuantity(sp.getQuantity() + sQuantity);
        return;
      }
    }

    storedProducts.add(new StoredProduct(sProduct, sQuantity));
  }

  public void addTransport(final CourierVehicleDTO transport) {
    if (transport == null) {
      throw new IllegalArgumentException("Transport cannot be null");
    }

    if (!transports.contains(transport)) {
      transports.add(transport);
    }
  }

  // Inner util class needed to store quantity along with the product.
  public static class StoredProduct {
    private final ProductDTO product;
    private int quantity;

    public StoredProduct(ProductDTO product, int quantity) {
      this.product = product;
      this.quantity = quantity;
    }

    public ProductDTO getProduct() {
      return product;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      if (quantity <= 0) {
        throw new IllegalArgumentException("Quantity must be greater than 0");
      }
      this.quantity = quantity;
    }
  }

  @Override
  public String toString() {
    return "Deposito di " + getAddress().getCity() + ", CAP: " + getAddress().getZipCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WarehouseDTO deposit = (WarehouseDTO) o;
    return warehouseId == deposit.warehouseId &&
      Float.compare(deposit.occupiedSpace, occupiedSpace) == 0 &&
      Float.compare(deposit.maxCapacity, maxCapacity) == 0 &&
      Objects.equals(address, deposit.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(warehouseId, occupiedSpace, maxCapacity, address);
  }
}