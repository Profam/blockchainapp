package by.rabtsevich.service;

import by.rabtsevich.pojo.Block;
import by.rabtsevich.util.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ValidationBlockService {

    @Autowired
    BlockService blockService;

    private final static Logger log = LoggerFactory.getLogger(ValidationBlockService.class);

    //validate new created block
    public boolean isValidNewBlock(Block newBlock, Block previousBlock) throws NoSuchAlgorithmException {
        if (newBlock != null && previousBlock != null) {
            if (previousBlock.getBlockId() + 1 != newBlock.getBlockId()) {
                return false;
            }
            if (newBlock.getPreviousHash() == null ||
                    !newBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
            if (newBlock.getHash() == null ||
                    !HashUtil.generate(newBlock).equals(newBlock.getHash())) {
                return false;
            }
            return true;
        }
        return false;
    }

    // validation of all blocks
    public boolean isBlockchainValid() throws NoSuchAlgorithmException {
        List<Block> listOfBlocks = blockService.getAllBlocks();
        for (int i = 1; i < listOfBlocks.size(); i++) {
            Block currentBlock = listOfBlocks.get(i);
            Block previousBlock = listOfBlocks.get(i - 1);

            if (!isValidNewBlock(currentBlock, previousBlock)) {
                log.info("isValidNewBlock {},{},{}", isValidNewBlock(currentBlock, previousBlock), currentBlock, previousBlock);
                return false;
            }
        }
        log.info("blockchain is valid true");
        return true;
    }

    //the level of difficulty of generation a hash function
    public static String zeros(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append("0".repeat(Math.max(0, length)));
        return builder.toString();
    }
}
