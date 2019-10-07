import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';
import './pages/index_page.dart';
import './routers/routers.dart';
import './routers/application.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {

    //-------------------主要代码start
    final router = Router();
    Routes.configureRoutes(router);
    Application.router=router;
    //-------------------主要代码end

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