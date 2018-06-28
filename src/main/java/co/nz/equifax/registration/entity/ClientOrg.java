package co.nz.equifax.registration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import co.nz.equifax.commons.database.AuditModel;

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
