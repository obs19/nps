package com.cci.payments.repository.test;

import com.cci.payments.model.*;
import com.cci.payments.model.dictionaries.*;
import com.cci.payments.repository.DictionaryRepository;
import com.cci.payments.repository.PaymentFormRepository;
import com.cci.payments.repository.PaymentRepository;
import com.cci.payments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"dev","test"})
public class PopulateRepoService {
    private int numOfPayment = 35;
    private int numOfDict = 3;
    private int numOfUsers = 15;

    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private PaymentFormRepository paymentFormRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRepoPopulation(){
        System.out.println("<<******* Start Population repo **********************************");
        populateDictRepo();
        populatePaymentRepo();
        populateUserRepo();
        System.out.println("<<******* End Population repo **********************************");
    }

    private void populateDictRepo(){
        System.out.println("<-------START insert DICTIONARIES into db  ");
        List<Dictionary> dummyDictList = getDummyDictList();
        //List<DictDummyDTO> dtoList = new ArrayList<>();
        dummyDictList.forEach(d ->{
            Dictionary savedDict = dictionaryRepository.save(d);
            //DictDummyDTO dto = beanMapper.map(savedDict, DictDummyDTO.class);
        });
        System.out.println("<-------END insert DICTIONARIES into db COMPLETE total = " + dummyDictList.size());
    }

    private void populatePaymentRepo(){
        System.out.println("<-------START insert PAYMENTS into db  ");
        List<Payment> paymentList = getDummyPaymentList();
        paymentList.forEach(p -> {
            Payment savedPayment = paymentRepository.save(p);
        });
        System.out.println("<-------END insert PAYMENTS into db COMPLETE total = " + paymentList.size());
    }

    private void populateUserRepo(){
        System.out.println("<-------START insert USERS into db  ");
        List<User> userList = getDummyUserList();
        userList.forEach(p -> {
            userService.save(p);
        });
        System.out.println("<-------END insert USERS into db COMPLETE total = " + userList.size());
    }

    private List<User> getDummyUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < numOfUsers; i++) {
            User user = new User();
            user.setLogin("login_dummy_" + i);
            user.setPass("pass_dummy_" + i);
            user.setFullName("fullName_dummy_" + i);
            user.setDeleted(false);
            if (i<=10){
                user.setStatus(UserActivityStatus.ACTIVE);
            } else {
                user.setStatus(UserActivityStatus.BLOCKED);
            }
            userList.add(user);
        }
        return userList;
    }

    private List<Dictionary> getDummyDictList(){
        List<Dictionary> dictionaryList = new ArrayList<>();
        for (int i = 0; i < numOfDict; i++) {
            DictTypeOfPayment d = new DictTypeOfPayment();
            d.setName("01-DictTypeOfPayment_Name_"+i);
            d.setExtId(String.valueOf(i));
            d.setOrdinal((long) i);
            d.setDescription("DictTypeOfPayment _Description_"+i);
            dictionaryList.add(d);
        }
        for (int i = 0; i < numOfDict; i++) {
            DictRegisterName d = new DictRegisterName();
            d.setName("02-DictRegisterName_Name_"+i);
            d.setExtId(String.valueOf(i));
            d.setOrdinal((long) i);
            d.setDescription("DictRegisterName _Description_"+i);
            dictionaryList.add(d);
        }
        for (int i = 0; i < numOfDict; i++) {
            DictPaymentAction d = new DictPaymentAction();
            d.setName("03-DictPaymentAction_Name_"+i);
            d.setExtId(String.valueOf(i));
            d.setOrdinal((long) i);
            d.setDescription("DictPaymentAction _Description_"+i);
            dictionaryList.add(d);
        }
        for (int i = 0; i < numOfDict; i++) {
            DictCountryRegion d = new DictCountryRegion();
            d.setName("04-DictCountryRegion_Name_"+i);
            d.setExtId(String.valueOf(i));
            d.setOrdinal((long) i);
            d.setDescription("DictCountryRegion _Description_"+i);
            dictionaryList.add(d);
        }
        return dictionaryList;
    }

    private List<Payment> getDummyPaymentList(){
        List<Payment> paymentList = new ArrayList<>();
        User user = new User();
        user.setLogin("sergey");
        user.setFullName("Sergio Leone");
        user.setPass("pass_secret666");
        user.setStatus(UserActivityStatus.ACTIVE);
        User persistedUser = userService.save(user);
        DictTypeOfPayment dict1 = (DictTypeOfPayment) dictionaryRepository.findDictionaryByTypeAndExtId(DictTypeEnum.DICT_TYPE_OF_PAYMENT, "1");
        DictRegisterName dict2 = (DictRegisterName) dictionaryRepository.findDictionaryByTypeAndExtId(DictTypeEnum.DICT_REGISTER_NAME, "1");
        DictPaymentAction dict3 = (DictPaymentAction) dictionaryRepository.findDictionaryByTypeAndExtId(DictTypeEnum.DICT_PAYMENT_ACTION, "1");
        DictCountryRegion dict4 = (DictCountryRegion) dictionaryRepository.findDictionaryByTypeAndExtId(DictTypeEnum.DICT_COUNTRY_REGION, "1");
        for (int i = 0; i < numOfPayment; i++) {
            PaymentForm pf = new PaymentForm();
            pf.setClientName("Customer_First_Name_"+i+" LastName");
            pf.setClientITN("100020003000"+i);
            pf.setDictTypeOfPayment(dict1);
            pf.setDictRegisterName(dict2);
            pf.setDictPaymentAction(dict3);
            pf.setDictCountryRegion(dict4);
            pf.setRecipient("Recipient_First_name_"+i+" LastName");
            pf.setAmount(new BigDecimal(100.31+i));
            pf.setAmountTotal(new BigDecimal(120.66+i));
            PaymentForm savedForm = paymentFormRepository.save(pf);

            Payment p = new Payment();
            p.setUser(persistedUser);
            p.setForm(savedForm);
            LocalDateTime now = LocalDateTime.now();

            if (i%2 == 0){
                p.setStatus(PaymentStatus.CREATED);
                p.setCreationDate(now);
            } else {
                p.setStatus(PaymentStatus.COMPLETED);
                p.setCreationDate(now.minusDays(1+i));
                p.setCompletedDate(now.minusHours(1+i));
            }
            paymentList.add(p);
        }
        return paymentList;
    }

}
