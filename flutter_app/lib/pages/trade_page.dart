import 'package:flutter/material.dart';


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

  @override
  Widget build(BuildContext context) {
    return Container(
      
    );
  }

}


class NewestDealPanel extends StatelessWidget {

  NewestDealPanel({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      
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


