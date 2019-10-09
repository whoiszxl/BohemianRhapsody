

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
}

