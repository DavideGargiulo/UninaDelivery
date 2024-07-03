package com.unina.oobd2324gr18.DTO;

import java.time.LocalDate;

/**
 * Classe che rappresenta un autista, estendendo le informazioni di un account con dettagli specifici
 * come la mail aziendale e il magazzino di riferimento.
 *
 * @author DavideGargiulo
 */
public final class DriverDTO extends AccountDTO {
  private String businessMail; // Email aziendale dell'autista
  private WarehouseDTO warehouse; // Magazzino di riferimento per l'autista

  /**
   * Costruttore privato utilizzato dal Builder per creare un'istanza di DriverDTO.
   *
   * @param builder Il builder che contiene i dati per costruire l'oggetto.
   */
  private DriverDTO(final DriverBuilder builder) {
    super(builder.accountName, builder.accountSurname, builder.accountEmail, builder.accountPassword,
        builder.accountBirthDate, builder.accountAddress);
    this.businessMail = builder.businessMail;
    this.warehouse = builder.warehouse;
  }

  /**
   * Classe Builder per DriverDTO. Permette di costruire un oggetto DriverDTO passo dopo passo.
   */
  public static class DriverBuilder {
    // Campi obbligatori per AccountDTO
    private String accountName;
    private String accountSurname;
    private String accountEmail;
    private String accountPassword;
    private LocalDate accountBirthDate;
    private SavedAddressDTO accountAddress;

    // Campi specifici per DriverDTO
    private String businessMail;
    private WarehouseDTO warehouse;

    /**
     * Costruttore del Builder con parametri obbligatori.
     *
     * @param accountName Nome dell'account.
     * @param accountSurname Cognome dell'account.
     * @param accountEmail Email dell'account.
     * @param accountPassword Password dell'account.
     * @param businessMail Email aziendale.
     * @param accountAddress Indirizzo dell'account.
     * @param accountBirthDate Data di nascita dell'account.
     */
    public DriverBuilder(final String accountName, final String accountSurname, final String accountEmail,
        final String accountPassword, final String businessMail, final SavedAddressDTO accountAddress,
        final LocalDate accountBirthDate) {
      this.accountName = accountName;
      this.accountSurname = accountSurname;
      this.accountEmail = accountEmail;
      this.accountPassword = accountPassword;
      this.businessMail = businessMail;
      this.accountAddress = accountAddress;
      this.accountBirthDate = accountBirthDate;
    }

    // Metodi setter del Builder per impostare i campi specifici e restituire il builder stesso
    public DriverBuilder businessMail(final String businessMail) {
      this.businessMail = businessMail;
      return this;
    }

    public DriverBuilder warehouse(final WarehouseDTO warehouse) {
      this.warehouse = warehouse;
      return this;
    }

    public DriverBuilder accountAddress(final SavedAddressDTO accountAddress) {
      this.accountAddress = accountAddress;
      return this;
    }

    public DriverBuilder accountBirthDate(final LocalDate accountBirthDate) {
      this.accountBirthDate = accountBirthDate;
      return this;
    }

    /**
     * Costruisce l'oggetto DriverDTO utilizzando i dati forniti al Builder.
     *
     * @return Un nuovo oggetto DriverDTO.
     * @throws IllegalStateException Se il magazzino non è stato impostato.
     */
    public DriverDTO build() {
      if (warehouse == null) {
        throw new IllegalStateException("Warehouse cannot be null");
      }
      return new DriverDTO(this);
    }
  }

  /**
   * Costruttore di DriverDTO che accetta un AccountDTO e i campi specifici di DriverDTO.
   *
   * @param account L'account base da cui copiare i dati.
   * @param businessEmail L'email aziendale dell'autista.
   * @param warehouse Il magazzino di riferimento per l'autista.
   */
  public DriverDTO(final AccountDTO account, final String businessEmail, final WarehouseDTO warehouse) {
    super(account.getName(), account.getSurname(), account.getEmail(), account.getPassword(), account.getBirthDate(),
        account.getAddress());
    this.businessMail = businessEmail;
    this.warehouse = warehouse;
  }

  // Getter e setter per i campi specifici di DriverDTO
  public String getBusinessMail() {
    return businessMail;
  }

  public void setBusinessMail(final String businessMail) {
    this.businessMail = businessMail;
  }

  public WarehouseDTO getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(final WarehouseDTO warehouse) {
    this.warehouse = warehouse;
  }

  @Override
  public String toString() {
    return "DriverDTO [businessMail=" + businessMail + ", warehouse=" + warehouse + "]";
  }
}