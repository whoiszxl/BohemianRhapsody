import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_app/config/app_const.dart';
import 'package:flutter_app/utils/ToastUtils.dart';
import 'package:flutter_easyrefresh/easy_refresh.dart';
import 'package:flutter_easyrefresh/phoenix_header.dart';
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
        "imgUrl":
            "https://tvax1.sinaimg.cn/crop.0.0.512.512.180/a08698d0ly8g61dy1lk86j20e80e8mxm.jpg"
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
              this.assetHeaderData = assetHeaderData;
              return new EasyRefresh(
                header: PhoenixHeader(),
                onRefresh: () async {
                  setState(() {});
                },
                child: ListView(
                  children: <Widget>[
                    AssetHeader(
                      assetHeaderData: this.assetHeaderData,
                    ),
                    ButtonGroup(),
                    Container(
                      padding: EdgeInsets.only(top: 10),
                      height: ScreenUtil().setHeight(1200),
                      child: ListView.separated(
                        shrinkWrap: true, //解决无限高度问题
                        physics:
                            NeverScrollableScrollPhysics(), //禁用滑动事件 双层ListView嵌套会有滑动冲突
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
                ),
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
        width: ScreenUtil().setWidth(750),
        alignment: Alignment.topLeft,
        child: Column(
          children: <Widget>[
            _titleWidget(assetHeaderData['currency_name']),
            _assetWidget(assetHeaderData)
          ],
        ));
  }

  Widget _titleWidget(String currencyName) {
    return Container(
      padding: EdgeInsets.only(top: 5, left: 5),
      alignment: Alignment.bottomLeft,
      child: Text(
        currencyName,
        style: TextStyle(
          color: Colors.blue,
          fontSize: ScreenUtil().setSp(70),
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }

  Widget _assetWidget(assetHeaderData) {
    return Container(
      padding: EdgeInsets.only(top: 5, left: 5),
      child: Row(
        children: <Widget>[
          _assetDetail('可用', assetHeaderData['usable_balance'].toString(), 5),
          _assetDetail('冻结', assetHeaderData['lock_balance'].toString(), 60),
          _assetDetail('总金额', assetHeaderData['all_balance'].toString(), 100)
        ],
      ),
    );
  }

  Widget _assetDetail(String title, String value, double paddingLeft) {
    return Container(
      padding: EdgeInsets.only(left: paddingLeft),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[Text(title), Text(value)],
      ),
    );
  }
}

class ButtonGroup extends StatelessWidget {

  ButtonGroup({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return _buttonGroupWidget();
  }


    Widget _buttonGroupWidget() {
    return Container(
      margin: EdgeInsets.only(top: 15),
      child: Row(
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(left: 5),
            child: OutlineButton(
              child: Text('充币'),
              borderSide: BorderSide(color: AppConst.themeMainColor),
              onPressed: () {
                ToastUtils.showMessage("充币");
              },
            ),
          ),
          Container(
            margin: EdgeInsets.only(left: 5),
            child: OutlineButton(
              child: Text('提币'),
              borderSide: BorderSide(color: AppConst.themeMainColor),
              onPressed: () {
                ToastUtils.showMessage("提币");
              },
            ),
          ),
          Container(
            margin: EdgeInsets.only(left: 5),
            child: OutlineButton(
              child: Text('划转'),
              borderSide: BorderSide(color: AppConst.themeMainColor),
              onPressed: () {
                ToastUtils.showMessage("划转");
              },
            ),
          ),
          Container(
            margin: EdgeInsets.only(left: 5),
            child: OutlineButton(
              child: Text('币币交易'),
              borderSide: BorderSide(color: AppConst.themeMainColor),
              onPressed: () {
                ToastUtils.showMessage("币币交易");
              },
            ),
          ),
        ],
      ),
    );
  }
}