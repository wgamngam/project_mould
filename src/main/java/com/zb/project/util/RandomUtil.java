package com.zb.project.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 随机数工具类
 *
 * @author StarLove
 * @version V1.0
 * @ClassName: RandomUtil
 * @Description:
 * @date 2017-06-19 下午5:10:37
 */
public abstract class RandomUtil {

    public static Character[] charArray = {'1', '2', '3', '4', '5', '6', '7',
            '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '^', '!', '$', '*', ')', '&'};

    public static Character[] letterArray = {'1', '2', '3', '4', '5', '6', '7',
            '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};

    static int max = Integer.MAX_VALUE;
    static int lenght = charArray.length;
    static int letter_lenght = letterArray.length;

    /**
     * 获取指定长度的随机字符串
     *
     * @param
     * @return 随机字符串
     * @Title: randomString
     * @Description: 默认返回8位
     */
    public static String randomString() {
        int several = 8;
        StringBuffer sb = new StringBuffer();
        List<Character> tmpList = Arrays.asList(charArray);
        Collections.shuffle(tmpList);
        Random rd = new Random(System.currentTimeMillis());
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < several; i++) {
            int tmp = rd.nextInt(max) % lenght;
            list.add(String.valueOf(tmpList.get(tmp)));
        }
        Collections.shuffle(list);
        for (String str : list) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 获取指定长度的随机字母数字字符串
     *
     * @param several 制定获取的字符串长度
     * @return 随机字符串
     * @Title: randomString
     * @Description:
     */
    public static String letterRandom(int several) {
        return getRandomString(several, letterArray, letter_lenght);
    }

    /**
     * 获取指定长度的随机字母数字字符串
     *
     * @param several 制定获取的字符串长度
     * @return 随机字符串
     * @Title: randomString
     * @Description:
     */
    public static String randomString(int several) {
        return getRandomString(several, charArray, lenght);
    }

    private static String getRandomString(int several, Character[] array, int len) {
        if (several <= 0)
            randomString();
        StringBuffer sb = new StringBuffer();
        List<Character> tmpList = Arrays.asList(array);
        Collections.shuffle(tmpList);
        Random rd = new Random(System.currentTimeMillis());
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < several; i++) {
            int tmp = rd.nextInt(max) % len;
            list.add(String.valueOf(tmpList.get(tmp)));
        }
        Collections.shuffle(list);
        for (String str : list) {
            sb.append(str);
        }
        return sb.toString();
    }


    /**
     * 随机数
     *
     * @return
     */
    public static int randomInt() {
        return randomInt(Integer.MAX_VALUE);
    }

    /**
     * 随机数
     *
     * @param max 最大值
     * @return
     */
    public static int randomInt(int max) {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(max);
    }

    /**
     * 随机数
     *
     * @return
     */
    public static long randomLong() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextLong();
    }

    /**
     * UUID
     */
    public final static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("\\-", "");
    }

}
