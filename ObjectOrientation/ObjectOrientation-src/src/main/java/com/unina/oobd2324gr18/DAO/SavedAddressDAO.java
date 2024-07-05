package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;

import com.unina.oobd2324gr18.DTO.SavedAddressDTO;

public interface SavedAddressDAO extends BasicDAO<SavedAddressDTO> {
  SavedAddressDTO extractAddress(String addressCity, String addressProvince, String addressZipCode, String addressStreet, String addressCivicNumber, String addressState) throws SQLException;
}