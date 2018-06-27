package co.nz.equifax.registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import co.nz.equifax.database.AuditModel;

@Entity
public class UuidCode extends AuditModel {

	
	  public UuidCode() {
		super();
	}
	@Id
	  @Column(name = "uuid", nullable = false)
	  private String uuid;
	  @Column(name = "code", nullable = false)
	  private long code;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public UuidCode(String uuid, long code) {
		super();
		this.uuid = uuid;
		this.code = code;
	}

	
	  

}
