package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;
import java.util.List;

import com.unina.oobd2324gr18.DTO.DriverDTO;
import com.unina.oobd2324gr18.DTO.OrderDTO;
import com.unina.oobd2324gr18.DTO.ShipmentDTO;

/**
 * Interfaccia per il Data Access Object (DAO) delle spedizioni.
 * Estende l'interfaccia BasicDAO per fornire operazioni CRUD di base.
 * Include metodi specifici per gestire le spedizioni.
 *
 * @param <ShipmentDTO> Tipo del Data Transfer Object (DTO) per le spedizioni.
 * @throws SQLException Eccezione lanciata in caso di errori SQL.
 *
 * @autor DavideGargiulo
 */
public interface ShipmentDAO extends BasicDAO<ShipmentDTO> {

  /**
   * Trova una spedizione in base al codice di tracciamento.
   *
   * @param order L'ordine associato alla spedizione.
   * @param shipment La spedizione da trovare.
   * @return Il ShipmentDTO della spedizione trovata.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  ShipmentDTO findByTrackingCode(OrderDTO order, ShipmentDTO shipment) throws SQLException;

  /**
   * Assegna un autista a una spedizione.
   *
   * @param shipment La spedizione a cui assegnare l'autista.
   * @param driver L'autista da assegnare.
   * @return Il numero di righe aggiornate.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  int assignDriver(ShipmentDTO shipment, DriverDTO driver) throws SQLException;

  /**
   * Spedisce un ordine.
   *
   * @param order L'ordine da spedire.
   * @param shipment La spedizione associata all'ordine.
   * @return Il numero di righe aggiornate.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  int shipOrder(OrderDTO order, ShipmentDTO shipment) throws SQLException;

  /**
   * Ottiene una lista di spedizioni compatibili con un ordine.
   *
   * @param order L'ordine per cui trovare le spedizioni compatibili.
   * @return Una lista di ShipmentDTO delle spedizioni compatibili.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  List<ShipmentDTO> getCompatibleShipment(OrderDTO order) throws SQLException;
}