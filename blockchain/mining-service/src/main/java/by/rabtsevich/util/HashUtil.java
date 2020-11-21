package by.rabtsevich.util;

import by.rabtsevich.pojo.Block;
import by.rabtsevich.pojo.Transaction;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

public class HashUtil {

    @SneakyThrows
    public static String generate(Block block) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String s = (block.getTimeStamp() + block.getPreviousHash() + block.getNonce());
        md.update(s.getBytes());
        byte[] digest = md.digest();
        return new String(Base64.getEncoder().encode(digest));
    }

    @SneakyThrows
    public static String generateHashOfTransaction(List<Transaction> transactions) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        StringBuilder sb = new StringBuilder();
        if (transactions == null) {
            md.update(sb.toString().getBytes());
            byte[] digest = md.digest();
            return new String(Base64.getEncoder().encode(digest));
        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            String s = t.getWalletId() + t.getReceiverWalletId() + t.getValue();
            sb.append(s);
        }
        md.update(sb.toString().getBytes());
        byte[] digest = md.digest();
        return new String(Base64.getEncoder().encode(digest));
    }
}
