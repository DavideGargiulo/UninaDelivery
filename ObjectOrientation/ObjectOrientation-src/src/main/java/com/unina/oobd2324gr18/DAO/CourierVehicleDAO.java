package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.unina.oobd2324gr18.DTO.CourierVehicleDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;

/**
 * Interfaccia per la gestione delle operazioni sui veicoli dei corrieri.
 * Estende l'interfaccia BasicDAO per le operazioni CRUD generiche.
 * Definisce metodi specifici per trovare veicoli dei corrieri
 * tramite ID, magazzino e compatibilità con determinati requisiti.
 *
 * @autor DavideGargiulo
 */
public interface CourierVehicleDAO extends BasicDAO<CourierVehicleDTO> {

  /**
   * Trova un veicolo del corriere in base all'ID.
   *
   * @param id l'ID del veicolo da trovare
   * @return il DAO del veicolo del corriere corrispondente all'ID
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  CourierVehicleDTO getCourierVehicleById(int id) throws SQLException;

  /**
   * Trova i veicoli dei corrieri associati a un determinato magazzino.
   *
   * @param warehouse l'oggetto WarehouseDTO del magazzino
   * @return una lista di DTO dei veicoli dei corrieri associati al magazzino
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<CourierVehicleDTO> getCourierVehicleByWarehouse(WarehouseDTO warehouse) throws SQLException;

  /**
   * Trova i veicoli dei corrieri compatibili con determinati requisiti di spazio occupato,
   * magazzino e data.
   *
   * @param occupiedSpace lo spazio occupato richiesto
   * @param warehouse l'oggetto WarehouseDTO del magazzino
   * @param data la data per cui verificare la compatibilità
   * @return una lista di DTO dei veicoli dei corrieri compatibili
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<CourierVehicleDTO> getCompatibleVehicles(double occupiedSpace, WarehouseDTO warehouse, LocalDate data) throws SQLException;
}
