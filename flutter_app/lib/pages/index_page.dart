import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

import './home_page.dart';
import './money_page.dart';
import './market_page.dart';
import './trade_page.dart';
import './member_page.dart';

class IndexPage extends StatefulWidget {
  @override
  _IndexPageState createState() => _IndexPageState();
}

class _IndexPageState extends State<IndexPage> {

  PageController _pageController;

  final List<BottomNavigationBarItem> bottomTabs = [
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.home), title: Text('首页')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.search), title: Text('行情')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.shopping_cart), title: Text('法币')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.book), title: Text('币币')),
    BottomNavigationBarItem(
      icon: Icon(CupertinoIcons.profile_circled), title: Text('个人'))
  ];

  final List<Widget> tabBodies = [
    HomePage(),
    MarketPage(),
    MoneyPage(),
    TradePage(),
    MemberPage()
  ];

  int currentIndex = 0;
  var currentPage;

  @override
  void initState() {
    // 进行初始化操作
    currentPage = tabBodies[currentIndex];
    _pageController = new PageController()
      ..addListener(() {
        if(currentPage != _pageController.page.round()) {
          setState(() {
           currentPage = _pageController.page.round(); 
          });
        }
      });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    ScreenUtil.instance = ScreenUtil(width: 750, height: 1334)..init(context);
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
      body: IndexedStack(
        index: currentIndex,
        children: tabBodies,
      )
    );
  }



}