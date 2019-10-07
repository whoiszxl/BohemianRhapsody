

import 'package:shared_preferences/shared_preferences.dart';

void addString(String key, String value) async {
  SharedPreferences sp = await SharedPreferences.getInstance();
  sp.setString(key, value);
}

Future getString(String key) async {
  SharedPreferences sp = await SharedPreferences.getInstance();
  return sp.getString(key);
}

void clearString(String key) async {
  SharedPreferences sp = await SharedPreferences.getInstance();
  sp.remove(key);
}