import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_app/service/service_method.dart';

class AssetDetailPage extends StatefulWidget {
  final String currencyId;
  AssetDetailPage(this.currencyId) : super();

  _AssetDetailPageState createState() => _AssetDetailPageState(currencyId);
}

class _AssetDetailPageState extends State<AssetDetailPage> {
  String currencyId;
  List data = new List();

  var assetHeaderData;

  _AssetDetailPageState(currencyId) {
    this.currencyId = currencyId;
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
          title: Text("Asset"),
        ),
        body: FutureBuilder(
          future: getAssetDetailData(currencyId),
          builder: (context, snapshot) {
            var assetHeaderData;
            if (snapshot.hasData) {
              assetHeaderData = json.decode(snapshot.data.toString())['data'];
              return ListView(
                children: <Widget>[
                  AssetHeader(
                    assetHeaderData: assetHeaderData,
                  ),
                  Container(
                    height: ScreenUtil().setHeight(1200),
                    child: ListView.separated(
                      shrinkWrap: true, //解决无限高度问题
                      physics: NeverScrollableScrollPhysics(), //禁用滑动事件 双层ListView嵌套会有滑动冲突
                      itemCount: this.data.length,
                      itemBuilder: (context, index) {
                        var _data = this.data[index];
                        return ListTile(
                            leading: Image.network(_data['imgUrl']),
                            title: Text(_data["title"]),
                            subtitle: Text(_data["subTitle"]),
                            trailing: Icon(Icons.chevron_right));
                      },
                      separatorBuilder: (context, index) {
                        return Divider();
                      },
                    ),
                  )
                ],
              );
            } else {
              return Container(
                child: Text('loading'),
              );
            }
          },
        ));
  }
}

//资产详情头部组件
class AssetHeader extends StatelessWidget {
  var assetHeaderData;
  AssetHeader({Key key, this.assetHeaderData}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: ScreenUtil().setHeight(300),
      width: ScreenUtil().setWidth(750),
      alignment: Alignment.center,
      color: Colors.yellow,
      child: Text(assetHeaderData['currency_name']),
    );
  }
}
