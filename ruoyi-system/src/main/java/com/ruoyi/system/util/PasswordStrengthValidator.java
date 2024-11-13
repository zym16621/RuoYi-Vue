package com.ruoyi.system.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthValidator {
    // 定义弱口令的检测规则
    public static boolean isWeakPassword(String password) {
        // 1. 长度小于8
        if (password.length() < 8) {
            return true;
        }

        // 2. 包含常见弱口令
        String[] weakPasswords = {"123456", "password", "qwerty", "123456789", "letmein", "welcome"};
        for (String weak : weakPasswords) {
            if (password.equalsIgnoreCase(weak)) {
                return true;
            }
        }

        // 3. 没有包含大小写字母、数字和特殊字符
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return true;
        }

        // 4. 口令是重复的字符
        if (password.matches("(.)\\1{2,}")) {
            return true;
        }

        // 5. 包含字典单词（简单实现）
        String[] dictionaryWords = {"password", "admin", "user", "hello"};
        for (String word : dictionaryWords) {
            if (password.toLowerCase().contains(word)) {
                return true;
            }
        }

        return false;  // 如果没有触发任何弱口令规则
    }

    public static void main(String[] args) {
        // 测试口令
        String password = "zym16621";
        if (isWeakPassword(password)) {
            System.out.println(password+"：该口令是弱口令");
        } else {
            System.out.println("该口令是强口令");
        }

    }
}
