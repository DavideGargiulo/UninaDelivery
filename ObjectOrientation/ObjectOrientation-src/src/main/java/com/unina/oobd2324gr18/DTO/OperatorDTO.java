package com.unina.oobd2324gr18.DTO;

import java.time.LocalDate;

public class OperatorDTO extends AccountDTO {

  // Attributes
  private String businessMail;

  // Private constructor
  private OperatorDTO(String accName, String accSurname, String accEmail, LocalDate accBdate, String accPropic,
                   String accPassword, SavedAddressDTO accAddress, String businessMail) {
    super(accName, accSurname, accEmail, accBdate, accPropic, accPassword, accAddress);
    this.businessMail = businessMail;
  }

  // Factory method
  public static OperatorDTO createOperator(String accName, String accSurname, String accEmail, LocalDate accBdate, String accPassword, SavedAddressDTO accAddress, String businessMail) {
    return new OperatorDTO(accName, accSurname, accEmail, accBdate, accPassword, accAddress, businessMail);
  }

  // Getters and Setters
  public String getBusinessMail() {
    return businessMail;
  }

  public void setBusinessMail(String businessMail) {
    this.businessMail = businessMail;
  }

  // Override toString
  @Override
  public String toString() {
    return "Operator{" +
            "name='" + getName() + '\'' +
            ", surname='" + getSurname() + '\'' +
            ", email='" + getEmail() + '\'' +
            ", birthdate='" + getBirthDate() + '\'' +
            ", password='" + getPassword() + '\'' +
            ", address=" + getAddress().toString().replace("\n", "\n\t") +
            ", businessMail='" + businessMail + '\'' +
            '}';
  }
}
