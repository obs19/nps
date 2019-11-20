package com.cci.payments.repository;

import com.cci.payments.model.Payment;
import com.cci.payments.model.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface PaymentRepository extends CrudRepository<Payment, Long> {

    Optional<Payment> findById(Long id);

    List<Payment> findTop12ByStatusInAndUserIdOrderByCompletedDateDesc(Collection<PaymentStatus> status, Long userId);

    Page<Payment> findByStatusInAndUserId(Collection<PaymentStatus> status, Long userId, Pageable pageable);

    List<Payment> findByStatusInAndUserIdOrderByIdDesc(Collection<PaymentStatus> status, Long userId);

    List<Payment> findByStatusInAndPostponedDateBeforeOrderByIdDesc(Collection<PaymentStatus> status, LocalDateTime postponedDate);

//    ------------------------- TODO delete TEST methods -----------------------
//    List<Payment> findTop12ByStatusInOrderByIdDesc(Collection<PaymentStatus> status);
//    Page<Payment> findByStatusInOrderByIdDesc(Collection<PaymentStatus> status, Pageable pageable);
//    Page<PaymentDummy> findAllByOrderByIdDesc(Pageable pageable);
//    List<Payment> findTop6AllByOrderByIdDesc();
//    List<Payment> findTop6ByStatusIn(Collection<PaymentStatus> status);


    //find last 10 completed payments
    List<Payment> findTop6ByStatusInOrderByIdDesc(Collection<PaymentStatus> status);
    List<Payment> findTop6ByOrderByIdDesc();
}
