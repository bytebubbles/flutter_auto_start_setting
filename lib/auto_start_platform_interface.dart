import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'auto_start_method_channel.dart';

abstract class AutoStartPlatform extends PlatformInterface {
  /// Constructs a AutoStartPlatform.
  AutoStartPlatform() : super(token: _token);

  static final Object _token = Object();

  static AutoStartPlatform _instance = MethodChannelAutoStart();

  /// The default instance of [AutoStartPlatform] to use.
  ///
  /// Defaults to [MethodChannelAutoStart].
  static AutoStartPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AutoStartPlatform] when
  /// they register themselves.
  static set instance(AutoStartPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<void> toAutoStartSetting() {
    throw UnimplementedError('toAutoStartSetting() has not been implemented.');
  }

  Future<bool> get isAutoStartAvailable async {
    throw UnimplementedError('isAutoStartAvailable() has not been implemented.');
  }
}
