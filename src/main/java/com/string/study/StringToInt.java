package com.string.study;

/**
 * Created by wangwei on 16/4/27.
 */
public class StringToInt {

    public static void main(String[] args) {


        //Character.digit
        //返回在10进制下的数字， 返回MIN_RADIX≤基数≤MAX_RADIX 中的数字，如果不是其中的，返回-1
        System.out.println("c:" + Character.digit('c', 16));//在16进制下是  0xC,转换成10进制是 12
        System.out.println("a:" + Character.digit('a', 16));//在16进制下是  0XA,转换成10进制是 10
        System.out.println("f:" + Character.digit('f', 16));//在16进制下是  0XA,转换成10进制是 10
        System.out.println("m:" + Character.digit('m', 16));//在16进制下是  0XA,转换成10进制是 10
        System.out.println("4:" + Character.digit('4', 10));


        System.out.println("-----String 转 Int--------------");


        //12345 字符串转换成 int类型
        String str = "12345";
        char[] chars = str.toCharArray();
        int result = 0;
        int factor = 10;
        for (int i = 0; i < chars.length; i ++) {
            if (i == chars.length - 1) {
                factor = 1;
            }
            //在10进制下的值 加上 结果 再乘以 因子
            result = (Character.digit(chars[i], 10) + result) * factor;
        }
        System.out.println(result);


        System.out.println("-----Int 转 String--------------");

        //int 转String
        int number = 12345;
        String finStr = "";
        while (number != 0) {
            finStr = number % 10 + finStr;
            number = number / 10;
            if (number == 0) {
                break;
            }
        }
        System.out.println(finStr);

    }
}
