package org.leonlong.android.moneymaker.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsUtil {

	private static final String PREFS_NAME = "moneymaker";
	private static final String PREFS_IS_FIRST_BOOT = "is_first_boot";

	public static boolean getIsFirstBoot(Context context) {
		boolean is_first_boot = true;
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		is_first_boot = settings.getBoolean(PREFS_IS_FIRST_BOOT, true);
		return is_first_boot;
	}

	public static void setIsFirstBoot(Context context, boolean is_first_boot) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(PREFS_IS_FIRST_BOOT, is_first_boot);
		editor.commit();
	}

}
