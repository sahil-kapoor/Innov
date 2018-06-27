package co.nz.equifax.registration.entity;

import co.nz.equifax.database.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ClientOrg extends AuditModel {

  @Column(name = "client_id", nullable = false)
  private long clientId;
  @Id
  @Column(name = "uuid", nullable = false)
  private String uuid;
  @Column(name = "org_id", nullable = false)
  private long orgId;

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public long getOrgId() {
    return orgId;
  }

  public void setOrgId(long orgId) {
    this.orgId = orgId;
  }
}
