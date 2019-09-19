import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';

import './home_page.dart';
import './asset_page.dart';
import './market_page.dart';
import './trade_page.dart';
import './member_page.dart';

class IndexPage extends StatefulWidget {
  @override
  _IndexPageState createState() => _IndexPageState();
}

class _IndexPageState extends State<IndexPage> {

  final List<BottomNavigationBarItem> bottomTabs = [
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.home), title: Text('首页')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.search), title: Text('行情')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.shopping_cart), title: Text('交易')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.book), title: Text('资产')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.profile_circled), title: Text('个人'))
  ];

  final List tabBodies = [
    HomePage(),
    MarketPage(),
    TradePage(),
    AssetPage(),
    MemberPage()
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color.fromRGBO(244, 245, 245, 1.0),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        currentIndex: currentIndex,
        items: bottomTabs,
        onTap: (index) {
          setState(() {
           currentIndex = index;
           currentPage = tabBodies[currentIndex]; 
          });
        },
      ),
      body: currentPage,
    );
  }


  int currentIndex = 0;
  var currentPage;

  @override
  void initState() {
    // 进行初始化操作
    currentPage = tabBodies[currentIndex];
    super.initState();
  }
}