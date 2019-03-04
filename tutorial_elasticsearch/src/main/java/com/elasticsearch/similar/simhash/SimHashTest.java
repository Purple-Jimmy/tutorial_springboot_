package com.elasticsearch.similar.simhash;

/**
 * @Author: jimmy
 * @Date: 2019/3/4
 */
public class SimHashTest {

    public static void main(String[] args) {
        String s = "战狼1";

        SimHash hash1 = new SimHash(s, 64);
        //System.out.println(hash1.intSimHash + "  " + hash1.intSimHash.bitLength());

        hash1.subByDistance(hash1, 3);

        System.out.println("\n");
        s = "战狼1";
        SimHash hash2 = new SimHash(s, 64);
       // System.out.println(hash2.intSimHash+ "  " + hash2.intSimHash.bitCount());
        hash1.subByDistance(hash2, 3);

        int dis = hash1.getDistance(hash1.getStrSimHash(),hash2.getStrSimHash());

        System.out.println(hash1.hammingDistance(hash2) + " "+ dis);



    }
}
