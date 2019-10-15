const serviceUrl = 'http://118.126.92.128:19091';

const servicePath = {
  'commonBanner': serviceUrl + '/common/banner', // 首页轮播图
  'commonNavigator': serviceUrl + '/common/config/getNavigator', //首页导航8个icon
  'getAdBanner': serviceUrl + '/common/config/getAdBanner', //首页广告图片

  'userLogin': serviceUrl + '/user/login', //用户登录
  'userInfo': serviceUrl + '/user/info', //用户个人信息

  'assetList': serviceUrl + '/wallet/asset/assetList', //用户资产列表

  'currencyList': serviceUrl + '/wallet/asset/currencyList', //币种列表
  'getAssetByCurrencyId': serviceUrl + '/wallet/asset/getAssetByCurrencyId', //用户资产列表
  'dispenseAddress': serviceUrl + '/wallet/address/dispense', //分配或获取地址
};