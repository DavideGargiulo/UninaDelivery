package com.unina.oobd2324gr18.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Rappresenta un oggetto di trasferimento dati (DTO) dell'account per trasferire dati dell'account tra processi o attraverso la rete.
 * Questa classe incapsula informazioni sull'account includendo dettagli personali, indirizzo, ordini e spese.
 *
 * @author DavideGargiulo
 */
public class AccountDTO {
  // Nome dell'account
  private String name;

  // Cognome dell'account
  private String surname;

  // Indirizzo email dell'account
  private String email;

  // Password dell'account
  private String password;

  // Data di nascita dell'account
  private LocalDate birthDate;

  // Indirizzo salvato dell'account
  private savedAddress address;

  // Lista degli ordini associati all'account
  private List<Order> orders = new ArrayList<>();

  // Totale denaro speso dall'account
  private double moneySpent;

  /**
   * Costruttore per AccountDTO.
   *
   * @param name Nome dell'account
   * @param surname Cognome dell'account
   * @param email Indirizzo email dell'account
   * @param password Password dell'account
   * @param birthDate Data di nascita dell'account
   * @param address Indirizzo salvato dell'account
   */
  public AccountDTO(final String name, final String surname, final String email, final String password, final LocalDate birthDate, final savedAddress address) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.birthDate = birthDate;
    this.address = address;
  }

  // Getter per il nome
  public String getName() {
    return name;
  }

  // Setter per il nome
  public void setName(final String name) {
    this.name = name;
  }

  // Getter per il cognome
  public String getSurname() {
    return surname;
  }

  // Setter per il cognome
  public void setSurname(final String surname) {
    this.surname = surname;
  }

  // Getter per l'email
  public String getEmail() {
    return email;
  }

  // Setter per l'email
  public void setEmail(final String email) {
    this.email = email;
  }

  // Getter per la password
  public String getPassword() {
    return password;
  }

  // Setter per la password
  public void setPassword(final String password) {
    this.password = password;
  }

  // Getter per la data di nascita
  public LocalDate getBirthDate() {
    return birthDate;
  }

  // Setter per la data di nascita
  public void setBirthDate(final LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  // Getter per l'indirizzo
  public savedAddress getAddress() {
    return address;
  }

  // Setter per l'indirizzo
  public void setAddress(final savedAddress address) {
    this.address = address;
  }

  // Getter per gli ordini
  public List<Order> getOrders() {
    return orders;
  }

  // Setter per gli ordini
  public void setOrders(final List<Order> orders) {
    this.orders = orders;
  }

  // Getter per il denaro speso
  public double getMoneySpent() {
    return moneySpent;
  }

  // Setter per il denaro speso
  public void setMoneySpent(final double moneySpent) {
    this.moneySpent = moneySpent;
  }

  /**
   * Fornisce una rappresentazione sotto forma di stringa dell'oggetto AccountDTO.
   *
   * @return Rappresentazione sotto forma di stringa dell'AccountDTO
   */
  @Override
  public String toString() {
    return "AccountDTO{" +
        "name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", birthDate=" + birthDate +
        ", address=" + address +
        ", orders=" + orders +
        ", moneySpent=" + moneySpent +
        '}';
  }
}