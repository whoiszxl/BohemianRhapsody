import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_app/config/app_const.dart';
import 'package:flutter_app/service/service_method.dart';
import 'package:flutter_app/utils/ToastUtils.dart';
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
            return QrImage(
              data: addressData['data']['rechargeAddress'],
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