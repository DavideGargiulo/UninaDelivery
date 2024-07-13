package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;
import java.util.List;

import com.unina.oobd2324gr18.DTO.CourierDTO;
import com.unina.oobd2324gr18.DTO.CourierVehicleDTO;

/**
 * Interfaccia per la gestione delle operazioni sui corrieri.
 * Estende l'interfaccia BasicDAO per le operazioni CRUD generiche.
 * Definisce metodi specifici per trovare corrieri tramite partita IVA e
 * ottenere i veicoli associati a un corriere.
 *
 * @autor DavideGargiulo
 */
public interface CourierDAO extends BasicDAO<CourierDTO> {

  /**
   * Trova un corriere in base alla partita IVA.
   *
   * @param VAT la partita IVA del corriere da trovare
   * @return il DTO del corriere corrispondente alla partita IVA
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  CourierDTO findByVAT(String VAT) throws SQLException;

  /**
   * Ottiene la lista dei veicoli associati a un corriere specifico.
   *
   * @param courierID l'ID del corriere di cui ottenere i veicoli
   * @return una lista di DTO dei veicoli associati al corriere
   * @throws SQLException se si verifica un errore durante l'accesso al database
   */
  List<CourierVehicleDTO> getVehiclesByCourier(int courierID) throws SQLException;
}
