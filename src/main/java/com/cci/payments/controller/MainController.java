package com.cci.payments.controller;
import com.cci.payments.services.DictionaryService;
import com.cci.payments.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DictionaryService dictionaryService;


















//    *********************************** TEST METHODS DELETE ************************

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="SpringBoot")
                                   String name, Model model) {
        model.addAttribute("name", name);
        System.out.println("****************************** GREETING ******************************");
//        TODO add version and release date into html page
//        PaymentDummy payment = new PaymentDummy();
//        payment.setName("Sergio payment");
//        payment.setStartDate(LocalDateTime.now());
//        payment.setDeleted(false);
//        DummyPaymentDTO paymentDTO = paymentService.savePayment(payment);
//        System.out.println("<<------ COntroller DTO: id = " + paymentDTO.getId() + " || " + paymentDTO.getName());
        return "greeting";
    }


//    @GetMapping("/populateDB")
//    public String populateDb(Model model) {
//        int sizePayments = paymentService.populateDB();
//        model.addAttribute("paymentsSizeInserted", sizePayments);
//        return "greeting";
//    }

//    @GetMapping("/sizeOfPayments")
//    public String sizeOfPayments(Model model) {
//        model.addAttribute("paymentsSize", paymentService.countPayments());
//        return "greeting";
//    }

//    DICT test
//    @GetMapping("/populateDictDB")
//    public String populateDictDB(Model model) {
//        int sizePayments = dictionaryService.populateDB();
//        model.addAttribute("dictSizeInserted", sizePayments);
//        return "greeting";
//    }


//    @GetMapping("/sizeOfDict")
//    public String sizeOfDict(Model model) {
//        model.addAttribute("dictSize", dictionaryService.countDict());
//        return "greeting";
//    }

    //    Repo test
//    @GetMapping("/testRepoMethods")
//    public String testRepoMethods() {
//        dictionaryService.testRepoMethods();
//        return "greeting";
//    }


}
