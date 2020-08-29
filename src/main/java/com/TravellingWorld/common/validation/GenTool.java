package com.TravellingWorld.common.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class GenTool {

	@SuppressWarnings("rawtypes")
	public static boolean isObjEmpty(final Object obj) {
		boolean flag = false;

		if (obj == null) {
			flag = true;
		} else if (obj instanceof ArrayList) {
			ArrayList arr1 = (ArrayList) obj;
			if (arr1.size() <= 0) {
				flag = true;
			}
		} else if (obj instanceof String) {
			flag = isEmpty((String) obj);
		} else if (obj instanceof Hashtable) {
			Hashtable objHashtable = (Hashtable) obj;
			if (objHashtable.size() <= 0) {
				flag = true;
			}
		} else if (obj instanceof HashMap) {
			HashMap objHashMap = (HashMap) obj;
			if (objHashMap.size() <= 0) {
				flag = true;
			}
		} else if (obj instanceof String[]) {
			String[] arrString = (String[]) obj;
			for (int iCount = 0; iCount < arrString.length; ++iCount) {
				if (!isEmpty(arrString[iCount])) {
					return false;
				}
			}
			return true;
		}

		return flag;
	}

	public static boolean isEmpty(String sVariable) {
		if (sVariable == null || sVariable.isEmpty()) {
			return true;
		}
		return false;
	}
}
