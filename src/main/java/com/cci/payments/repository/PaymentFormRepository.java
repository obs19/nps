package com.cci.payments.repository;

import com.cci.payments.model.PaymentForm;
import org.springframework.data.repository.CrudRepository;

public interface PaymentFormRepository extends CrudRepository<PaymentForm, Long> {
}
