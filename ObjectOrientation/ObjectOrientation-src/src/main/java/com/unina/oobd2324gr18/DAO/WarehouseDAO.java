package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.unina.oobd2324gr18.DTO.OrderDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;

/**
 * Interfaccia WarehouseDAO che estende BasicDAO per WarehouseDTO.
 * Fornisce metodi per operazioni specifiche sui magazzini.
 *
 * @autor DavideGargiulo
 */
public interface WarehouseDAO extends BasicDAO<WarehouseDTO> {

  /**
   * Trova un magazzino per ID.
   *
   * @param id l'ID del magazzino
   * @return WarehouseDTO il magazzino trovato
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  WarehouseDTO findWarehouseById(int id) throws SQLException;

  /**
   * Trova magazzini per città.
   *
   * @param city la città dei magazzini
   * @return List<WarehouseDTO> lista di magazzini trovati
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<WarehouseDTO> findWarehousesByCity(String city) throws SQLException;

  /**
   * Trova magazzini per regione.
   *
   * @param region la regione dei magazzini
   * @return List<WarehouseDTO> lista di magazzini trovati
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<WarehouseDTO> findWarehousesByRegion(String region) throws SQLException;

  /**
   * Trova magazzini per paese.
   *
   * @param country il paese dei magazzini
   * @return List<WarehouseDTO> lista di magazzini trovati
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<WarehouseDTO> findWarehousesByCountry(String country) throws SQLException;

  /**
   * Trova magazzini per capacità.
   *
   * @param capacity la capacità dei magazzini
   * @return List<WarehouseDTO> lista di magazzini trovati
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<WarehouseDTO> findWarehousesByCapacity(int capacity) throws SQLException;

  /**
   * Ottiene depositi compatibili per un ordine e una data specifica.
   *
   * @param order l'ordine per cui trovare i depositi compatibili
   * @param date la data per cui trovare i depositi compatibili
   * @return List<WarehouseDTO> lista di depositi compatibili
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<WarehouseDTO> getCompatibleDeposits(OrderDTO order, LocalDate date) throws SQLException;
}