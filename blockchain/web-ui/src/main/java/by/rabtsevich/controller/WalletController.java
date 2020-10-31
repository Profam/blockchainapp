package by.rabtsevich.controller;

import by.rabtsevich.pojo.Wallet;
import by.rabtsevich.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WalletController {

    private static final Logger log = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    WalletService walletService;

    @GetMapping("/{walletId}/wallet-details.html")
    public ModelAndView showWalletForm(
            @PathVariable String walletId,
            ModelAndView modelAndView
    ) {
        Wallet wallet = walletService.get(walletId);
        modelAndView.setViewName("wallet-details");
        modelAndView.addObject("wallet", wallet);
        return modelAndView;
    }
}
