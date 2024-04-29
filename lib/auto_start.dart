
import 'auto_start_platform_interface.dart';

class AutoStart {
  static Future<String?> getPlatformVersion() {
    return AutoStartPlatform.instance.getPlatformVersion();
  }

  static toAutoStartSetting() {
    return AutoStartPlatform.instance.toAutoStartSetting();
  }

  static Future<bool> get isAutoStartAvailable async {
    return AutoStartPlatform.instance.isAutoStartAvailable;
  }
}
