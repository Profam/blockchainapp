package by.rabtsevich.controller;

import by.rabtsevich.pojo.Block;
import by.rabtsevich.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class BlockController {

    @Autowired
    private BlockService blockService;

    @GetMapping("/")
    public String sayHello() {
        return "Hi from BlockController";
    }

    @GetMapping("/blocks/all")
    public List<Block> blocks() {
        return blockService.getAllBlocks();
    }

    @GetMapping("/blocks/mine")
    public Block saveBlock(@RequestParam String previousHash) throws NoSuchAlgorithmException {
        return blockService.saveNewBlock(previousHash);
    }
}
