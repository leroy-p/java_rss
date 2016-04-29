import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String passwd;
	private String authKey;
	private Integer id;
	
	public Users() {
		
	}
	
	public Users(String email, String passwd, String authKey, Integer id) {
		this.email = email;
		this.passwd = passwd;
		this.authKey = authKey;
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email", nullable = false, length = 70)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "passwd", nullable = false, length = 70)
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	@Column(name = "auth_key", nullable = false, length = 255)
	public String getAuthKey() {
		return authKey;
	}
	
	pubic void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
}
