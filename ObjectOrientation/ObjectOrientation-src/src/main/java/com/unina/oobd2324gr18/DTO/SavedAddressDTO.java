package com.unina.oobd2324gr18.DTO;

/**
 * Rappresenta un indirizzo salvato con dettagli quali città, provincia, codice postale, via, numero civico e stato.
 * Questa classe fornisce metodi getter e setter per ogni campo, consentendo la manipolazione delle proprietà dell'indirizzo.
 *
 * @author DavideGargiulo
 */
public class SavedAddressDTO {
  // Città in cui si trova l'indirizzo
  private String city;

  // Provincia in cui si trova l'indirizzo
  private String province;

  // Codice postale dell'indirizzo
  private int zipCode;

  // Via dell'indirizzo
  private String street;

  // Numero civico dell'indirizzo
  private int civicNumber;

  // Stato in cui si trova l'indirizzo
  private String state;

  /**
   * Costruttore per creare un'istanza di savedAddressDTO con tutti i dettagli dell'indirizzo.
   *
   * @param city La città dell'indirizzo.
   * @param province La provincia dell'indirizzo.
   * @param zipCode Il codice postale dell'indirizzo.
   * @param street La via dell'indirizzo.
   * @param civicNumber Il numero civico dell'indirizzo.
   * @param state Lo stato dell'indirizzo.
   */
  public SavedAddressDTO(final String city, final String province, final int zipCode, final String street, final int civicNumber, final String state) {
    this.city = city;
    this.province = province;
    this.zipCode = zipCode;
    this.street = street;
    this.civicNumber = civicNumber;
    this.state = state;
  }

  // Metodi getter per accedere ai valori delle proprietà dell'indirizzo
  public String getCity() {
    return city;
  }
  public String getProvince() {
    return province;
  }
  public int getZipCode() {
    return zipCode;
  }
  public String getStreet() {
    return street;
  }
  public int getCivicNumber() {
    return civicNumber;
  }
  public String getState() {
    return state;
  }

  // Metodi setter per modificare i valori delle proprietà dell'indirizzo
  public void setCity(final String city) {
    this.city = city;
  }
  public void setProvince(final String province) {
    this.province = province;
  }
  public void setZipCode(final int zipCode) {
    this.zipCode = zipCode;
  }
  public void setStreet(final String street) {
    this.street = street;
  }
  public void setCivicNumber(final int civicNumber) {
    this.civicNumber = civicNumber;
  }
  public void setState(final String state) {
    this.state = state;
  }

  /**
   * Restituisce una rappresentazione in formato stringa dell'oggetto savedAddressDTO.
   *
   * @return Una stringa che rappresenta l'oggetto savedAddressDTO.
   */
  @Override
  public String toString() {
    return "savedAddressDTO{" +
            "city='" + city + '\'' +
            ", province='" + province + '\'' +
            ", zipCode=" + zipCode +
            ", street='" + street + '\'' +
            ", civicNumber=" + civicNumber +
            ", state='" + state + '\'' +
            '}';
  }

  /**
   * Genera il codice hash per questo oggetto.
   * Questo metodo utilizza l'implementazione di hashCode della superclasse.
   *
   * @return il codice hash generato.
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Confronta questo oggetto con un altro per verificare se sono uguali.
   *
   * @param objectToCompare l'oggetto da confrontare con questo.
   * @return true se gli oggetti sono uguali, false altrimenti.
   */
  @Override
  public boolean equals(Object objectToCompare) {
    if (this == objectToCompare) return true;
    if (objectToCompare == null || getClass() != objectToCompare.getClass()) return false;

    SavedAddressDTO that = (SavedAddressDTO) objectToCompare;
    boolean isEqual = true;

    isEqual &= city.equals(that.city);
    isEqual &= province.equals(that.province);
    isEqual &= zipCode == that.zipCode;
    isEqual &= street.equals(that.street);
    isEqual &= civicNumber == that.civicNumber;
    isEqual &= state.equals(that.state);

    return isEqual;
  }
}