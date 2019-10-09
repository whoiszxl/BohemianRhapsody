

import 'package:shared_preferences/shared_preferences.dart';

class SpUtils {
  static SharedPreferences preferences;
  static Future<bool> getInstance() async{
    preferences = await SharedPreferences.getInstance();
    return true;
  }

  static void addString(String key, String value) {
    preferences.setString(key, value);
  }

  static String getString(String key) {
    return preferences.getString(key);
  }

  static void clearString(String key) {
    preferences.remove(key);
  }

  static void addStringAsync(String key, String value) async {
    var sp = await SharedPreferences.getInstance();
    sp.setString(key, value);
  }

  static Future getStringAsync(String key) async {
    var sp = await SharedPreferences.getInstance();
    return sp.getString(key);
  }

  static void clearStringAsync(String key) async {
    var sp = await SharedPreferences.getInstance();
    sp.remove(key);
  }
}

