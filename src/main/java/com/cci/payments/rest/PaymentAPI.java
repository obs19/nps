package com.cci.payments.rest;

import com.cci.payments.dto.*;
import com.cci.payments.model.Payment;
import com.cci.payments.model.PaymentForm;
import com.cci.payments.services.AppMessageService;
import com.cci.payments.services.PaymentService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@RequestMapping("/rest/payment")
public class PaymentAPI {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AppMessageService appMessageService;


    @GetMapping(value = "/last-history")
    public ResponseEntity<List<PaymentLastHistoryDTO>> getLastHistory(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.getLasHistory());
    }


    @PostMapping(value = "/save")
    @Transactional
    public ResponseEntity<?> processPayment(PaymentFormInDTO dto, Locale locale){
        String msg;
        paymentService.saveNewPayment(dto);
        if (dto.isAddToBasket()){
            msg = appMessageService.getMessage("newPaymentFormMsgSuccessAddedToBasket", null, locale);
        } else {
            msg = appMessageService.getMessage("newPaymentFormMsgSuccessPay",null, locale);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new EditResponse(true, msg));
    }


    @GetMapping(value = "/history")
    public ResponseEntity<PageDTOGeneric> getPaymentHistory(@RequestParam(name = "page") int page,
                                                @RequestParam(name = "start") int start,
                                                @RequestParam(name = "limit") int limit){
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.getPaymentHistory(page, start, limit));
    }


    @GetMapping
    public ResponseEntity<PaymentDTO> getById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.getById(id));
    }


    @GetMapping(value = "/basket")
    public ResponseEntity<List<PaymentDTO>> getBasketItems(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.getBasketItems());
    }


    @PostMapping(value = "/basket/save")
    @Transactional
    public ResponseEntity<List<Long>> saveSelectedPayments(@RequestParam(name = "idList") List<Long> idList) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.savePaymentsFromBasket(idList));
    }


    @DeleteMapping(value = "/delete")
    @Transactional
    public ResponseEntity<Long> deletePayment(@RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.delete(id));
    }


    @PutMapping(path = "/update")
    @Transactional
    public ResponseEntity<Long> updatePayment(@RequestParam Map<String, String> params) {
        Long id = Long.valueOf(params.get("id"));
        Long updatedPaymentId = null;
        if (!params.get("postponedDate").trim().isEmpty()){
            updatedPaymentId = paymentService.setPostponedDate(params.get("postponedDate"), id);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedPaymentId);
    }


//    ****************************** Test MEthod for DElete *****************


//    @PostMapping(path = "/update")
//    public ResponseEntity<?> updatePayment(@RequestParam Map<String, String> body) throws ParseException {
//        System.out.println("<==== UPDATE = : " + body.toString());
//
//        if (!body.get("startDate").trim().isEmpty()){
//            String startDate = body.get("startDate");
//            Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(startDate);
//            System.out.println("<==== DATE : " + date2.toString());
//        }
//        if (!body.get("name").trim().isEmpty()){
//            String val = body.get("name");
//            System.out.println("<==== name : " + val);
//        }
//        if (!body.get("text").trim().isEmpty()){
//            String val = body.get("text");
//            System.out.println("<==== text : " + val);
//        }
//
//        String msg = "Successfully updated !";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
//    }


}
