package by.rabtsevich.service;

import by.rabtsevich.pojo.Block;
import by.rabtsevich.repo.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class BlockService {
    //validate
    //save in database
    //parametr id wallet user

    @Autowired
    private BlockRepository blockRepository;

    public List<Block> getAllBlocks() {
        return (List<Block>) blockRepository.findAll();
    }

    public Block saveNewBlock(String previousHash) throws NoSuchAlgorithmException {
        Block block = new Block(previousHash);
        return blockRepository.save(block);
    }
}
