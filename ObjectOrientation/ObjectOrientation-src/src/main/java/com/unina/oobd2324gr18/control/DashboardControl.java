package com.unina.oobd2324gr18.control;

import com.unina.oobd2324gr18.DAO.AccountDAO;
import com.unina.oobd2324gr18.DAO.OrderDAO;
import com.unina.oobd2324gr18.DAO.OrderDAOPostgresql;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.control.*;

public final class DashboardControl extends NonLoginControl {

  private OrderDAO orderdao = new OrderDAOPostgresql();

  private static DashboardControl instance;

  protected DashboardControl(final String pageName) {
    super(pageName);
  }

  public static DashboardControl getInstance() {
    if (instance == null) {
      instance = new DashboardControl("Dashboard");
    }
    return instance;
  }

  public DashboardControl goToOrderPage() {
    // TODO
    return this;
  }

  public DashboardControl goTOMonthlyReportPage() {
    // TODO
    return this;
  }

  public DashboardControl getUnfinishedOrderNumber() {
    // TODO
    return this;
  }

  public void editOperatorProfile() {
    // TODO
  }

  public void logout() {
    //TODO
  }
}
