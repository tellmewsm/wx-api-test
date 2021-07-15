# wx-api-test

> http api test

## about
mvn  clean install

Test Steps Demo:

```
    private static Wequest wequest = WxApi.build(Wequest.class);
    private static ResponseResult response;
    private Map<String, Object> Headers = new HashMap<>();
    private Map<String, Object> body = new HashMap<>();

    public static void main(String args[]) {
        testHttpsGet();
    }

    @Test
    public static void testHttpsGetError() {
        response = wequest.HttpsGet("https://www.baidu.com11111");
        if (!response.success) {
            System.out.println(response.message);
        }
    }

    @Test
    public static void testHttpsGet() {
        response = wequest.HttpsGet("https://api.apiopen.top/searchMusic");
        System.out.println(response.getResponseReturn());
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getField("message"));
    }

    @Test
    public void testPostBodyHeader() {

        body.put("testUserName", "tellme");
        Headers.put("Accept", "application/json, text/plain, */*");
        response = wequest.HttpsPostBodyHeader(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic", Headers);
        System.out.println(response.getResponseReturn());
        Assert.assertEquals(response.getField("code"), "200");

    }

```


