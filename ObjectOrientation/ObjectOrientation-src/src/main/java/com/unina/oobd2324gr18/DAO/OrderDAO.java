package com.unina.oobd2324gr18.DAO;

import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import com.unina.oobd2324gr18.DTO.AccountDTO;
import com.unina.oobd2324gr18.DTO.OrderDTO;

/**
 * Interfaccia OrderDAO
 *
 * Questa interfaccia definisce i metodi per l'accesso ai dati degli ordini.
 *
 * @autor DavideGargiulo
 */
public interface OrderDAO extends BasicDAO<OrderDTO> {

  /**
   * Trova un ordine in base al suo ID.
   *
   * @param id L'ID dell'ordine da trovare.
   * @return Un oggetto OrderDTO che rappresenta l'ordine trovato.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  OrderDTO findById(int id) throws SQLException;

  /**
   * Trova tutti gli ordini non completati.
   *
   * @return Una lista di oggetti OrderDTO che rappresentano gli ordini non completati.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  List<OrderDTO> findUnfinished() throws SQLException;

  /**
   * Trova l'ordine con la quantità maggiore in un determinato mese e anno.
   *
   * @param month Il mese in cui cercare l'ordine.
   * @param year L'anno in cui cercare l'ordine.
   * @return Un oggetto OrderDTO che rappresenta l'ordine trovato.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  OrderDTO findWithLargestQuantity(Month month, Year year) throws SQLException;

  /**
   * Ottiene la quantità di ordini per ogni mese.
   *
   * @return Una lista di interi che rappresentano la quantità di ordini per ogni mese.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  List<Integer> getMonthlyOrderQuantities() throws SQLException;

  /**
   * Ottiene gli ordini in base a un filtro specificato.
   *
   * @param filter Il filtro da applicare per la ricerca degli ordini.
   * @return Una lista di oggetti OrderDTO che rappresentano gli ordini trovati.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  List<OrderDTO> findByFilter(HashMap<String, Object> filter) throws SQLException;

  /**
   * Ottiene gli ordini in base a un account e a un determinato mese e anno.
   *
   * @param account L'account per cui cercare gli ordini.
   * @param month Il mese in cui cercare gli ordini.
   * @param year L'anno in cui cercare gli ordini.
   * @return Una lista di oggetti OrderDTO che rappresentano gli ordini trovati.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  List<OrderDTO> findByAccountAndMonth(AccountDTO account, Month month, Year year) throws SQLException;

  /**
   * Ottiene l'anno con il maggior numero di ordini.
   *
   * @return Un oggetto Year che rappresenta l'anno con il maggior numero di ordini.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  Year findYearWithMostOrders() throws SQLException;

  /**
   * Ottiene la quantità di categorie per un determinato mese e anno.
   *
   * @param month Il mese in cui cercare le categorie.
   * @param year L'anno in cui cercare le categorie.
   * @return Una mappa che associa il nome della categoria alla quantità di ordini.
   * @throws SQLException Se si verifica un errore durante l'operazione di selezione.
   */
  HashMap<String, Integer> getCategoryQuantitiesByMonth(Month month, Year year) throws SQLException;
}