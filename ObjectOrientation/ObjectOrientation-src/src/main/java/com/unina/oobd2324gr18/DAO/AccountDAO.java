package com.unina.oobd2324gr18.DAO;

import com.unina.oobd2324gr18.DTO.AccountDTO;
import com.unina.oobd2324gr18.DTO.OperatorDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;
import com.unina.oobd2324gr18.DTO.DriverDTO;

import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * Interfaccia che definisce le operazioni CRUD (Create, Read, Update, Delete) per gli account,
 * estendendo le funzionalità di base definite in BasicDAO.
 *
 * @author DavideGargiulo
 */
public interface AccountDAO extends BasicDAO<AccountDTO> {

  /**
   * Trova un account basato sull'email fornita.
   *
   * @param email L'email dell'account da trovare.
   * @return L'AccountDTO corrispondente all'email fornita.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  AccountDTO findAccountByEmail(String email) throws SQLException;

  /**
   * Trova un account basato sull'email e sulla password fornite.
   *
   * @param email L'email dell'account da trovare.
   * @param password La password dell'account da trovare.
   * @return L'AccountDTO corrispondente alla combinazione di email e password fornite.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  AccountDTO findAccountByEmailNPassword(String email, String password) throws SQLException;

  /**
   * Trova un operatore basato sull'email aziendale e sulla password fornite.
   *
   * @param businessEmail L'email aziendale dell'operatore da trovare.
   * @param password La password dell'operatore da trovare.
   * @return L'OperatorDTO corrispondente alla combinazione di email aziendale e password fornite.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  OperatorDTO findOperatorByBusinessemailNPassword(String businessEmail, String password) throws SQLException;

  /**
   * Trova gli account basati sul nome e cognome forniti.
   *
   * @param name Il nome da cercare.
   * @param surname Il cognome da cercare.
   * @return Una lista di AccountDTO corrispondenti al nome e cognome forniti.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  List<AccountDTO> findAccountNameNSurname(String name, String surname) throws SQLException;

  /**
   * Trova l'account che ha speso di più in un determinato anno e mese.
   *
   * @param year L'anno da considerare.
   * @param month Il mese da considerare.
   * @return L'AccountDTO che ha speso di più nel periodo specificato.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  AccountDTO findMostSpendingAccount(Year year, Month month) throws SQLException;

  //List<DriverDTO> findCompatiblesDrivers(WarehouseDTO warehouse, LocalDate date) throws SQLException;
}