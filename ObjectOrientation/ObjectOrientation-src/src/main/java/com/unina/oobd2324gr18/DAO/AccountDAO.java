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

public interface AccountDAO extends BasicDAO<AccountDTO> {

  AccountDTO findAccountByEmail(String email) throws SQLException;

  AccountDTO findAccountByEmailNPassword(String email, String password) throws SQLException;

  OperatorDTO findOperatorByBusinessemailNPassword(String businessEmail, String password) throws SQLException;

  List<AccountDTO> findAccountNameNSurname(String name, String surname) throws SQLException;

  AccountDTO findMostSpendingAccount(Year year, Month month) throws SQLException;

  List<DriverDTO> findCompatiblesDrivers(WarehouseDTO warehouse, LocalDate date) throws SQLException;
}