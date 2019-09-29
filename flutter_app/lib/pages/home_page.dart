import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import '../service/service_method.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class HomePage extends StatefulWidget {
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String homePageContent='正在获取数据';


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('波西米亚狂想曲'),),
      body: FutureBuilder(
        future: getHomePageContent(),
        builder: (context, snapshot) {
          if(snapshot.hasData) {
            var data = json.decode(snapshot.data.toString());
            print(data);
            List<Map> swiperDataList = (data['data'] as List).cast();
            return Column(children: <Widget>[
              HomeSwiper(swiperDataList: swiperDataList,)
            ],);
          }else {
            return Center(
              child: Text('加载中'),
            );
          }
        },
      )
      
    
    );
  }
}





//主页轮播图组件
class HomeSwiper extends StatelessWidget {

  final List swiperDataList;
  HomeSwiper({Key key,this.swiperDataList}):super(key:key);

  @override
  Widget build(BuildContext context) {
    // 返回一个容器包裹swiper
    return Container(
      height: ScreenUtil().setHeight(333),
      width: ScreenUtil().setWidth(750),
      child: Swiper(
        itemBuilder: (BuildContext context, int index) {
          return Image.network("${swiperDataList[index]['pic']}", fit: BoxFit.fill,);
        },
        itemCount: swiperDataList.length,
        pagination: new SwiperPagination(),
        autoplay: true,
      ),
    );
  }
}