import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'auto_start_platform_interface.dart';

/// An implementation of [AutoStartPlatform] that uses method channels.
class MethodChannelAutoStart extends AutoStartPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('auto_start');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> toAutoStartSetting() async {
    try {
      await methodChannel.invokeMethod("toAutoStartSetting");
    } catch (e) {
      if (kDebugMode) {
        print(e);
      }
    }
  }

  @override
  Future<bool?> get isAutoStartAvailable async {
    final bool? isAutoStartAvailable =
    await methodChannel.invokeMethod('isAutoStartPermission');
    return isAutoStartAvailable;
  }
}
