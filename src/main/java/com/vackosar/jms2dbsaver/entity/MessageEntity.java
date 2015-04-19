package com.vackosar.jms2dbsaver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class MessageEntity {
	@GeneratedValue
	@Id
	long id;
	
	@Version
	long version;
	
	String payload;
	
	public MessageEntity() {
	}
	
	public MessageEntity(String payload) {
		this.payload = payload;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
}
