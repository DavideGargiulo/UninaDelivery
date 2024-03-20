import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:5432/uninaDelivery";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "Bnbnmvzx123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public WarehouseDAO() {

    }

    // Metodo per inserire un nuovo Warehouse
    public void addWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "INSERT INTO Warehouse (WarehouseId, Name, MaxCapacity, OccupiedSpace, VAT, ShipmentId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, warehouse.getWarehouseId());
            statement.setString(2, warehouse.getName());
            statement.setInt(3, warehouse.getMaxCapacity());
            statement.setInt(4, warehouse.getOccupiedSpace());
            statement.setString(5, warehouse.getVat());
            statement.setInt(6, warehouse.getShipmentId());
            statement.executeUpdate();
        }
    }

    // Metodo per ottenere un Warehouse tramite il suo ID
    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        String sql = "SELECT * FROM Warehouse WHERE WarehouseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, warehouseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Warehouse(
                        resultSet.getInt("WarehouseId"),
                        resultSet.getString("Name"),
                        resultSet.getInt("MaxCapacity"),
                        resultSet.getInt("OccupiedSpace"),
                        resultSet.getString("VAT"),
                        resultSet.getInt("ShipmentId"));
            }
        }
        return null;
    }

    // Metodo per aggiornare un Warehouse
    public void updateWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "UPDATE Warehouse SET Name = ?, MaxCapacity = ?, OccupiedSpace = ?, VAT = ?, ShipmentId = ? WHERE WarehouseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, warehouse.getName());
            statement.setInt(2, warehouse.getMaxCapacity());
            statement.setInt(3, warehouse.getOccupiedSpace());
            statement.setString(4, warehouse.getVat());
            statement.setInt(5, warehouse.getShipmentId());
            statement.setInt(6, warehouse.getWarehouseId());
            statement.executeUpdate();
        }
    }

    // Metodo per eliminare un Warehouse
    public void deleteWarehouse(int warehouseId) throws SQLException {
        String sql = "DELETE FROM Warehouse WHERE WarehouseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, warehouseId);
            statement.executeUpdate();
        }
    }

    // Metodo per ottenere tutti i Warehouse
    public List<Warehouse> getAllWarehouses() throws SQLException {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM Warehouse";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                warehouses.add(new Warehouse(
                        resultSet.getInt("WarehouseId"),
                        resultSet.getString("Name"),
                        resultSet.getInt("MaxCapacity"),
                        resultSet.getInt("OccupiedSpace"),
                        resultSet.getString("VAT"),
                        resultSet.getInt("ShipmentId")));
            }
        }
        return warehouses;
    }
}