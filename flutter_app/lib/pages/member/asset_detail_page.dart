import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class AssetDetailPage extends StatefulWidget {

  final String currencyName;
  AssetDetailPage(this.currencyName) : super();

  _AssetDetailPageState createState() => _AssetDetailPageState(currencyName);
}

class _AssetDetailPageState extends State<AssetDetailPage> {

  String currencyName;

  List data = new List();

  _AssetDetailPageState(currencyName) {
    this.currencyName = currencyName;
    for (var i = 0; i < 5; i++) {
      data.add({
        "title": "我是标题$i",
        "subTitle": "我是副标题$i",
        "imgUrl": "https://tvax1.sinaimg.cn/crop.0.0.512.512.180/a08698d0ly8g61dy1lk86j20e80e8mxm.jpg"
      });
    }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(currencyName + " Asset"),
      ),
      body: ListView(
        children: <Widget>[
          Container(
            height: ScreenUtil().setHeight(300),
            width: ScreenUtil().setWidth(750),
            alignment: Alignment.center,
            color: Colors.yellow,
            child: Text('TITLE'),
          ),

          Container(
            height: ScreenUtil().setHeight(1200),
            child: ListView.separated(
              shrinkWrap: true, //解决无限高度问题
              physics: NeverScrollableScrollPhysics(),//禁用滑动事件 双层ListView嵌套会有滑动冲突
              itemCount: this.data.length,
              itemBuilder: (context, index) {
                var _data = this.data[index];
                return ListTile(
                  leading: Image.network(_data['imgUrl']),
                  title: Text(_data["title"]),
                  subtitle: Text(_data["subTitle"]),
                  trailing: Icon(Icons.chevron_right)
                );
              },
              separatorBuilder: (context, index) {
                return Divider();
              },
              
              ),
          )
        ],
      )
    );
  }

}