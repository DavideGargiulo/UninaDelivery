package com.unina.oobd2324gr18.DAO;

import com.unina.oobd2324gr18.DTO.AccountDTO;
import com.unina.oobd2324gr18.DTO.DriverDTO;
import com.unina.oobd2324gr18.DTO.OperatorDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOPostgresql implements AccountDAO {

  @Override
  public int insert(AccountDTO entity) throws SQLException {
    return 0;
  }

  @Override
  public List<AccountDTO> findAll() throws SQLException {
    return null;
  }

  @Override
  public int update(AccountDTO entity) throws SQLException {
    return 0;
  }

  private AccountDTO populateAccountFromResultSet(final ResultSet resultSet) throws SQLException {
    return new AccountDTO(
      resultSet.getString("name"),
      resultSet.getString("surname"),
      resultSet.getString("email"),
      resultSet.getDate("birthdate").toLocalDate(),
      resultSet.getString("password")
    );
  }

  private DriverDTO populateDriverFromResultSet(final ResultSet resultSet) throws SQLException {
    return new DriverDTO(
      resultSet.getInt("id"),
      resultSet.getString("name"),
      resultSet.getString("surname"),
      resultSet.getString("license_number"),
      resultSet.getString("email"),
      resultSet.getString("phone"),
      resultSet.getString("status")
    );
  }

  private OperatorDTO populateOperatorFromResultSet(final ResultSet resultSet) throws SQLException {
    return new OperatorDTO(
      resultSet.getString("name"),
      resultSet.getString("surname"),
      resultSet.getString("business_email"),
      resultSet.getString("password")
    );
  }

  @Override
  public AccountDTO findAccountByEmail(String email) throws SQLException {
    Connection con = DBConnection.getConnectionBySchema("uninadelivery");
    AccountDTO account = null;
    PreparedStatement psSelect = null;
    ResultSet rs = null;

    try {
      psSelect = con.prepareStatement("SELECT * FROM account WHERE email = ?");
      psSelect.setString(1, email);
      rs = psSelect.executeQuery();
      if (rs.next()) {
        account = populateAccountFromResultSet(rs);
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (psSelect != null) {
        psSelect.close();
      }
      if (con != null) {
        con.close();
      }
    }

    return account;
  }

  @Override
  public AccountDTO findAccountByEmailNPassword(String email, String password) throws SQLException {
    Connection con = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    AccountDTO account = null;
    PreparedStatement psSelect = null;
    ResultSet rs = null;

    try {
      psSelect = con.prepareStatement("SELECT * FROM account WHERE email = ? AND password = ?");
      psSelect.setString(1, email);
      psSelect.setString(2, password);
      rs = psSelect.executeQuery();
      if (rs.next()) {
        account = populateAccountFromResultSet(rs);
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (psSelect != null) {
        psSelect.close();
      }
      if (con != null) {
        con.close();
      }
    }

    return account;
  }

  @Override
  public OperatorDTO findOperatorByBusinessemailNPassword(String businessEmail, String password) throws SQLException {
    Connection con = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    OperatorDTO operator = null;
    PreparedStatement psSelect = null;
    ResultSet rs = null;

    try {
      psSelect = con.prepareStatement("SELECT * FROM operator WHERE business_email = ? AND password = ?");
      psSelect.setString(1, businessEmail);
      psSelect.setString(2, password);
      rs = psSelect.executeQuery();
      if (rs.next()) {
        operator = populateOperatorFromResultSet(rs);
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (psSelect != null) {
        psSelect.close();
      }
      if (con != null) {
        con.close();
      }
    }

    return operator;
  }

  @Override
  public List<AccountDTO> findAccountNameNSurname(String name, String surname) throws SQLException {
    List<AccountDTO> accounts = new ArrayList<>();
    Connection con = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    PreparedStatement psSelect = null;
    ResultSet rs = null;

    try {
      psSelect = con.prepareStatement("SELECT * FROM account WHERE name = ? AND surname = ?");
      psSelect.setString(1, name);
      psSelect.setString(2, surname);
      rs = psSelect.executeQuery();
      while (rs.next()) {
        accounts.add(populateAccountFromResultSet(rs));
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (psSelect != null) {
        psSelect.close();
      }
      if (con != null) {
        con.close();
      }
    }

    return accounts;
  }

  @Override
  public AccountDTO findMostSpendingAccount(Year year, Month month) throws SQLException {
    Connection con = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    AccountDTO account = null;
    PreparedStatement psSelect = null;
    ResultSet rs = null;

    try {
      String query = "SELECT account.*, SUM(order.total) as total_spent " +
                     "FROM account " +
                     "JOIN orders ON account.email = orders.account_email " +
                     "WHERE EXTRACT(YEAR FROM orders.date) = ? AND EXTRACT(MONTH FROM orders.date) = ? " +
                     "GROUP BY account.email " +
                     "ORDER BY total_spent DESC " +
                     "LIMIT 1";
      psSelect = con.prepareStatement(query);
      psSelect.setInt(1, year.getValue());
      psSelect.setInt(2, month.getValue());
      rs = psSelect.executeQuery();
      if (rs.next()) {
        account = populateAccountFromResultSet(rs);
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (psSelect != null) {
        psSelect.close();
      }
      if (con != null) {
        con.close();
      }
    }

    return account;
  }

  @Override
  public List<DriverDTO> findCompatiblesDrivers(WarehouseDTO warehouse, LocalDate date) throws SQLException {
    List<DriverDTO> drivers = new ArrayList<>();
    Connection con = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    PreparedStatement psSelect = null;
    ResultSet rs = null;

    try {
      String query = "SELECT * FROM driver WHERE warehouse_id = ? AND available_date = ?";
      psSelect = con.prepareStatement(query);
      psSelect.setInt(1, warehouse.getId());
      psSelect.setDate(2, java.sql.Date.valueOf(date));
      rs = psSelect.executeQuery();
      while (rs.next()) {
        drivers.add(populateDriverFromResultSet(rs));
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (psSelect != null) {
        psSelect.close();
      }
      if (con != null) {
        con.close();
      }
    }

    return drivers;
  }

  @Override
  public int delete(AccountDTO account) throws SQLException {
    Connection con = DBConnection.getConnectionBySchema("uninadelivery");
    PreparedStatement psDelete = null;

    try {
      psDelete = con.prepareStatement("DELETE FROM account WHERE email = ?");
      psDelete.setString(1, account.getEmail());
      psDelete.executeUpdate();
    } finally {
      if (psDelete != null) {
        psDelete.close();
      }
      if (con != null) {
        con.close();
      }
    }
  }
}
