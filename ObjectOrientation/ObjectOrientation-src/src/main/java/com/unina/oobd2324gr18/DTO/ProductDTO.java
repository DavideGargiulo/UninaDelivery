package com.unina.oobd2324gr18.DTO;

import java.time.LocalDate;

/**
 * Classe che rappresenta un DTO (Data Transfer Object) per un prodotto.
 * Fornisce tutti i dettagli necessari per trasferire dati di prodotti tra vari livelli dell'applicazione.
 *
 * @author DavideGargiulo
 */
public class ProductDTO {
  // Nome del prodotto
  private String name;

  // Descrizione del prodotto
  private String description;

  // Prezzo del prodotto
  private double price;

  // Data di creazione del prodotto
  private LocalDate createdAt;

  // Categoria del prodotto
  private String category;

  // Indica se il prodotto è fragile o meno
  private boolean isFragile;

  /**
   * Costruttore per creare un'istanza di ProductDTO con tutti i dettagli necessari.
   *
   * @param name Nome del prodotto.
   * @param description Descrizione del prodotto.
   * @param price Prezzo del prodotto.
   * @param createdAt Data di creazione del prodotto.
   * @param category Categoria del prodotto.
   * @param isFragile Indica se il prodotto è fragile.
   */
  public ProductDTO(final String name, final String description, final double price, final LocalDate createdAt, final String category, final boolean isFragile) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.createdAt = createdAt;
    this.category = category;
    this.isFragile = isFragile;
  }

  // Getter per il nome
  public String getName() {
    return name;
  }

  // Getter per la descrizione
  public String getDescription() {
    return description;
  }

  // Getter per il prezzo
  public double getPrice() {
    return price;
  }

  // Getter per la data di creazione
  public LocalDate getCreatedAt() {
    return createdAt;
  }

  // Getter per la categoria
  public String getCategory() {
    return category;
  }

  // Getter per isFragile
  public boolean isFragile() {
    return isFragile;
  }

  // Setter per il nome
  public void setName(final String name) {
    this.name = name;
  }

  // Setter per la descrizione
  public void setDescription(final String description) {
    this.description = description;
  }

  // Setter per il prezzo
  public void setPrice(final double price) {
    this.price = price;
  }

  // Setter per la data di creazione
  public void setCreatedAt(final LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  // Setter per la categoria
  public void setCategory(final String category) {
    this.category = category;
  }

  // Setter per isFragile
  public void setFragile(final boolean isFragile) {
    this.isFragile = isFragile;
  }

  /**
   * Fornisce una rappresentazione in stringa dell'oggetto ProductDTO.
   *
   * @return Una stringa che rappresenta l'oggetto ProductDTO.
   */
  @Override
  public String toString() {
    return "ProductDTO{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", createdAt=" + createdAt +
            ", category='" + category + '\'' +
            ", isFragile=" + isFragile +
            '}';
  }

  /**
   * Genera un codice hash per questo oggetto.
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
    if (this == objectToCompare) {
      return true;
    }
    if (objectToCompare == null || getClass() != objectToCompare.getClass()) {
      return false;
    }

    ProductDTO that = (ProductDTO) objectToCompare;
    boolean isEqual = true;

    isEqual &= Double.compare(that.price, price) == 0;
    isEqual &= isFragile == that.isFragile;
    isEqual &= name.equals(that.name);
    isEqual &= description.equals(that.description);
    isEqual &= createdAt.equals(that.createdAt);
    isEqual &= category.equals(that.category);

    return isEqual;
  }
}