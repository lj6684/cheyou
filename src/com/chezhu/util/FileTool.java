package com.chezhu.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class FileTool {

	/**
	 * 产生随机文件名称
	 * @param fileName
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String formatDate = format.format(new Date());

		int random = new Random().nextInt(10000);

		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);

		return formatDate + "_" + random + extension;
	}
	
	/**
	 * 产生拼音文件名称
	 * @param name
	 * @param fileName
	 * @return
	 */
	public static String generatePinyinFileName(String name, String fileName) {
		String res = getPinyin(name);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		
		return res + extension;
	}
	
	public static String getPinyin(String name) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String res = "";
		try {
			for(int i = 0; i < name.length(); i++) {
				String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(name.charAt(i), format);
				res += pinyin[0];
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}

}
