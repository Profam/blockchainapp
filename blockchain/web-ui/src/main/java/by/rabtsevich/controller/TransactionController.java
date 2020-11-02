package by.rabtsevich.controller;


import by.rabtsevich.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @GetMapping("/wallet-list/{walletId}/transaction-list.html")
    public ModelAndView transactionList(
            ModelAndView modelAndView,
            @PathVariable String walletId
    ) {
        log.info("list of transactions by: {}", walletId);
        modelAndView.addObject("transactions", transactionService.getAllTransactions(walletId));
        modelAndView.setViewName("transaction-list");

        return modelAndView;
    }
}
