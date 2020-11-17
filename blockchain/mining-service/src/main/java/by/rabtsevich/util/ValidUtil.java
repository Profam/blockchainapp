package by.rabtsevich.util;

import by.rabtsevich.pojo.Block;
import by.rabtsevich.service.BlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ValidUtil {

    @Autowired
    BlockService blockService;

    private final static Logger log = LoggerFactory.getLogger(ValidUtil.class);

    //validation of new created block
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

    /*
    cycle for list of blocks, to check validity of blocks using previous method
     */
    public boolean isBlockchainValid() throws NoSuchAlgorithmException {
        List<Block> blockList = blockService.getAllBlocks();
        for (int i = 1; i < blockList.size(); i++) {
            Block currentBlock = blockList.get(i);
            Block previousBlock = blockList.get(i - 1);

            if (!isValidNewBlock(currentBlock, previousBlock)) {
                log.info("isValidNewBlock {},{},{}", isValidNewBlock(currentBlock, previousBlock), currentBlock, previousBlock);
                return false;
            }
        }
        log.info("blockchain is valid true from utils");
        return true;
    }

    public static String zeros(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append("0".repeat(Math.max(0, length)));
        return builder.toString();
    }
}
