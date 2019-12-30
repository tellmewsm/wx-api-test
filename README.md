# wx-api-test

> http api test

## about
mvn install clean test

Test Steps Demo:

```
Map<String, Object> Headers = new HashMap<>();
Map<String, Object> body = new HashMap<>();

body.put("testUserName","tellme");
Headers.put("Accept", "application/json, text/plain, */*");

response = wxAutoTest.HttpsPostBodyHeader(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic",Headers);
Assert.assertEquals(HttpUtils.getField("code"), "200");
```


