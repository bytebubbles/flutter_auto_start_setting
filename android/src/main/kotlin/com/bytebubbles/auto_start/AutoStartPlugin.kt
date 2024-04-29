package com.bytebubbles.auto_start

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** AutoStartPlugin */
class AutoStartPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context: Context

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    this.context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "auto_start")
    channel.setMethodCallHandler(this)

  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method) {
        "getPlatformVersion" -> {
          result.success("Android ${android.os.Build.VERSION.RELEASE}")
        }
        "toAutoStartSetting" -> {
          //addAutoStartup()
          AutoStartHelper.getInstance().getAutoStartPermission(context, newTask = true)
        }
        "isAutoStartPermission" -> {
          result.success(AutoStartHelper.getInstance().isAutoStartPermissionAvailable(context));
        }
        else -> {
          result.notImplemented()
        }
    }

  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun addAutoStartup() {
    try {
      val intent = Intent()
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      val manufacturer = Build.MANUFACTURER
      if ("xiaomi".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.miui.securitycenter",
          "com.miui.permcenter.autostart.AutoStartManagementActivity"
        )
      } else if ("oppo".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.coloros.safecenter",
          "com.coloros.safecenter.permission.startup.StartupAppListActivity"
        )
      } else if ("vivo".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.vivo.permissionmanager",
          "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"
        )
      } else if ("Letv".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.letv.android.letvsafe",
          "com.letv.android.letvsafe.AutobootManageActivity"
        )
      } else if ("Honor".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.huawei.systemmanager",
          "com.huawei.systemmanager.optimize.process.ProtectActivity"
        )
      } else if ("samsung".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.samsung.android.lool",
          "com.samsung.android.sm.battery.ui.BatteryActivity"
        )
      } else if ("oneplus".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.oneplus.security",
          "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"
        )
      } else if ("nokia".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.evenwell.powersaving.g3",
          "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity"
        )
      } else if ("asus".equals(manufacturer, ignoreCase = true)) {
        intent.component = ComponentName(
          "com.asus.mobilemanager",
          "com.asus.mobilemanager.autostart.AutoStartActivy"
        )
      } else if ("realme".equals(manufacturer, ignoreCase = true)) {
        intent.action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
      }
      val list: List<ResolveInfo> =
        context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
      if (list.size > 0) {
        context.startActivity(intent)
      }
    } catch (e: Exception) {
      Log.e("exc", e.toString())
    }
  }
}
