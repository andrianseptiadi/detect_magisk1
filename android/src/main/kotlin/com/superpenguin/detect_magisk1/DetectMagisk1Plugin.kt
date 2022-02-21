package com.superpenguin.detect_magisk1

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.File

class DetectMagisk1Plugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "detect_magisk1")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "check") {
            result.success(check())
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}

fun check(): Boolean {
    val paths = arrayOf(
        "/data/data/com.topjohnwu.magisk",
        "/data/user/0/com.topjohnwu.magisk",
        "/data/user_de/0/com.topjohnwu.magisk",
        "/sbin/magisk",
        "/sbin/magiskhide",
        "/init.magisk.rc",
        "/dev/magisk/bin/busybox",
        "/system/addon.d/99-magisk.sh",
        "/data/user/0/magisk.db",
        "/data/user_de/0/magisk.db,",
        "/root/magisk",
        "/magisk",
        "/sbin_orig",
        "/data/magisk",
        "/sbin/.core"
    )

    for (p in paths) {
        if (File(p).exists()) {
            return true
        }
    }

    return false
}
