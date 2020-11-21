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
/*
    public boolean verify(String s, String hash) throws NoSuchAlgorithmException {
        String oldHash = generate(s);
        return hash.equals(oldHash);
    }
    */
/*
    //Short hand helper to turn Object into a json string
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

    public static String getData(ArrayList<by.rabtsevich.pojo.Transaction> transactions) throws NoSuchAlgorithmException {
        int count = transactions.size();

        List<String> previousTreeLayer = new ArrayList<String>();
        for(by.rabtsevich.pojo.Transaction transaction : transactions) {
            previousTreeLayer.add(transaction.transactionId);
        }
        List<String> treeLayer = previousTreeLayer;

        while(count > 1) {
            treeLayer = new ArrayList<String>();
            for(int i=1; i < previousTreeLayer.size(); i+=2) {
                treeLayer.add(generate(previousTreeLayer.get(i-1) + previousTreeLayer.get(i)));
            }
            count = treeLayer.size();
            previousTreeLayer = treeLayer;
        }

        String data = (treeLayer.size() == 1) ? treeLayer.get(0) : "";
        return data;
    }*/
}
