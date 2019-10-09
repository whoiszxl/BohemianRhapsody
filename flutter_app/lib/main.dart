import 'package:flutter/material.dart';
import 'package:flutter_app/utils/SpUtils.dart';
import './pages/index_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {

    //初始化spUtils
    SpUtils.getInstance();

    return Container(
      child: MaterialApp(
        title: 'BohemianRhapsody',
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
          primaryColor: Colors.pink
        ),
        home: IndexPage(),
      ),
    ); 
  }
}