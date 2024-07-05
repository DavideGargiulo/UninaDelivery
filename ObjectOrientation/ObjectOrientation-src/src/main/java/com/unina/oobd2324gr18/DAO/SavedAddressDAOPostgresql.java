package com.unina.oobd2324gr18.DAO;

import java.sql.SQLException;
import java.util.List;

import com.unina.oobd2324gr18.DTO.SavedAddressDTO;
import com.unina.oobd2324gr18.utils.MethodNotImplemented;

public class SavedAddressDAOPostgresql implements SavedAddressDAO {

  @Override
  public SavedAddressDTO extractAddress(final String addressCity, final String addressProvince, final String addressZipCode, final String addressStreet, final String addressCivicNumber, final String addressState) throws SQLException {
    return new SavedAddressDTO (addressCity, addressProvince, Integer.parseInt(addressZipCode), addressStreet, Integer.parseInt(addressCivicNumber), addressState);
  }

  @Override
  public int insert(final SavedAddressDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public List<SavedAddressDTO> findAll() throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int update(final SavedAddressDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }

  @Override
  public int delete(final SavedAddressDTO savedAddressDTO) throws SQLException {
    throw new MethodNotImplemented();
  }
}