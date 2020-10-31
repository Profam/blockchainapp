package by.rabtsevich.controller;

import by.rabtsevich.pojo.Wallet;
import by.rabtsevich.service.AppUserService;
import by.rabtsevich.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WalletListController {

    @Autowired
    WalletService walletService;

    @Autowired
    AppUserService appUserService;

    @GetMapping("/wallet-list.html")
    public ModelAndView walletList(ModelAndView modelAndView) {
        List<Wallet> wallets = walletService.getAll(
                appUserService.findByUserName(
                        AppUserService.getAuthenticationUserUserName()).getId());
        modelAndView.setViewName("wallet-list");
        modelAndView.addObject("wallets", wallets);
        return modelAndView;
    }
}
