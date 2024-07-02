package com.unina.oobd2324gr18.DTO;

/**
 * Rappresenta un corriere con nome e partita IVA.
 *
 * @author DavideGargiulo
 */
public class CourierDTO {
  // Nome del corriere
  private String name;
  // Partita IVA del corriere
  private String vat;

  /**
   * Costruisce un nuovo CourierDTO con il nome e la partita IVA specificati.
   *
   * @param name Il nome del corriere.
   * @param vat La partita IVA del corriere.
   */
  public CourierDTO(final String name, final String vat) {
    this.name = name;
    this.vat = vat;
  }

  /**
   * Restituisce il nome del corriere.
   *
   * @return Il nome del corriere.
   */
  public String getName() {
    return name;
  }

  /**
   * Restituisce la partita IVA del corriere.
   *
   * @return La partita IVA del corriere.
   */
  public String getVat() {
    return vat;
  }

  /**
   * Imposta il nome del corriere.
   *
   * @param name Il nuovo nome del corriere.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Imposta la partita IVA del corriere.
   *
   * @param vat La nuova partita IVA del corriere.
   */
  public void setVat(String vat) {
    this.vat = vat;
  }

  /**
   * Restituisce una rappresentazione in formato stringa dell'oggetto CourierDTO.
   *
   * @return Una stringa che rappresenta il corriere.
   */
  @Override
  public String toString() {
    return "CourierDTO{" +
            "name='" + name + '\'' +
            ", vat='" + vat + '\'' +
            '}';
  }

  /**
   * Confronta questo CourierDTO con un altro oggetto per verificare se sono uguali.
   *
   * @param o L'oggetto con cui confrontare questo CourierDTO.
   * @return true se gli oggetti sono uguali, false altrimenti.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CourierDTO that = (CourierDTO) o;
    return name.equals(that.name) && vat.equals(that.vat);
  }

  /**
   * Genera il codice hash per questo CourierDTO.
   *
   * @return Il codice hash generato.
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }
}