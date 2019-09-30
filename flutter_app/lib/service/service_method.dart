import 'package:dio/dio.dart';
import 'dart:async';
import 'dart:io';
import '../config/api.dart';

Future request(url, {formData}) async {
  try {
    print('开始获取数据..............');
    Response response;
    Dio dio = new Dio();
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


// 获取首页主题内容
Future getHomePageContent() async {
  try {
    //print('开始获取首页数据..............' + servicePath['commonBanner']);
    Dio dio = new Dio();
    dio.options.contentType = ContentType.parse('application/x-www-form-urlencoded');  
    dio.options.responseType = ResponseType.plain;  
    Response bannerResponse = await dio.get(servicePath['commonBanner']);
    Response navigatorResponse = await dio.post(servicePath['commonNavigator']);

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

    return result;

  } catch (e) {
    return print('ERROR:===========>$e');
  }
}