package com.unina.oobd2324gr18.DAO;

import com.unina.oobd2324gr18.DTO.AccountDTO;
import com.unina.oobd2324gr18.DTO.DriverDTO;
import com.unina.oobd2324gr18.DTO.OperatorDTO;
import com.unina.oobd2324gr18.DTO.WarehouseDTO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;
import com.unina.oobd2324gr18.DTO.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class AccountDAOPostgresql implements AccountDAO {
  private OperatorDTO populateOperatorFromResultSet(final ResultSet resultSet) throws SQLException {
    return new OperatorDTO(populateAccountFromResultSet(resultSet), resultSet.getString("businessemail"));
  }

  private AccountDTO populateAccountFromResultSet(final ResultSet resultSet) throws SQLException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern to your date format
    String birthDateString = resultSet.getString("birthDate");
    LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
    SavedAddressDAO addressDAO = new SavedAddressDAOPostgresql();

    return new AccountDTO(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("email"),
                          resultSet.getString("password"), birthDate, addressDAO.extractAddress(resultSet.getString("addressCity"), resultSet.getString("addressProvince"), resultSet.getString("addressZipCode"), resultSet.getString("addressStreet"), resultSet.getString("addressCivicNumber"), resultSet.getString("addressState")));
  }

  @Override
  public int insert(AccountDTO entity) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public List<AccountDTO> findAll() throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int update(AccountDTO entity) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public AccountDTO findAccountByEmail(String email) throws SQLException {
    Connection con = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
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

/*  @Override
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
  }*/

  @Override
  public int delete(AccountDTO account) throws SQLException {
    throw new MethodNotImplemented();
  }
}
