import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_app/service/service_method.dart';
import 'package:fluttertoast/fluttertoast.dart';

class LoginPage extends StatefulWidget {
  static String tag = 'login-page';
  @override
  _LoginPageState createState() => new _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  @override
  Widget build(BuildContext context) {
    final email = TextFormField(
      keyboardType: TextInputType.emailAddress,
      autofocus: false,
      initialValue: '17688900411',
      decoration: InputDecoration(
          hintText: 'Email',
          contentPadding: EdgeInsets.fromLTRB(10.0, 2.0, 10.0, 10.0),
          border: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.black26)),
          focusedBorder: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.black))),
    );

    final password = TextFormField(
      autofocus: false,
      initialValue: '',
      obscureText: true,
      decoration: InputDecoration(
          hintText: 'Password',
          contentPadding: EdgeInsets.fromLTRB(10.0, 2.0, 10.0, 10.0),
          border: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.black26)),
          focusedBorder: UnderlineInputBorder(
              borderSide: BorderSide(color: Colors.black))),
    );

    final loginButton = Padding(
      padding: EdgeInsets.symmetric(vertical: 16.0),
      child: RaisedButton(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(18),
        ),
        onPressed: () {
          request('userLogin', formData: '{"phone": "17688900411","password": "zxl123456"}').then((val) {
            var data = json.decode(val.toString());
          });
        },
        padding: EdgeInsets.all(12),
        color: Colors.grey,
        child: Text('Log In', style: TextStyle(color: Colors.white)),
      ),
    );

    final forgotLabel = FlatButton(
      child: Text(
        'Forgot password?',
        style: TextStyle(color: Colors.black54),
      ),
      onPressed: () {},
    );

    return Scaffold(
      backgroundColor: Colors.white,
      appBar: new AppBar(
        backgroundColor: Colors.white,
        elevation: 0,
        title: const Text(
          '',
          style: TextStyle(color: Colors.black),
        ),
        centerTitle: false,
      ),
      body: Center(
        child: ListView(
          padding: EdgeInsets.only(left: 24.0, right: 24.0, top: 20.0),
          children: <Widget>[
            new Text(
              "登录",
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 50.0,
                  fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 48.0),
            email,
            SizedBox(height: 28.0),
            password,
            SizedBox(height: 24.0),
            loginButton,
            forgotLabel
          ],
        ),
      ),
    );
  }
}
