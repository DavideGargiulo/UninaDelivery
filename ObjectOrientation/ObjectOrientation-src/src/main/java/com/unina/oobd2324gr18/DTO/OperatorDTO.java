package com.unina.oobd2324gr18.DTO;

import java.time.LocalDate;

public class OperatorDTO extends AccountDTO {
  private String businessEmail;

  private OperatorDTO(final OperatorBuilder builder) {
    super(builder.accountName, builder.accountSurname, builder.accountEmail, builder.accountPassword, builder.accountBirthDate, builder.accountAddress);
    this.businessEmail = builder.businessEmail;
  }

  public static class OperatorBuilder {
    private String accountName;

    private String accountSurname;

    private String accountEmail;

    private LocalDate accountBirthDate;

    private String accountPassword;

    private SavedAddressDTO accountAddress;

    private String businessEmail;

    public OperatorBuilder(final String accountName, final String accountSurname, final String accountEmail, final LocalDate accountBirthDate, final String accountPassword, final SavedAddressDTO accountAddress, final String businessEmail) {
      this.accountName = accountName;
      this.accountSurname = accountSurname;
      this.accountEmail = accountEmail;
      this.accountBirthDate = accountBirthDate;
      this.accountPassword = accountPassword;
      this.accountAddress = accountAddress;
      this.businessEmail = businessEmail;
    }

    public OperatorDTO build() {
      return new OperatorDTO(this);
    }
  }

  public String getBusinessEmail() {
    return businessEmail;
  }

  public OperatorDTO (final AccountDTO account, final String businessEmail) {
    super(account.getName(), account.getSurname(), account.getEmail(), account.getPassword(), account.getBirthDate(), account.getAddress());
    this.businessEmail = businessEmail;
  }

  public AccountDTO getAccount() {
    return new AccountDTO(getName(), getSurname(), getEmail(), getPassword(), getBirthDate(), getAddress());
  }

  @Override
  public String toString() {
    return "OperatorDTO{" +
      "businessEmail='" + getBusinessEmail() + '\'' +
      ", name='" + getName() + '\'' +
      ", surname='" + getSurname() + '\'' +
      ", email='" + getEmail() + '\'' +
      ", password='" + getPassword() + '\'' +
      ", birthDate=" + getBirthDate() +
      ", address=" + getAddress() +
      '}';
  }
}