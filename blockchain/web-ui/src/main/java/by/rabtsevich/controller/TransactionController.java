package by.rabtsevich.controller;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/wallet-list/{walletId}/transaction-list.html")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ModelAndView transactionList(
            ModelAndView modelAndView,
            @PathVariable String walletId
    ) {
        log.info("transaction list by: {}", walletId);
        modelAndView.addObject("transactions", transactionService.getAllTransactions(walletId));
        modelAndView.setViewName("transaction-list");
        return modelAndView;
    }

    @PostMapping
    public String acceptTransaction(
            @RequestParam("senderSecretKey") String secretKey,
            @RequestParam("id") String id,
            Model model) {
        Transaction transaction = transactionService.getTransaction(id);
        if (transactionService.verifySecretkey(transaction, secretKey)) {
            transactionService.transferBalanceFromWalletToWallet(transaction);
            return "redirect:/wallet-list/{walletId}/transaction-list.html";
        } else {
            model.addAttribute("errorMessage", "Wrong secret key, try again.");
            return "error-page";
        }
    }
}
