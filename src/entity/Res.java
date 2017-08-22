package entity;

import java.util.List;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;*/

/*@Entity
@Table(name = "staff_Res")*/
//@SequenceGenerator(name="pmsSeq",sequenceName="PMS_SEQ")
public class Res{
	
	private Integer resId;
	private String text;
	private String servletPath;
	private Integer resCode;
	private Integer resPos;
	private Integer publicStatus;
	private Integer parentStatus;
	private Integer id;
	private String  joinDate;
	private String creater;
	private Integer resLevel;
	private List<Res> children;
	
	
	public Res() {
		
	}

	public Res(Integer resId, String servletPath, Integer resCode,
			Integer resPos, Integer publicStatus) {
		super();
		this.resId = resId;
		this.servletPath = servletPath;
		this.resCode = resCode;
		this.resPos = resPos;
		this.publicStatus = publicStatus;
	}

	@Override
	public String toString() {
		return "Res [resId=" + resId + ", servletPath=" + servletPath
				+ ", resCode=" + resCode + ", resPos=" + resPos
				+ ", publicStatus=" + publicStatus + "]";
	}
	/*@Id
	@Column(name = "res_Id", unique = true, nullable = false, length = 25)
	@GeneratedValue(strategy = GenerationType.AUTO)//@GeneratedValue(generator="pmsSeq")
*/	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}
	//@Column(name = "res_Name",  length = 255)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	//@Column(name = "servlet_Path",  length = 255)
	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	//@Column(name = "res_Code",  length = 25)
	public Integer getResCode() {
		return resCode;
	}

	public void setResCode(Integer resCode) {
		this.resCode = resCode;
	}
	//@Column(name = "res_Pos",  length = 25)
	public Integer getResPos() {
		return resPos;
	}

	public void setResPos(Integer resPos) {
		this.resPos = resPos;
	}
	//@Column(name = "public_Status",  length = 25)
	public Integer getPublicStatus() {
		return publicStatus;
	}

	public void setPublicStatus(Integer publicStatus) {
		this.publicStatus = publicStatus;
	}
	//@Column(name = "parent_Status",  length = 25)
	public Integer getParentStatus() {
		return parentStatus;
	}

	public void setParentStatus(Integer parentStatus) {
		this.parentStatus = parentStatus;
	}
	//@Column(name = "parent_Id",  length = 25)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
//	@Column(name = "join_Date",  length = 255)
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
	
	//@Column(name = "res_Level",  length = 25)
	public Integer getResLevel() {
		return resLevel;
	}

	public void setResLevel(Integer resLevel) {
		this.resLevel = resLevel;
	}
	//@Transient
	public List<Res> getChildren() {
		return children;
	}

	public void setChildren(List<Res> children) {
		this.children = children;
	}


	
	
	

}
