package net.darkion.uniformedtransitions;

import android.app.Activity;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class Main implements IXposedHookLoadPackage, IXposedHookZygoteInit {

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {

    }


    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod(Activity.class,
                "overridePendingTransition", int.class, int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if (param != null) {
                            if (param.args != null) {
                                if ((int) param.args[0] == 0 && (int) param.args[1] == 0) {
                                    Log.i("UniformedTransitions", "Allow transition override this time only");
                                } else param.setResult(null);
                            }

                        }
                    }
                }


        );
    }


}
