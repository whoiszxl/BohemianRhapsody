import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_app/config/error_code.dart';
import 'package:flutter_app/pages/index_page.dart';
import 'package:flutter_app/pages/login_page.dart';
import 'package:flutter_app/pages/member/asset_detail_page.dart';
import 'package:flutter_app/service/service_method.dart';
import 'package:flutter_app/utils/SpUtils.dart';
import 'package:flutter_app/utils/ToastUtils.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class MemberPage extends StatefulWidget {

  @override
  State<StatefulWidget> createState() => _MemberPageState();
}


class _MemberPageState extends State<MemberPage> {

  List list = [];
  
  @override
  void initState() {
    
    super.initState();
  }

  _MemberPageState() {
    _getAssetData();

    _getUserInfo();

  }

  void _getUserInfo() {
    authRequest('userInfo').then((val) {
      var userInfo = json.decode(val);
      if(userInfo['code'] == userErrorCode['JWT_TOKEN_AUTH_FAIL']) {
        ToastUtils.showMessage(userInfo['message']);
        SpUtils.clearString('userInfo');
      }
      setState(() {
        
      });
    });
  }

  List _getAssetData() {
    authRequest('assetList').then((val) {
      var data = json.decode(val.toString());
      if(data['code'] == 0) {     
        setState(() {
          list = data['data'];
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("个人中心"),
        actions: <Widget>[
          Offstage(
            offstage: false,
            child: Container(
              padding: EdgeInsets.only(right: 10),
              child: GestureDetector(
                onTap: () => {
                  showDialog(
                    context: context,
                    barrierDismissible: false,
                    builder: (BuildContext context) {
                      return AlertDialog(
                        title: Text('提示'),
                        content: Text('是否退出登录'),
                        actions: <Widget>[
                          FlatButton(
                            child: Text('取消'),
                            onPressed: () {
                              Navigator.pop(context);
                            },
                          ),
                          FlatButton(
                            child: Text('退出'),
                            onPressed: () {
                              logout();
                              //Navigator.pop(context);
                              Navigator.pushReplacement(context, new MaterialPageRoute(builder: (context) => new IndexPage()));
                            },
                          )

                        ],
                      );
                    }
                  )
                },
                child: Icon(Icons.exit_to_app),
              ),
            ),
          )
        ],
      ),
      body: FutureBuilder(
        future: getMemberData(),
        builder: (context, snapshot) {
          var username = '点击登录';
          bool isLogin = false;
          if(snapshot.hasData) {
            var userInfo = json.decode(snapshot.data['userInfo'].toString());
            if(null != userInfo) {
              username = userInfo['username'];
              isLogin = true;
            }
          }

          return ListView(
            children: <Widget>[
              _topHeader(username, isLogin),
              _myAsset(),
              _personalMenu(),
              ListView.builder(
                itemCount: list.length,
                shrinkWrap: true, //解决无限高度问题
                physics: NeverScrollableScrollPhysics(),//禁用滑动事件 双层ListView嵌套会有滑动冲突
                itemBuilder: (context, index) {
                  return _assetListWight(index);
                },
              )
            ],
          );
        },
      )
    );
  }






  //个人中心头部
  Widget _topHeader(String username, bool isLogin) {

    return Container(
      width: ScreenUtil().setWidth(750),
      padding: EdgeInsets.all(20),
      color: Colors.deepOrange[300],
      child: Column(
        children: <Widget>[
          Container(
            height: ScreenUtil().setHeight(160),
            
            margin: EdgeInsets.only(top: 30),
            child: ClipOval(
              child: Image.network('https://tvax1.sinaimg.cn/crop.0.0.512.512.180/a08698d0ly8g61dy1lk86j20e80e8mxm.jpg'), //TODO 接口获取
            ),
          ),
          Container(
            margin: EdgeInsets.only(top: 10),
            child: isLogin ? 
            Text(username,style: TextStyle(fontSize: ScreenUtil().setSp(36), color: Colors.white)):
            new GestureDetector(
              onTap: () {
                Navigator.push(context, new MaterialPageRoute(builder: (context) => new LoginPage()));
              },
              child: Text("点击登录",style: TextStyle(fontSize: ScreenUtil().setSp(36), color: Colors.white)),
            ),
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


  //TODO 资产组件，先用listile代替
  Widget _assetListWight(int index) {
    return ListTile(
      title:  Text(list[index]['currency_name']), // item 标题
      leading: Icon(Icons.attach_money), // item 前置图标
      subtitle: Text(
        "可用:" + list[index]['usable_balance'].toString() + "\n"
        "冻结:" + list[index]['lock_balance'].toString()), // item 副标题
      trailing: Icon(Icons.keyboard_arrow_right),// item 后置图标
      isThreeLine:true,  // item 是否三行显示
      dense:true,                // item 直观感受是整体大小
      contentPadding: EdgeInsets.all(2.0),// item 内容内边距
      enabled:true,
      onTap:(){
        Navigator.push(context, new MaterialPageRoute(builder: (context) => new AssetDetailPage(list[index]['id'].toString())));
      },// item onTap 点击事件
      //onLongPress:(){print('长按:$index');},// item onLongPress 长按事件
      selected:false,     // item 是否选中状态R
    );
  } 
}

