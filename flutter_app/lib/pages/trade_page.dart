import 'package:flutter/material.dart';
import 'package:flutter_app/config/app_const.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';


class TradePage extends StatefulWidget {

  @override
  State<StatefulWidget> createState() => _TradePageState();
}


class _TradePageState extends State<TradePage> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.menu),
        title: Text('币币交易'),
        actions: <Widget>[
          Icon(Icons.search)
        ],
      ),

      body: ListView(
        children: <Widget>[
          HeaderTitle(),

          Row(
            children: <Widget>[
              TransactionPanel(),
              NewestDealPanel(),
            ],
          )
        ],
      ),
    );
  }
}


class HeaderTitle extends StatelessWidget {

  HeaderTitle({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.cyanAccent,
      padding: EdgeInsets.only(top: 5, bottom: 5),
      child: Row(
        children: <Widget>[
          Icon(Icons.attach_money),
          Text("BTC/USDT  "),
          Text("-2.23%"),
        ],
      ),
    );
  }

}



class TransactionPanel extends StatelessWidget {

  TransactionPanel({Key key}) : super(key: key);

  TextEditingController transactionCountController = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Container(
      width: ScreenUtil().setWidth(430),
      height: ScreenUtil().setHeight(600),
      margin: EdgeInsets.only(left: 10, top: 10, right: 10),
      alignment: Alignment.topLeft,
      child: Column(
        children: <Widget>[
          Row(
            children: <Widget>[
              Container(
                width: ScreenUtil().setWidth(200),
                child: FlatButton(
                  child: Text('买入'),
                  color: Colors.green,
                  textColor: Colors.white,
                  onPressed: () {},
                ),
              ),
              Container(
                margin: EdgeInsets.only(left: 10),
                width: ScreenUtil().setWidth(200),
                child: FlatButton(
                  child: Text('卖出'),
                  color: Colors.grey,
                  textColor: Colors.white,
                  onPressed: () {},
                ),
              ),
            ],
          ),
          _transactionPriceInput(),
          _transactionCountInput(),
          _availBalance(),
          _buyOrSellButton()
        ],
      ),
    );
  }

  Widget _availBalance() {
    return Container(
      alignment: Alignment.centerLeft,
      margin: EdgeInsets.only(top: 5),
      child: Text('可用 19.12456 BTC', style: TextStyle(color: Colors.grey), textAlign: TextAlign.left),
    );
  }

  Widget _buyOrSellButton() {
    return Container(
      width: ScreenUtil().setWidth(430),
      child: FlatButton(
        child: Text('买入'),
        color: Colors.green,
        textColor: Colors.white,
        onPressed: () {},
      ),
    );
  }

  Widget _transactionPriceInput() {
    return Container(
      child: TextField(
        controller: transactionCountController,
        keyboardType: TextInputType.number,
        autofocus: false,
        decoration: InputDecoration(
            contentPadding: EdgeInsets.fromLTRB(10.0, 2.0, 10.0, 10.0),
            hintText: '委托价',
            border: OutlineInputBorder()
        )
      ),
    );
  }

  Widget _transactionCountInput() {
    return Container(
      margin: EdgeInsets.only(top: 10),
      child: TextField(
        controller: transactionCountController,
        keyboardType: TextInputType.number,
        autofocus: false,
        decoration: InputDecoration(
            contentPadding: EdgeInsets.fromLTRB(10.0, 2.0, 10.0, 10.0),
            hintText: '数量',
            border: OutlineInputBorder()
        )
      ),
    );
  }

}


class NewestDealPanel extends StatelessWidget {

  NewestDealPanel({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: ScreenUtil().setWidth(280),
      height: ScreenUtil().setHeight(600),
      color: Colors.yellowAccent,
      child: Text('NewestDealPanel'),
    );
  }

}



class CurrentTransactionPanel extends StatelessWidget {

  CurrentTransactionPanel({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      
    );
  }

}


