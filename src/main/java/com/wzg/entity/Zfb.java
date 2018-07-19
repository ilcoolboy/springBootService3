package com.wzg.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="zfb")
public class Zfb {
	@Id
	@GenericGenerator(name = "zfbGid", strategy = "guid")
	@GeneratedValue(generator = "zfbGid")
	private String gid;
	private String name;
	private BigDecimal account;
	private Date lasttime;
	private String bgid;
	public String getBgid() {
		return bgid;
	}
	public void setBgid(String bgid) {
		this.bgid = bgid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public Date getLasttime() {
		return lasttime;
	}
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

}
