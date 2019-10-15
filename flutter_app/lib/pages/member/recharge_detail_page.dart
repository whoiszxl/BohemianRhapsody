import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_app/config/app_const.dart';
import 'package:flutter_app/service/service_method.dart';
import 'package:flutter_app/utils/ToastUtils.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:qr_flutter/qr_flutter.dart';

class RechargeDetailPage extends StatefulWidget {
  final String currencyId;
  RechargeDetailPage(this.currencyId) : super();

  _RechargeDetailPageState createState() => _RechargeDetailPageState(currencyId);
}

class _RechargeDetailPageState extends State<RechargeDetailPage> {
  
  String currencyId;

  _RechargeDetailPageState(String currencyId) {
    this.currencyId = currencyId;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.arrow_back_ios),
        actions: <Widget>[
          Container(
            margin: EdgeInsets.only(right: 10),
            child: Icon(Icons.book),
          )
        ],
      ),
      body: FutureBuilder(
        future: UserService.dispenseOrGetAddress(currencyId),
        builder: (context, snapshot) {
          if(snapshot.hasData) {
            print("获取到的用户地址" + snapshot.data.toString());
            var addressData = json.decode(snapshot.data);
            if(addressData['code'] != AppConst.successCode) {
              ToastUtils.showMessage(addressData['message']);
            }
            String rechargeAddress = addressData['data']['rechargeAddress'];
            return Column(
              children: <Widget>[
                addressHeader(currencyId: currencyId),
                addressDetail(rechargeAddress: rechargeAddress)
              ],
            );
          }else {
            return Container(
              alignment: Alignment.center,
              child: Text('加载中'),
            );
          }
        },
      ),
    );
  }
}




class addressHeader extends StatelessWidget {

  String currencyId;

  addressHeader({Key key, this.currencyId}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Container(
          margin: EdgeInsets.only(left: 10),
          alignment: Alignment.centerLeft,
          child: Text('充币', textAlign: TextAlign.left, style: TextStyle(fontSize: ScreenUtil().setSp(64), fontWeight: FontWeight.bold))),
        Container(
          margin: EdgeInsets.only(right: 10, left: 10, top: 15),
          padding: EdgeInsets.only(top: 10, bottom: 10),
          color: AppConst.themeGreyColor,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Container(child: Text('BTC', style: TextStyle(fontWeight: FontWeight.bold),), margin: EdgeInsets.only(left: 8),),
              Container(child: Text('选择币种 >'), margin: EdgeInsets.only(right: 8)),
            ],
          ),
        ),

      ],
    );
  }
}


class addressDetail extends StatelessWidget {

  String rechargeAddress;

  addressDetail({Key key, this.rechargeAddress}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(right: 10, left: 10, top: 10),
      padding: EdgeInsets.only(top: 25, bottom: 15),
      width: ScreenUtil().setWidth(750),
      color: AppConst.themeGreyColor,
      child: Column(children: <Widget>[
        _qrcodeWidget(rechargeAddress),
        Container(child: OutlineButton(
          borderSide: new BorderSide(color: AppConst.themeMainColor),
          child: new Text('保存二维码', style: TextStyle(color: AppConst.themeMainColor)),
          onPressed: () {
            
          },
        ),),
        Text('充币地址', textAlign: TextAlign.center),
        Text(rechargeAddress, textAlign: TextAlign.center),
        Container(child: OutlineButton(
          borderSide: new BorderSide(color: AppConst.themeMainColor),
          child: new Text('复制地址', style: TextStyle(color: AppConst.themeMainColor)),
          onPressed: () {
            
          },
        ),),
      ],),
    );
  }


  Widget _qrcodeWidget(String rechargeAddress) {
    return QrImage(
      data: rechargeAddress,
      version: QrVersions.auto,
      size: 180,
      gapless: false,
      errorStateBuilder: (context, err) {
        return Container(
          child: Text(
            "Uh oh! Something went wrong...",
            textAlign: TextAlign.center,
          ),
        );
      },
    );
  }
}