package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;

import com.unina.oobd2324gr18.DTO.ProductDTO;

/**
 * Interfaccia per il Data Access Object (DAO) dei prodotti.
 * Estende l'interfaccia BasicDAO per fornire operazioni CRUD di base.
 * Include un metodo per trovare un prodotto in base al nome e al fornitore.
 *
 * @param <ProductDTO> Tipo del Data Transfer Object (DTO) per i prodotti.
 * @throws SQLException Eccezione lanciata in caso di errori SQL.
 *
 * @autor DavideGargiulo
 */
public interface ProductDAO extends BasicDAO<ProductDTO> {

  /**
   * Trova un prodotto in base al nome e al fornitore.
   *
   * @param name Nome del prodotto da trovare.
   * @param supplier Fornitore del prodotto da trovare.
   * @return Il ProductDTO del prodotto trovato.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  ProductDTO findByNameAndSupplier(String name, String supplier) throws SQLException;
}
