import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class MemberPage extends StatelessWidget {
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("个人中心"),
      ),
      body: ListView(
        children: <Widget>[
          _topHeader()
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
            height: ScreenUtil().setHeight(200),
            
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


}

