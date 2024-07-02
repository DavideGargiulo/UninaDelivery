package com.unina.oobd2324gr18.DTO;

/**
 * Classe che rappresenta il veicolo di un corriere, contenente dettagli come l'ID,
 * lo spazio occupato, la disponibilità, la targa, il proprietario del corriere e
 * il magazzino per cui lavora.
 *
 * @author DavideGargiulo
 */
public class CourierVehicleDTO {
  private int courierVehicleId;

  private double occupiedSpace;

  private static final double maxCapacity = 100.0;

  private boolean isAvailable;

  private String licensePlate;

  private CourierDTO courierOwner;

  private WarehouseDTO worksFor;

  /**
   * Costruttore della classe CourierVehicleDTO.
   * Inizializza un nuovo veicolo del corriere con i dettagli specificati.
   *
   * @param courierVehicleId Identificativo univoco del veicolo.
   * @param occupiedSpace Spazio attualmente occupato nel veicolo.
   * @param isAvailable Stato di disponibilità del veicolo.
   * @param licensePlate Targa del veicolo.
   * @param courierOwner Proprietario del veicolo (corriere).
   * @param worksFor Magazzino per cui il veicolo sta lavorando.
   */
  public CourierVehicleDTO(final int courierVehicleId, final double occupiedSpace, final boolean isAvailable, final String licensePlate, final CourierDTO courierOwner, final WarehouseDTO worksFor) {
    if (worksFor == null) {
      throw new IllegalArgumentException("worksFor cannot be null");
    }
    this.courierVehicleId = courierVehicleId;
    this.occupiedSpace = occupiedSpace;
    this.isAvailable = isAvailable;
    this.licensePlate = licensePlate;
    this.courierOwner = courierOwner;
    this.worksFor = worksFor;
  }

  /**
   * Restituisce l'identificativo del veicolo.
   *
   * @return Identificativo del veicolo.
   */
  public int getCourierVehicleId() {
    return courierVehicleId;
  }

  /**
   * Restituisce lo spazio occupato nel veicolo.
   *
   * @return Spazio occupato.
   */
  public double getOccupiedSpace() {
    return occupiedSpace;
  }

  /**
   * Imposta lo spazio occupato nel veicolo.
   *
   * @param occupiedSpace Spazio occupato.
   */
  public void setOccupiedSpace(double occupiedSpace) {
    this.occupiedSpace = occupiedSpace;
  }

  /**
   * Restituisce lo stato di disponibilità del veicolo.
   *
   * @return Stato di disponibilità.
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Imposta lo stato di disponibilità del veicolo.
   *
   * @param available Stato di disponibilità.
   */
  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  /**
   * Restituisce la targa del veicolo.
   *
   * @return Targa del veicolo.
   */
  public String getLicensePlate() {
    return licensePlate;
  }

  /**
   * Imposta la targa del veicolo.
   *
   * @param licensePlate Targa del veicolo.
   */
  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  /**
   * Restituisce il corriere proprietario del veicolo.
   *
   * @return Corriere proprietario del veicolo.
   */
  public CourierDTO getCourierOwner() {
    return courierOwner;
  }

  /**
   * Imposta il corriere proprietario del veicolo.
   *
   * @param courierOwner Corriere proprietario del veicolo.
   */
  public void setCourierOwner(CourierDTO courierOwner) {
    this.courierOwner = courierOwner;
  }

  /**
   * Restituisce il magazzino per cui il veicolo sta lavorando.
   *
   * @return Magazzino per cui il veicolo sta lavorando.
   */
  public WarehouseDTO getWorksFor() {
    return worksFor;
  }

  /**
   * Imposta il magazzino per cui il veicolo sta lavorando.
   *
   * @param worksFor Magazzino per cui il veicolo sta lavorando.
   */
  public void setWorksFor(WarehouseDTO worksFor) {
    this.worksFor = worksFor;
  }

  /**
   * Restituisce la capacità massima del veicolo.
   *
   * @return Capacità massima del veicolo.
   */
  public double getMaxCapacity() {
    return maxCapacity;
  }

  /**
   * Imposta la capacità massima del veicolo.
   *
   * @param maxCapacity Capacità massima del veicolo.
   */
  public void setMaxCapacity(final double maxCapacity) {
    if (maxCapacity < 0) {
      throw new IllegalArgumentException("Space cannot be negative");
    }
    this.occupiedSpace = maxCapacity;
  }

  /**
   * Fornisce una rappresentazione in stringa dell'oggetto CourierVehicleDTO.
   *
   * @return Una stringa che rappresenta l'oggetto CourierVehicleDTO.
   */
  @Override
  public String toString() {
    return "CourierVehicleDTO{" +
            "courierVehicleId=" + courierVehicleId +
            ", occupiedSpace=" + occupiedSpace +
            ", isAvailable=" + isAvailable +
            ", licensePlate='" + licensePlate + '\'' +
            ", courierOwner=" + courierOwner +
            ", worksFor=" + worksFor +
            '}';
  }
}