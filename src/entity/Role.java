package entity;

import java.util.Set;

/*import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;*/

/*@Entity
@Table(name = "staff_Role")*/
//@SequenceGenerator(name="pmsSeq",sequenceName="PMS_SEQ")
public class Role{
	
	private Integer roleId;
	private String text;
	private Integer roleNum;
	private String createDate;	
	private String creater;
	private Set<Auth> authSet;

	
	public Role() {
		
	}
	
	


	public Role(Integer roleId, String text, Set<Auth> authSet) {
		super();
		this.roleId = roleId;
		this.text = text;
		this.authSet = authSet;
	}




	public Role(Integer roleId, String text) {
		super();
		this.roleId = roleId;
		this.text = text;
	}



	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", text=" + text + "]";
	}
	/*@Id
	@Column(name = "role_Id", unique = true, nullable = false, length = 25)
	@GeneratedValue(strategy = GenerationType.AUTO)//@GeneratedValue(generator="pmsSeq")
*/	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	//@Column(name = "role_Name",  length = 255)
	public String getText() {
		return text;
	}

	public void setText(String roleName) {
		this.text = roleName;
	}
	
	//@Column(name = "role_Num",  length = 25)
	 public Integer getRoleNum() {
		return roleNum;
	}




	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}



	//@Column(name = "create_Date",  length = 255)
	public String getCreateDate() {
		return createDate;
	}




	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



	//@Column(name = "creater",  length = 255)
	public String getCreater() {
		return creater;
	}




	public void setCreater(String creater) {
		this.creater = creater;
	}




	/*@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST}   
	         )    
	         @JoinTable(name="inner_Role_Auth",    
	                    joinColumns={@JoinColumn(name="role_Id")},    
	                    inverseJoinColumns={@JoinColumn(name="auth_Id")}
	         	
	         ) */
	public Set<Auth> getAuthSet() {
		return authSet;
	}

	public void setAuthSet(Set<Auth> authSet) {
		this.authSet = authSet;
	}

}
