import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import '../service/service_method.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:fluttertoast/fluttertoast.dart';

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
            var bannerData = json.decode(snapshot.data['banner'].toString());
            List<Map> swiperDataList = (bannerData['data'] as List).cast(); //轮播图数据
            
            var navigatorData = json.decode(snapshot.data['navigator'].toString());
            List<Map> navigatorList = (navigatorData['data'] as List).cast(); //导航栏数据

            var adImageData = json.decode(snapshot.data['adBanner'].toString());
            String adImage = adImageData['data']; //广告图片

            return ListView(
              children: <Widget>[
                HomeSwiper(swiperDataList: swiperDataList),
                TopNavigator(navigatorList: navigatorList),
                AdBanner(adImage: adImage),
              ],
            );
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


//顶部导航8个icon
class TopNavigator extends StatelessWidget {

  final List navigatorList;
  const TopNavigator({Key key, this.navigatorList}) : super(key: key);

  Widget _gridItemUI(BuildContext context, item) {
    return InkWell(
      
      //导航选项点击事件
      onTap: () {
        Fluttertoast.showToast(
          msg: '点击了',
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.BOTTOM,
          timeInSecForIos: 1
        );
      },

      child: Column(
        children: <Widget>[
          Image.network(
            item['imageUrl'],
            width: ScreenUtil().setWidth(95),
          ),
          Text(item['navName'])
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    if(this.navigatorList.length > 10) {
      this.navigatorList.removeRange(10, this.navigatorList.length);
    }
    
    return Container(
      height: ScreenUtil().setHeight(330),
      padding: EdgeInsets.all(3.0),
      child: GridView.count(
        // 解决顶部导航区域（GridView）与全局（SingleChildScrollView）的滑动冲突问题
        physics: NeverScrollableScrollPhysics(),
        crossAxisCount: 5,
        padding: EdgeInsets.all(5.0),
        children: navigatorList.map((item) {
          return _gridItemUI(context, item);
        }).toList(),
      ),
    );
  }
}



// 广告区域
class AdBanner extends StatelessWidget {
  final String adImage;

  AdBanner({Key key, this.adImage}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Image.network(adImage),
    );
  }
}