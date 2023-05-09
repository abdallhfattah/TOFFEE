
public class User {
	private
		String name;
		int age;
		String address;
		String email;
		String phone;
		String password;
		String gender;
		String birthday;

		public String getName() {
      return name;
    }
		public void setName(String name) {
      this.name = name;
    }

		public int getAge() {
      return age;
    }
		public void setAge(int age) {
      this.age = age;
    }

		public String getAddress() {
      return address;
    }
		public void setAddress(String address) {
      this.address = address;
    }

		public String getEmail() {
      return email;
    }
		public void setEmail(String email) {
      this.email = email;
    }

		public String getPhone() {
      return phone;
    }
		public void setPhone(String phone) {
      this.phone = phone;
    }

		public void setPassword(String password) {
      this.password = password;
    }

		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
      this.gender = gender;
		}

		public String getBirthday() {
      return birthday;
    }
		public void setBirthday(String birthday) {
      this.birthday = birthday;
    }
}