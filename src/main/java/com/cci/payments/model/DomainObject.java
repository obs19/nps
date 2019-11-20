package com.cci.payments.model;

import java.io.Serializable;

public interface DomainObject extends Serializable {
	Long getId();
	void setId(Long id);
}
