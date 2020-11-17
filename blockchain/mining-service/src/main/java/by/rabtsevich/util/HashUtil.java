package by.rabtsevich.util;

import by.rabtsevich.pojo.Block;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//import com.google.gson.GsonBuilder;

public class HashUtil {

    @SneakyThrows
    public static String generate(Block block) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String s = (block.getTimeStamp() + block.getPreviousHash());
        md.update(s.getBytes());
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

    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
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
