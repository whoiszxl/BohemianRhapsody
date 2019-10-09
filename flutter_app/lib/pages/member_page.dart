import 'package:flutter/material.dart';
import 'package:flutter_app/service/service_method.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_app/utils/SpUtils.dart';

class MemberPage extends StatefulWidget {

  @override
  State<StatefulWidget> createState() => _MemberPageState();
}


class _MemberPageState extends State<MemberPage> {

  List list = [];

  @override
  void initState() {
    //getAssetData();
    super.initState();
  }

  List getAssetData() {
    request('assetList').then((val) {

    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("个人中心"),
      ),
      body: ListView(
        children: <Widget>[
          _topHeader(),
          _myAsset(),
          _personalMenu(),
          _assetListWight(1)
        ],
      ),
    );
  }






  //个人中心头部
  Widget _topHeader() {

    return Container(
      width: ScreenUtil().setWidth(750),
      padding: EdgeInsets.all(20),
      color: Colors.pinkAccent,
      child: Column(
        children: <Widget>[
          Container(
            height: ScreenUtil().setHeight(160),
            
            margin: EdgeInsets.only(top: 30),
            child: ClipOval(
              child: Image.network('https://tvax1.sinaimg.cn/crop.0.0.828.828.180/8d72bb9fly8g7hcftv97uj20n00n00u2.jpg'), //TODO 接口获取
            ),
          ),
          Container(
            margin: EdgeInsets.only(top: 10),
            child: Text("whoiszxl",style: TextStyle(fontSize: ScreenUtil().setSp(36), color: Colors.white)),
          )
        ],
      ),
    );
  }


  Widget _myAsset() {
    return Container(
      margin: EdgeInsets.only(top: 3),
      decoration: BoxDecoration(
        color: Colors.white,
        border: Border(
          bottom: BorderSide(width: 1, color: Colors.black12)
        )
      ),
      child: ListTile(
        title: Text('币币总资产折合(BTC)', style: TextStyle(fontSize: ScreenUtil().setSp(20), color: Colors.black54)),
        subtitle: Text('0.1345775≈16778.54 CNY', style: TextStyle(fontSize: ScreenUtil().setSp(30), color: Colors.black87)),
        trailing: Icon(Icons.account_balance_wallet),

      ),
    );
  }

  
  Widget _personalMenu() {

    return Container(
      margin: EdgeInsets.only(top: 5),
      width: ScreenUtil().setWidth(750),
      height: ScreenUtil().setHeight(180),
      padding: EdgeInsets.only(top: 20),
      color: Colors.white,
      child: Row(
        children: <Widget>[
          Container(
            width: ScreenUtil().setWidth(150),
            child: Column(
              children: <Widget>[
                Icon(
                  Icons.attach_money,
                  size: 30,
                ),
                Text('充币')
              ],
            ),
          ),

          Container(
            width: ScreenUtil().setWidth(150),
            child: Column(
              children: <Widget>[
                Icon(
                  Icons.query_builder,
                  size: 30,
                ),
                Text('提币')
              ],
            ),
          ),

          Container(
            width: ScreenUtil().setWidth(150),
            child: Column(
              children: <Widget>[
                Icon(
                  Icons.transfer_within_a_station,
                  size: 30,
                ),
                Text('划转')
              ],
            ),
          ),

          Container(
            width: ScreenUtil().setWidth(150),
            child: Column(
              children: <Widget>[
                Icon(
                  Icons.control_point_duplicate,
                  size: 30,
                ),
                Text('合约')
              ],
            ),
          ),

          Container(
            width: ScreenUtil().setWidth(150),
            child: Column(
              children: <Widget>[
                Icon(
                  Icons.content_paste,
                  size: 30,
                ),
                Text('法币')
              ],
            ),
          ),
        ],
      ),
    );
  }


  Widget _assetListCurrencyName(index) {
    return Container(
      width: ScreenUtil().setWidth(750),
      child: Column(
        children: <Widget>[
        Text('BTC', style: TextStyle(), textAlign: TextAlign.left,),
      ],),
    );
  }


  Widget _assetListWight(int index) {
    return InkWell(
      onTap: () {},
      child: Container(
        width: ScreenUtil().setWidth(750),
        padding: EdgeInsets.only(top: 5.0,bottom: 5.0),
        decoration: BoxDecoration(
          color: Colors.white,
          border: Border(
            bottom: BorderSide(width: 1.0,color: Colors.black12)
          )
        ),
        child: Row(
          children: <Widget>[
            _assetListCurrencyName(1)
          ],
        ),
      ),
    );
  } 
}

