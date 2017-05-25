package com.qtt.sms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 运营商判断
 * @author pc
 *
 */
public class OperatorsUtil {
	
	/**
	 * 获取手机号运营商
	 * @param mobile
	 * @return 1=移动 2=联通 3=电信 0=未知
	 */
	public static int getOperators(String mobile) {
		int result = 0;
		if (cmccMatcher(mobile)) {
			result = 1;
		} else if (cuccMatcher(mobile)) {
			result = 2;
		} else if (ctccMatcher(mobile)) {
			result = 3;
		}
		return result;
	}
	
	/**
	 * 匹配移动号码
	 * @param mobile
	 * @return
	 */
	public static boolean cmccMatcher(String mobile) {
		//移动号段有134,135,136,137, 138,139,147,150,151, 152,157,158,159,178,182,183,184,187,188
		Pattern pattern = Pattern.compile("^(134|135|136|137|138|139|147|150|151|152|157|158|159|178|182|183|184|187|188)[0-9]{8}$");
		Matcher cmcc = pattern.matcher(mobile);
		return cmcc.matches();
	}
	
	/**
	 * 匹配联通号码
	 * @param mobile
	 * @return
	 */
	public static boolean cuccMatcher(String mobile) {
		//联通号段有130，131，132，155，156，185，186，145，176
		Pattern pattern = Pattern.compile("^(130|131|132|155|156|185|186|145|176)[0-9]{8}$");
		Matcher cucc = pattern.matcher(mobile);
		return cucc.matches();		
	}
	
	/**
	 * 匹配电信号码
	 * @param mobile
	 * @return
	 */
	public static boolean ctccMatcher(String mobile) {
		//电信号段有133，153，177，180，181，189
		Pattern pattern = Pattern.compile("^(133|153|177|180|181|189)[0-9]{8}$");
		Matcher ctcc = pattern.matcher(mobile);		
		return ctcc.matches();
	}
}
