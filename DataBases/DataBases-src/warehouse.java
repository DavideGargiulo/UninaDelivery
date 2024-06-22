import WarehouseDAO;

import java.sql.SQLException;
import java.util.List;

public class Warehouse {
    private int warehouseId;
    private String name;
    private int maxCapacity;
    private int occupiedSpace;
    private String vat;
    private int shipmentId;

    // Costruttore vuoto
    public Warehouse() {
    }

    // Costruttore con parametri
    public Warehouse(int warehouseId, String name, int maxCapacity, int occupiedSpace, String vat, int shipmentId) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.occupiedSpace = occupiedSpace;
        this.vat = vat;
        this.shipmentId = shipmentId;
    }

    // Getter e setter per warehouseId
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    // Getter e setter per name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter e setter per maxCapacity
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Getter e setter per occupiedSpace
    public int getOccupiedSpace() {
        return occupiedSpace;
    }

    public void setOccupiedSpace(int occupiedSpace) {
        this.occupiedSpace = occupiedSpace;
    }

    // Getter e setter per vat
    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    // Getter e setter per shipmentId
    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }
}

public class Main {
    public static void main(String[] args) {
        WarehouseDAO warehouseDAO = new WarehouseDAO(); // Assumi che WarehouseDAO sia già definito

        try {
            List<Warehouse> warehouses = warehouseDAO.getAllWarehouses();
            for (Warehouse warehouse : warehouses) {
                System.out.println("ID Magazzino: " + warehouse.getWarehouseId());
                System.out.println("Nome: " + warehouse.getName());
                System.out.println("Capacità Massima: " + warehouse.getMaxCapacity());
                System.out.println("Spazio Occupato: " + warehouse.getOccupiedSpace());
                System.out.println("Partita IVA: " + warehouse.getVat());
                System.out.println("ID Spedizione: " + warehouse.getShipmentId());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}