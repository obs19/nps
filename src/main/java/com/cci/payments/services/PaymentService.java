package com.cci.payments.services;

import com.cci.payments.dto.*;
import com.cci.payments.model.Payment;
import com.cci.payments.model.PaymentForm;
import com.cci.payments.model.PaymentStatus;
import com.cci.payments.model.User;
import com.cci.payments.repository.PaymentFormRepository;
import com.cci.payments.repository.PaymentRepository;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentFormRepository paymentFormRepository;
    @Autowired
    private Mapper beanMapper;
//    @Value("${tab.history.items}")
//    private int HISTORY_PAGE_SIZE;
    @Autowired
    private UserService userService;

    public List<PaymentLastHistoryDTO> getLasHistory(){
        EnumSet<PaymentStatus> statuses = EnumSet.of(PaymentStatus.COMPLETED);
        //TODO change user to correct
        Long userId = userService.findDummyTestUserId();
        List<Payment> paymentList = paymentRepository.findTop12ByStatusInAndUserIdOrderByCompletedDateDesc(statuses, userId);
        List<PaymentLastHistoryDTO> dtoList = new ArrayList<>();
        paymentList.forEach(p -> {
            dtoList.add(beanMapper.map(p, PaymentLastHistoryDTO.class));
        });
        return dtoList;
    }


    public Payment saveNewPayment(PaymentFormInDTO dto){
        //TODO change user to correct
        User user = userService.findDummyTestUser();
        Payment payment = new Payment();
        payment.setUser(user);
        PaymentForm mappedForm = beanMapper.map(dto, PaymentForm.class);
        PaymentForm savedForm = paymentFormRepository.save(mappedForm);
        payment.setForm(savedForm);
        payment.setCreationDate(LocalDateTime.now());
        if (dto.isAddToBasket()){
            payment.setStatus(PaymentStatus.CREATED);
        } else {
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setCompletedDate(LocalDateTime.now());
        }
        return paymentRepository.save(payment);
    }


    public List<Long> savePaymentsFromBasket(List<Long> idList){
        ArrayList<Long> processedPayments = new ArrayList<>();
        idList.forEach(id -> {
            Payment payment = paymentRepository.findById(id).orElseThrow(
                    () -> new NoSuchElementException("Can't find Payment (from basket) with ID = " + id));
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setCompletedDate(LocalDateTime.now());
            processedPayments.add(paymentRepository.save(payment).getId());
        });
        return processedPayments;
    }


    public Long delete(Long id){
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find Payment (from basket) with ID = " + id));
        payment.setStatus(PaymentStatus.DELETED);
        payment.setDeleted(true);
        return paymentRepository.save(payment).getId();
    }


    public PageDTOGeneric getPaymentHistory(int page, int start, int limit){
        EnumSet<PaymentStatus> allowedStatuses = EnumSet.of(PaymentStatus.COMPLETED, PaymentStatus.DELETED);
        //TODO change user to correct
        Long userId = userService.findDummyTestUserId();
        Page<Payment> paymentPage = paymentRepository.findByStatusInAndUserId(allowedStatuses, userId,
                PageRequest.of(page-1, limit, Sort.by("completedDate").descending()));
            List<PaymentHistoryDTO> dtoList = new ArrayList<>();
            paymentPage.getContent().forEach(p -> {
                dtoList.add(beanMapper.map(p, PaymentHistoryDTO.class));
            });
            return new PageDTOGeneric(true, paymentPage.getTotalElements(), dtoList);
    }


    public PaymentDTO getById(Long id){
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find Payment with ID = " + id));
        return beanMapper.map(payment, PaymentDTO.class);
    }


    public List<PaymentDTO> getBasketItems(){
        EnumSet<PaymentStatus> allowedStatuses = EnumSet.of(PaymentStatus.CREATED, PaymentStatus.POSTPONED);
        //TODO change user to correct
        Long userId = userService.findDummyTestUserId();
        List<Payment> paymentList = paymentRepository.findByStatusInAndUserIdOrderByIdDesc(allowedStatuses, userId);
        List<PaymentDTO> dtoList = new ArrayList<>();
        paymentList.forEach(p -> {
            dtoList.add(beanMapper.map(p, PaymentDTO.class));
        });
        return dtoList;
    }


    public Long setPostponedDate(String date, Long id){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime postponedDate = LocalDateTime.parse(date, formatter);
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find Payment with ID = " + id));
        payment.setPostponedDate(postponedDate);
        payment.setStatus(PaymentStatus.POSTPONED);
        return paymentRepository.save(payment).getId();
    }

    @Transactional
    public int processPostponedPayment(){
        EnumSet<PaymentStatus> statuses = EnumSet.of(PaymentStatus.POSTPONED);
        List<Payment> payments = paymentRepository.findByStatusInAndPostponedDateBeforeOrderByIdDesc(statuses, LocalDateTime.now());
        ArrayList<Long> processedPaymentsIds = new ArrayList<>();
        payments.forEach(payment -> {
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setCompletedDate(LocalDateTime.now());
            processedPaymentsIds.add(paymentRepository.save(payment).getId());
        });
        return processedPaymentsIds.size();
    }

}
