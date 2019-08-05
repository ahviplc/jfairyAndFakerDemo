package com.lc.demoapp.utils;

import java.util.Collection;

/**
 * 集合工具类
 * @Title CollectionUtil.java
 * @Package com.sinotn.online.util
 * @Description
 * Copyright: Copyright (c) 2018 
 * Company:lmt
 * @author 
 * @date 2018年6月11日10:27:09
 * @version V1.0
 */
@SuppressWarnings("all")
public class CollectionUtil {
	
	/**
	 * 
	 * 判断集合是否为空
	 * @param obj
	 * @return
	 * @auth 
	 * @date 2016年5月9日 下午3:31:30
	 */
	public static boolean isEmpty(Collection obj) {
		if (obj == null) {
			return true;
		}
		return obj.isEmpty();
	}
}
