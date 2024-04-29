import 'package:flutter_test/flutter_test.dart';
import 'package:auto_start/auto_start.dart';
import 'package:auto_start/auto_start_platform_interface.dart';
import 'package:auto_start/auto_start_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAutoStartPlatform
    with MockPlatformInterfaceMixin
    implements AutoStartPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final AutoStartPlatform initialPlatform = AutoStartPlatform.instance;

  test('$MethodChannelAutoStart is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAutoStart>());
  });

  test('getPlatformVersion', () async {
    AutoStart autoStartPlugin = AutoStart();
    MockAutoStartPlatform fakePlatform = MockAutoStartPlatform();
    AutoStartPlatform.instance = fakePlatform;

    expect(await autoStartPlugin.getPlatformVersion(), '42');
  });
}
