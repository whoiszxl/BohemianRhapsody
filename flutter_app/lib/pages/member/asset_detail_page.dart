import 'package:flutter/material.dart';

class AssetDetailPage extends StatefulWidget {

  final String a;
  AssetDetailPage(this.a) : super();

  _AssetDetailPageState createState() => _AssetDetailPageState(a);
}

class _AssetDetailPageState extends State<AssetDetailPage> {

  _AssetDetailPageState(a) {
    print(a);
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Text('hello'),
    );
  }

}