package travel.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TokenGenerator {

    private static char[] getDictionary() {
        String number = "123456789";
        String letter = "abcdefghjklmnpqrstuvwxyz";
        String biggerLetter = letter.toUpperCase();
        number += biggerLetter;

        return number.toCharArray();
    }

//    注册邮箱验证码
    public static String emailToken() {
        char[] chars = getDictionary();
        String res = "";

        for (int i = 0; i < 6; i++) {
            res += chars[(int) (Math.random() * chars.length)];
        }

        return res;
    }

//    登陆人机验证码
    public static String getRobotToken() {
        char[] chars = getDictionary();
        String res = "";

        for (int i = 0; i < 4; i++) {
            res += chars[(int) (Math.random() * chars.length)];
        }
        return res;
    }
}
