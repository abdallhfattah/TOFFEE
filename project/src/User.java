public class User {
  private String UserName;
  private String Password;
  private String Address;
  private String PhoneNumber;
  private String Email;
  private String Gender;

  User() {}
  public User(String username, String password, String address, String phoneNumber, String gender, String email) {
    this.UserName = username;
    this.Password = password;
    this.Address = address;
    this.PhoneNumber = phoneNumber;
    this.Gender = gender;
    this.Email = email;
  }

  public String getAddress() {
    return Address;
  }

  public String getPhoneNumber() {
    return PhoneNumber;
  }

  public String getEmail() {
    return Email;
  }

  public String getGender() {
    return Gender;
  }

  public String getUsername() {
    return UserName;
  }

  public String getPassword() {
    return Password;
  }
}