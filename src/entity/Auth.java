package entity;

import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Transient;*/

//@Entity
//@Table(name = "staff_Auth")
//@SequenceGenerator(name="pmsSeq",sequenceName="PMS_SEQ")
public class Auth{
	
	private Integer authId;
	private String text;
	private Integer authLevel;
	private Integer id;
	private Set<Res> resSet;
	private List<Auth> children;
	private String  joinDate;
	private String creater;
	public Auth() {
		// TODO Auto-generated constructor stub
	}

	public Auth(Integer authId, String text, Set<Res> resSet) {
		super();
		this.authId = authId;
		this.text = text;
		this.resSet = resSet;
	}

	@Override
	public String toString() {
		return "Auth [authId=" + authId + ", text=" + text + "]";
	}
	
	//@Id
	//@Column(name = "auth_Id", unique = true, nullable = false, length = 25)
	//@GeneratedValue(generator="pmsSeq")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}
	//@Column(name = "auth_Name",  length = 255)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	// @ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST}    
/*	         )    
	         @JoinTable(name="inner_Auth_Res",    
	                    joinColumns={@JoinColumn(name="auth_Id")},
	                    inverseJoinColumns = {@JoinColumn(name="res_Id")}
	         ) */
	public Set<Res> getResSet() {
		return resSet;
	}

	public void setResSet(Set<Res> resSet) {
		this.resSet = resSet;
	}
	//@Column(name = "auth_Level",  length = 25)
	public Integer getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(Integer authLevel) {
		this.authLevel = authLevel;
	}
	//@Column(name = "parent_Id",  length = 255)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	//@Column(name = "join_Date",  length = 255)
	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	//@Column(name = "creater",  length = 255)
	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	//@Transient
	public List<Auth> getChildren() {
		return children;
	}

	public void setChildren(List<Auth> children) {
		this.children = children;
	}
	

}
