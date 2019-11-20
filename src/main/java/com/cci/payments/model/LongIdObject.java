package com.cci.payments.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class LongIdObject implements DomainObject{

	@Id
	@GeneratedValue
	private Long id;
	@Version
	private Integer version;


	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this.getClass().isAssignableFrom(o.getClass())) {
			LongIdObject objectLongId = (LongIdObject)o;
			return (this.getId() != null && objectLongId.getId() != null) && this.getId().equals(objectLongId.getId());
		} else {
			return this == o;
		}
	}

	public int hashCode() {
		try {
			return this.getId() != null ? this.getId().hashCode() : super.hashCode();
		} catch (Throwable var2) {
			throw new RuntimeException(var2);
		}
	}
}
