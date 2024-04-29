
import 'auto_start_platform_interface.dart';

class AutoStart {
  Future<String?> getPlatformVersion() {
    return AutoStartPlatform.instance.getPlatformVersion();
  }

  toAutoStartSetting() {
    return AutoStartPlatform.instance.toAutoStartSetting();
  }

  Future<bool?> get isAutoStartAvailable async {
    return AutoStartPlatform.instance.isAutoStartAvailable;
  }
}
