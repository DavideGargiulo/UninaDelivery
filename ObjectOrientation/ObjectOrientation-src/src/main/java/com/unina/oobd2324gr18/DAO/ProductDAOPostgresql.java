package com.unina.oobd2324gr18.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.unina.oobd2324gr18.DTO.ProductDTO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;

/**
 * Implementazione del DAO per i prodotti utilizzando PostgreSQL.
 * Implementa i metodi definiti nell'interfaccia ProductDAO.
 *
 * @autor DavideGargiulo
 */
public class ProductDAOPostgresql implements ProductDAO {

  /**
   * Popola un oggetto ProductDTO dai dati ottenuti da un ResultSet.
   *
   * @param resultSet Il ResultSet contenente i dati del prodotto.
   * @return Un oggetto ProductDTO popolato con i dati del ResultSet.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  private ProductDTO populateProductFromResultSet(final ResultSet resultSet) throws SQLException {
    return new ProductDTO(
        resultSet.getString("name"),
        resultSet.getString("description"),
        resultSet.getDouble("price"),
        resultSet.getDate("creadetAt").toLocalDate(),
        resultSet.getString("category"),
        resultSet.getBoolean("isFragile"));
  }

  /**
   * Inserisce un nuovo prodotto nel database.
   *
   * @param savedAddressDTO Il prodotto da inserire.
   * @return Il numero di righe inserite.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  @Override
  public int insert(final ProductDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Trova tutti i prodotti nel database.
   *
   * @return Una lista di ProductDTO contenente tutti i prodotti.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  @Override
  public List<ProductDTO> findAll() throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Aggiorna un prodotto esistente nel database.
   *
   * @param savedAddressDTO Il prodotto da aggiornare.
   * @return Il numero di righe aggiornate.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  @Override
  public int update(final ProductDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Elimina un prodotto dal database.
   *
   * @param savedAddressDTO Il prodotto da eliminare.
   * @return Il numero di righe eliminate.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  @Override
  public int delete(final ProductDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }

  /**
   * Trova un prodotto in base al nome e al fornitore.
   *
   * @param name Nome del prodotto da trovare.
   * @param supplier Fornitore del prodotto da trovare.
   * @return Il ProductDTO del prodotto trovato.
   * @throws SQLException Eccezione lanciata in caso di errori SQL.
   */
  @Override
  public ProductDTO findByNameAndSupplier(String name, String supplier) throws SQLException {
    // Ottiene una connessione al database con lo schema "uninadelivery"
    Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    ProductDTO product = null;

    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE name = ? AND supplier = ?");
    preparedStatement.setString(1, name);
    preparedStatement.setString(2, supplier);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      product = populateProductFromResultSet(resultSet);
    }
    resultSet.close();
    preparedStatement.close();
    connection.close();

    return product;
  }
}