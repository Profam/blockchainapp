package by.rabtsevich.service;

import by.rabtsevich.pojo.Block;
import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.repository.BlockRepository;
import by.rabtsevich.repository.TransactionRepository;
import by.rabtsevich.util.HashUtil;
import by.rabtsevich.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class BlockService {

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Block createBlock(Transaction transaction) throws NoSuchAlgorithmException {
        Block block = new Block();
        block.setBlockId(getLastBlock().getBlockId() + 1);
        block.setTimeStamp(new Date().getTime());
        block.setPreviousHash(getLastBlock().getHash());
        block.setHash(HashUtil.generate(block));
        block.setNonce(0);
        if (transaction != null) {
            transaction.setTransactionStatus("Accepted");
            transactionRepository.save(transaction);
            block.setTransaction(transaction.toString());
        }
        return block;
    }

    public Block mineBlock(Block blockToMine, int difficulty) throws NoSuchAlgorithmException {
        int nonce = blockToMine.getNonce();
        blockToMine.setHash(HashUtil.generate(blockToMine));
        while (!blockToMine.getHash().substring(0, difficulty).equals(ValidUtil.zeros(difficulty))) {
            nonce++;
            blockToMine.setNonce(nonce);
            blockToMine.setHash(HashUtil.generate(blockToMine));
        }
        return blockToMine;
    }

    public Block saveNewBlock(Block block) {
        return blockRepository.save(block);
    }

    public Block getFirstBlock(long id) {
        return blockRepository.findById(id).orElse(null);
    }

    public Block getLastBlock() {
        return blockRepository.findFirstByTimestamp();
    }

    public List<Block> getAllBlocks() {
        return (List<Block>) blockRepository.findAll();
    }
}
