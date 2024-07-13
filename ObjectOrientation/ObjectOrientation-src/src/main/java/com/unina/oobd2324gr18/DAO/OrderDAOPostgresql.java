package com.unina.oobd2324gr18.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.unina.oobd2324gr18.DTO.AccountDTO;
import com.unina.oobd2324gr18.DTO.OrderDTO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;

public class OrderDAOPostgresql implements OrderDAO {

  private OrderDTO populateFromResultSet(final ResultSet resultSet) throws SQLException {
    OrderDTO newOrder = null;

    newOrder = new OrderDTO(
      resultSet.getInt("id"),
      resultSet.getDate("orderDate").toLocalDate(),
      new AccountDAOPostgresql().findAccountByEmail(resultSet.getString("email")),
      resultSet.getInt("quantity"),
      resultSet.getDouble("unitPrice"),
      resultSet.getDouble("taxRate"),
      resultSet.getBoolean("isExpressDelivery"),
      resultSet.getBoolean("isDelivered"),
      resultSet.getDate("deliveryDate").toLocalDate()
    );
    return newOrder;
  }

  @Override
  public int insert(OrderDTO entity) throws SQLException {
    Connection conn = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    int rowAffected = 0;

    PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO \"orders\" " + "(price)");
    return rowAffected;
  }

  @Override
  public List<OrderDTO> findAll() throws SQLException {
    String query = "SELECT * FROM orders";
    List<OrderDTO> orders = new ArrayList<>();

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery()) {

      while (resultSet.next()) {
        orders.add(populateFromResultSet(resultSet));
      }
    }
    return orders;
  }

  @Override
  public int update(OrderDTO entity) throws SQLException {
    // String query = "UPDATE orders SET customer_name = ?, order_date = ?, status = ?, quantity = ?, category = ? WHERE id = ?";

    // try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
    //   PreparedStatement preparedStatement = connection.prepareStatement(query)) {

    //   preparedStatement.setString(1, entity.getCustomerName());
    //   preparedStatement.setDate(2, Date.valueOf(entity.getOrderDate()));
    //   preparedStatement.setString(3, entity.getStatus());
    //   preparedStatement.setInt(4, entity.getQuantity());
    //   preparedStatement.setString(5, entity.getCategory());
    //   preparedStatement.setInt(6, entity.getId());
    //   return preparedStatement.executeUpdate();
    // }
    return 1;
  }

  @Override
  public int delete(OrderDTO entity) throws SQLException {
    String query = "DELETE FROM orders WHERE id = ?";

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setInt(1, entity.getOrderId());
      return preparedStatement.executeUpdate();
    }
  }

  @Override
  public OrderDTO findById(int id) throws SQLException {
    String query = "SELECT * FROM orders WHERE id = ?";
    OrderDTO order = null;

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setInt(1, id);

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          order = populateFromResultSet(resultSet);
        }
      }
    }

    if (order == null) {
      throw new SQLException("Order with ID " + id + " not found.");
    }
    return order;
  }

  @Override
  public List<OrderDTO> findUnfinished() throws SQLException {
    String query = "SELECT * FROM orders WHERE status != 'completed'";
    List<OrderDTO> orders = new ArrayList<>();

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        orders.add(populateFromResultSet(resultSet));
      }
    }

    return orders;
  }

  @Override
  public OrderDTO findWithLargestQuantity(Month month, Year year) throws SQLException {
    String query = "SELECT * FROM orders WHERE MONTH(order_date) = ? AND YEAR(order_date) = ? ORDER BY quantity DESC LIMIT 1";
    OrderDTO order = null;

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setInt(1, month.getValue());
      preparedStatement.setInt(2, year.getValue());

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
          order = populateFromResultSet(resultSet);
        }
      }
    }
    return order;
  }

  @Override
  public List<Integer> getMonthlyOrderQuantities() throws SQLException {
    String query = "SELECT MONTH(order_date) AS month, COUNT(*) AS quantity FROM orders GROUP BY month";
    List<Integer> quantities = new ArrayList<>(Collections.nCopies(12, 0));

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery()) {

      while (resultSet.next()) {
        int month = resultSet.getInt("month");
        int quantity = resultSet.getInt("quantity");
        quantities.set(month - 1, quantity);
      }
    }

    return quantities;
  }

  @Override
  public List<OrderDTO> findByFilter(HashMap<String, Object> filter) throws SQLException {
    StringBuilder query = new StringBuilder("SELECT * FROM orders WHERE 1=1");
    List<Object> parameters = new ArrayList<>();

    filter.forEach((key, value) -> {
      query.append(" AND ").append(key).append(" = ?");
      parameters.add(value);
    });

    List<OrderDTO> orders = new ArrayList<>();

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {

      for (int i = 0; i < parameters.size(); i++) {
        preparedStatement.setObject(i + 1, parameters.get(i));
      }

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          orders.add(populateFromResultSet(resultSet));
        }
      }
    }
    return orders;
  }

  @Override
  public List<OrderDTO> findByAccountAndMonth(AccountDTO account, Month month, Year year) throws SQLException {
    String query = "SELECT * FROM orders WHERE email = ? AND MONTH(order_date) = ? AND YEAR(order_date) = ?";
    List<OrderDTO> orders = new ArrayList<>();

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setString(1, account.getEmail());
      preparedStatement.setInt(2, month.getValue());
      preparedStatement.setInt(3, year.getValue());

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          orders.add(populateFromResultSet(resultSet));
        }
      }
    }
    return orders;
  }

  @Override
  public Year findYearWithMostOrders() throws SQLException {
    String query = "SELECT YEAR(order_date) AS year, COUNT(*) AS quantity FROM orders GROUP BY year ORDER BY quantity DESC LIMIT 1";
    Year year = null;

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery()) {

      if (resultSet.next()) {
        year = Year.of(resultSet.getInt("year"));
      }
    }

    return year;
  }

  @Override
  public HashMap<String, Integer> getCategoryQuantitiesByMonth(Month month, Year year) throws SQLException {
    String query = "SELECT category, COUNT(*) AS quantity FROM orders WHERE MONTH(order_date) = ? AND YEAR(order_date) = ? GROUP BY category";
    HashMap<String, Integer> categoryQuantities = new HashMap<>();

    try (Connection connection = DatabaseConnectionDAO.getConnectionBySchema("uninadelivery");
      PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setInt(1, month.getValue());
      preparedStatement.setInt(2, year.getValue());

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          categoryQuantities.put(resultSet.getString("category"), resultSet.getInt("quantity"));
        }
      }
    }
    return categoryQuantities;
  }
}