package by.rabtsevich.controller;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wallet-list/{walletId}/new-transaction.html")
public class NewTransactionController {
    private static final Logger log = LoggerFactory.getLogger(NewTransactionController.class);

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public String showNewTransaction(@PathVariable String walletId) {
        return "new-transaction";
    }

    @PostMapping
    public String createNewTransaction(
            @PathVariable String walletId,
            String secretKey,
            @ModelAttribute Transaction transaction,
            Model model) {
        log.info("New transaction: {}", transaction);
        if (transactionService.validateTransaction(transaction, walletId, secretKey)) {
            return "redirect:/wallet-list/{walletId}/transaction-list.html";
        } else {
            model.addAttribute("errorMessage", "can not create a new transaction");
            return "error-page";
        }
    }
}

