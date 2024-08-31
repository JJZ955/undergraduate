package webjavaTest.bean;

	public class User {
		private int id;
		private String username;
		private String sex;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", sex=" + sex + "]";
		}
		
}
