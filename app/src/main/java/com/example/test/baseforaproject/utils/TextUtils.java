package com.example.test.baseforaproject.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class TextUtils {

    private static final String REGX_HASHTAG = "[`@!\\&×\\÷~#\\-\\+=\\[\\]{}\\^()<>/;:,.?'|\"\\*%$\\s+\\\\"
            + "•√π£¢€°™®©¶¥₩○�?□■♤♡♢♧¿¡》《¤▪☆]"; // #$%^*()+=\-\[\]\';,.\/{}|":<>?~\\\\

    static {
        PATTERN_HASHTAG = Pattern.compile(REGX_HASHTAG);
    }

    public static String encodeToBase64(CharSequence content) {
        if (content == null) {
            return null;
        }
        byte[] bytes = Base64.encode(content.toString().getBytes(), Base64.DEFAULT);
        return new String(bytes);
    }

    public static Pattern PATTERN_HASHTAG;

    public static boolean isValidColor(String colorCode) {
        final String REGX = "^#([A-Fa-f0-9]{8}|[A-Fa-f0-9]{6})$";
        return colorCode != null && colorCode.matches(REGX);
    }

    public static int getInverseColor(int color) {
        return Color.rgb(255 - Color.red(color), 255 - Color.green(color), 255 - Color.blue(color));
    }

    public static SpannableString getItalicFont(String text) {
        SpannableString spanString = new SpannableString(text);
        spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);
        return spanString;
    }

    public static String encodeToBase64(byte[] data) {
        byte[] bytes = Base64.encode(data, Base64.DEFAULT);
        return new String(bytes).trim();
    }

    public static String decodeBase64(String base64String) {
        if (base64String == null) {
            return null;
        }

        try {
            return new String(Base64.decode(base64String, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return base64String;
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().equals("");
    }

    public static boolean isNullOrEmpty(CharSequence value) {
        return value == null || value.toString().equals("");
    }

    public static boolean isEmpty(EditText editText) {
        return editText == null || isNullOrEmpty(editText.getText().toString());
    }

    public static boolean isEmpty(TextView textView) {
        return textView == null || isNullOrEmpty(textView.getText());
    }

    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidWebUrl(CharSequence target) {
        return target != null && android.util.Patterns.WEB_URL.matcher(target).matches();
    }

    public static String arrayToString(ArrayList<String> array, String delimiter) {
        StringBuilder builder = new StringBuilder();
        if (array.size() > 0) {
            builder.append(array.get(0));
            for (int i = 1; i < array.size(); i++) {
                builder.append(delimiter);
                builder.append(array.get(i));
            }
        }
        return builder.toString();
    }

    public static ArrayList<String> stringToArray(String string) {
        return new ArrayList<>(Arrays.asList(string.split(",")));
    }

    public static String integerArrayToString(ArrayList<Integer> array, String delimiter) {
        StringBuilder builder = new StringBuilder();
        if (array.size() > 0) {
            builder.append(array.get(0));
            for (int i = 1; i < array.size(); i++) {
                builder.append(delimiter);
                builder.append(array.get(i));
            }
        }
        return builder.toString();
    }

    public static String capitalizeFirstLetter(String original) {
        if (original.length() == 0)
            return original;
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static String capitalizeEachWord(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;

        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') {
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static String toUpperCaseFirstCharOfEachWord(String name) {
        if (TextUtils.isNullOrEmpty(name)) {
            return "";
        }

        StringBuilder buffer = new StringBuilder();
        String[] strArr = name.split(" ");
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);
            buffer.append(str).append(" ");
        }

        return buffer.toString().trim();
    }
}
