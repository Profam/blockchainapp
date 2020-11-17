package by.rabtsevich.controller;

import by.rabtsevich.service.MiningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class MiningController {

    private static final Logger log = LoggerFactory.getLogger(MiningController.class);

    @Autowired
    private MiningService miningService;

    @GetMapping("/mine/{walletId}")
    public ResponseEntity mine(@PathVariable String walletId) throws NoSuchAlgorithmException, InterruptedException {
        log.info("Mining process start for walletId {}", walletId);
        if (miningService.mine(walletId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            log.error("Mining stopped");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
