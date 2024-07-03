package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia che definisce le operazioni CRUD di base per un tipo di entità.
 * CRUD sta per Creazione (Insert), Lettura (GetAll), Aggiornamento (Update) e
 * Cancellazione (Delete).
 *
 * @param <T> Il tipo di entità che questa DAO gestisce.
 * @author DavideGargiulo
 */
public interface BasicDAO<T> {

  /**
   * Inserisce una nuova entità nel database.
   *
   * @param entity L'entità da inserire.
   * @return Il numero di righe influenzate.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  int insert(T entity) throws SQLException;

  /**
   * Recupera tutte le entità dal database.
   *
   * @return Una lista di entità.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  List<T> findAll() throws SQLException;

  /**
   * Aggiorna un'entità esistente nel database.
   *
   * @param entity L'entità da aggiornare.
   * @return Il numero di righe influenzate.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  int update(T entity) throws SQLException;

  /**
   * Elimina un'entità dal database.
   *
   * @param entity L'entità da eliminare.
   * @return Il numero di righe influenzate.
   * @throws SQLException Se si verifica un errore di accesso al database.
   */
  int delete(T entity) throws SQLException;
}