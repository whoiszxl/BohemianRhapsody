import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter_app/utils/HttpUtils.dart';

import 'package:shared_preferences/shared_preferences.dart';

import 'dart:async';
import 'dart:io';
import '../config/api.dart';

Future request(url, {formData}) async {
  try {
    Response response;
    Dio dio = new Dio();
    dio.options.responseType = ResponseType.plain;
    if (formData == null) {
      response = await dio.post(servicePath[url]);
    } else {
      response = await dio.post(servicePath[url], data: formData);
    }
    if (response.statusCode == 200) {
      return response.data;
    } else {
      throw Exception('后端接口出现异常。');
    }
  } catch (e) {
    return print('ERROR:===========>$e');
  }
}

Future authRequest(url, {formData}) async {
  try {
    Response response;
    Dio dio = new Dio();
    dio.options.responseType = ResponseType.plain;

    //配置Bearer令牌
    Map<String, String> headers = new Map();
    SharedPreferences sp = await SharedPreferences.getInstance();
    String userInfo = sp.getString("userInfo");
    if(null != userInfo) {
      var userInfoObj = json.decode(userInfo);
      String token = userInfoObj['token'];
      headers['Authorization'] = 'Bearer ' + token;
    }
    dio.options.headers = headers;

    if (formData == null) {
      response = await dio.post(servicePath[url]);
    } else {
      response = await dio.post(servicePath[url], data: formData);
    }
    if (response.statusCode == 200) {
      return response.data;
    } else {
      throw Exception('后端接口出现异常。');
    }
  } catch (e) {
    return print('ERROR:===========>$e');
  }
}

Future requestJson(url, {formData}) async {
  try {
    print('开始获取数据..............');
    Response response;
    Dio dio = new Dio();
    dio.options.contentType = ContentType.parse('application/json');
    if (formData == null) {
      response = await dio.post(servicePath[url]);
    } else {
      response = await dio.post(servicePath[url], data: formData);
    }
    if (response.statusCode == 200) {
      return response.data;
    } else {
      throw Exception('后端接口出现异常。');
    }
  } catch (e) {
    return print('ERROR:===========>$e');
  }
}

//TODO 两个async嵌套调用不知道为什么FutureBuilder拿不到数据 ，GG
Future getAssetDetailData(String currencyId) async {
  //初始化资产详情头部的数据
  // Map params = new Map();
  // params['currencyId'] = currencyId;
  // authRequest('getAssetByCurrencyId', formData: params).then((val) {
  //   var data = json.decode(val.toString());
  //   return data['data'];
  // });

  //print('开始获取首页数据..............' + servicePath['commonBanner']);
  Dio dio = new Dio();
  dio.options.contentType = ContentType.parse('application/json');
  dio.options.responseType = ResponseType.plain; 
  //配置Bearer令牌
  Map<String, String> headers = new Map();
  SharedPreferences sp = await SharedPreferences.getInstance();
  String userInfo = sp.getString("userInfo");
  if(null != userInfo) {
    var userInfoObj = json.decode(userInfo);
    String token = userInfoObj['token'];
    headers['Authorization'] = 'Bearer ' + token;
  }
  dio.options.headers = headers; 

  Map data = new Map();
  data['currencyId'] = currencyId;
  Response assetResponse = await dio.post(servicePath['getAssetByCurrencyId'], data: data);

  if (assetResponse.statusCode == 200) {
    return assetResponse.data;
  } else {
    throw Exception('banner后端接口出现异常。');
  }

}

Future getMemberData() async {
  SharedPreferences sp = await SharedPreferences.getInstance();
  String userInfo = sp.getString("userInfo");
  Map result = new Map();
  if(userInfo != null) {
    result['userInfo'] = userInfo;
  }
  return result;
}

Future logout() async {
  SharedPreferences sp = await SharedPreferences.getInstance();
  sp.remove('userInfo');
}


// 获取首页主题内容
Future getHomePageContent() async {
  try {
    //print('开始获取首页数据..............' + servicePath['commonBanner']);
    Dio dio = new Dio();
    dio.options.contentType = ContentType.parse('application/x-www-form-urlencoded');  
    dio.options.responseType = ResponseType.plain;  
    Response bannerResponse = await dio.get(servicePath['commonBanner']);
    Response navigatorResponse = await dio.post(servicePath['commonNavigator']);
    Response adBannerResponse = await dio.post(servicePath['getAdBanner']);

    Map result = new Map();

    if (bannerResponse.statusCode == 200) {
      result['banner'] = bannerResponse.data;
    } else {
      throw Exception('banner后端接口出现异常。');
    }

    if (navigatorResponse.statusCode == 200) {
      result['navigator'] = navigatorResponse.data;
    } else {
      throw Exception('navigator后端接口出现异常。');
    }

    if (adBannerResponse.statusCode == 200) {
      result['adBanner'] = adBannerResponse.data;
    } else {
      throw Exception('navigator后端接口出现异常。');
    }

    return result;
  } catch (e) {
    return print('ERROR:===========>$e');
  }
}