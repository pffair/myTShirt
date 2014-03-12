package com.pffair.mytshirt.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class StringUtil {

	public static boolean isEmpty(String s) {
		boolean b = true;
		if (s != null && !s.trim().equals("")) {
			b = false;
		}
		return b;
	}

	public static boolean isEmail(String strEmail) {
		String strPattern = "^([a-z0-9A-Z_]+[-\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

	public static boolean isNumeric(String str) {
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean isPwdMatchRule(String pwd) {
		if (pwd == null)
			return false;
		Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
		Matcher matcher = pattern.matcher(pwd);
		return matcher.matches();
	}

	public static int getChineseCount(String str) {
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0, size = m.groupCount(); i <= size; i++) {
				count = count + 1;
			}
		}
		return count;
	}

	public static boolean isChinese(String str) {
		return java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", str);
	}

	public static boolean isNameMatchRule(String name) {
		if (name == null)
			return false;
		Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public static boolean isNickNameMatchRule(String nickName) {
		int len = nickName.length();
		for (int i = 0; i < len; i++) {
			String str = nickName.substring(i, i + 1);
			if (isChinese(str) || isNameMatchRule(str)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean isTelPhone(String telPhone) {
		if (telPhone == null)
			return false;
		Pattern pattern = Pattern.compile("[0-9-]+");
		Matcher matcher = pattern.matcher(telPhone);
		return matcher.matches();
	}

	public static boolean checkNickNameMinLen(String str, int len) {
		int chinaCount = getChineseCount(str);
		int otherCount = str.length() - chinaCount;
		if (otherCount + chinaCount * 2 < len)
			return false;
		return true;
	}

	public static boolean checkNickNameMaxLen(String str, int len) {
		int chinaCount = getChineseCount(str);
		int otherCount = str.length() - chinaCount;
		if (otherCount + chinaCount * 2 > len)
			return false;
		return true;
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	public static String getMD5Str(String url) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(url.getBytes());
			return new BigInteger(digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException ex) {
			return url;
		}
	}

	public static List<String> getTokenizer(String str, String split) {
		if (TextUtils.isEmpty(str)) {
			return new ArrayList<String>();
		}
		List<String> strs = new ArrayList<String>();
		StringTokenizer strToken = new StringTokenizer(str, split);// 默认不打印分隔符
		while (strToken.hasMoreTokens()) {
			strs.add(strToken.nextToken());
		}
		return strs;
	}

	// 半角转全角
	public static String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	// 全角转半角，适合于文字自动换行
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {// 全角空格为12288，半角空格为32
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)// 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
