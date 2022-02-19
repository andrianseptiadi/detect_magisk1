
import 'dart:async';

import 'package:flutter/services.dart';

class DetectMagisk1 {
  static const MethodChannel _channel = MethodChannel('detect_magisk1');

  static Future<bool>check() async {
    final bool result = (await _channel.invokeMethod<bool>('check'))!;
    return result;
  }
}
