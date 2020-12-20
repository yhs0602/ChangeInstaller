package com.kyhsgeekcode.changeinstaller;

import android.util.*;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage.*;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class ChangeInstaller implements IXposedHookLoadPackage
{
	private String TAG="ChangeInstaller";
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable
	{
		/*if (!lpparam.packageName.equals("com.android.bluetooth"))
		{
			Log.i(TAG, "Not: " + lpparam.packageName);
			return;
		}*/
		Log.d(TAG, "Yes " + lpparam.packageName);	

		findAndHookMethod("android.content.pm.PackageManager", lpparam.classLoader, "getInstallerPackageName", String.class, new XC_MethodHook() {
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable
				{
					Log.v(TAG, "HOOK called:"+param.args[0]);
					param.setResult((Object)"com.android.vending");
					return;
				}
			});
		return;
	}
}
