/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.util.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * post 工具
 *
 * @author yuhao.zx
 * @version $Id: Poster.java, v 0.1 2018年12月17日 10:21 PM yuhao.zx Exp $
 */
public class Poster {

    public static void main(String[] args) throws IOException {

        //String url = "http://promocontent-S901929914.web.dev.alipay.net/hzf/content/search.json?_output_charset=utf-8&ctoken=OaVIjJVa__volea6";
        //String cookie="zone=GZ00B; _dd_scan=\"nOqd7rNy7F18CZZFzp8CxA,,\"; buservice_domain_id=KOUBEI_SALESCRM; route_group=alipay.net;IS_INNER_LOGIN=1;sofahelp_USER_COOKIE=7C991743E434EE505775566AC6565375583DE150CE3995B46391DEAE09D0EC1FADFB963951F5C7514C8C9FB3DE6B3C412F193B27E273F26E6BCE2DE3C4E0EFAE64DC48634AF5A66FB4647F90B6289F3DC8CBFFFCB166C80845192D8E961566AA3E0808617D4078EB5791C998FF7F0A486753E769FE256C75EEE77CFCDCF5E991FC3CE1ADC0120D8322A1CE736931C08452F06459B7182C7059A6E48135D21BBF0037392B7F92C14EDE33C994E6B78DE31B8E3B9FAD4C2388727B9E1091C4F91868B6EE4B874ADACBD9C65ADD1E6DDB994369311F68F8011EACB3D041C44BA1EDC508BAF80D465274AB25104C40664B01D9F8B89A086A77DDAD2A93DE95F3E4BA80591151DC64FA0B6945B798266033071814016F30357AB3687592F6173F7C9E9A02F1025AC932761E154584FCFBDEC04DB507C5913EB5D3AB4C18F19E506620D6DCC2B3216F03633BBD76746F015B68B6772AB538D24681D850FDE17469A626C9C85271B51AE1B682579BC1DF60AFB4E8E06373B8538BE5FDD28352D575EE1B9850147B62514374EBC3B2B04E44C39D394917767479D6644B9F46C3F7EEEECBAE10C473B32DD38B43D246A1EAAB9A5F26D9F4FE05EFE06B28A44CDCB8177AE6; sofahelp_SSO_TOKEN=0A2A678BE584046D9D85D794C97D4618070440343E8535429FF1B9D6932819B87DB85C25D35794CAC6AD11A7D3583D25; sofahelp_LAST_HEART_BEAT_TIME=196331A93B502AF8A610A076B21071AF; SSO_LANG_V2=ZH-CN; UM_distinctid=167827ece62c67-0c1cd792d7ae2b-35677407-13c680-167827ece63908; sso.global.authtoken=sso.global.authtoken; _b_s=yuhao.zx; _b_n=68019; ALIPAYBUMNGJSESSIONID=GZ004hBsmUEmi5sWiejW4Fq1tBUJsKantprocessGZ00; IAM_TOKEN=eyJraWQiOiJkZWZhdWx0IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJjbmwiOiJCVUMiLCJzdWIiOiJ5dWhhby56eCIsImF1dGhfdHAiOlsiQ0VSVCIsIkRPTUFJTiJdLCJpc3MiOiJidW1uZy5hbGlwYXkuY29tIiwibm9uY2UiOiI2ZDFlZTcxNSIsInNpZCI6IjEwMDAxNDg1MTgiLCJhdWQiOiIqIiwibmJmIjoxNTQ1MDE3NjA3LCJzbm8iOiI2ODAxOSIsInRudF9pZCI6IkFMSVBXM0NOIiwibmFtZSI6IuWuh-aZpyIsImV4cCI6MTU0NTEwNDA2NywiaWF0IjoxNTQ1MDE3NjY3LCJqdGkiOiI2OTIxZjQ1ODZhYTk0YmRlODQ1N2MxOWQ0YjRkMmM0MiJ9.N_s7-MmbuGf97eBDYi471Kg5bOqBkBPjMzC9xTZRZ5NRzaB9PmeVCcsh7gMj79pLD_VAHayuGjqp06AKjErGJA; __TRACERT_COOKIE_bucUserId=68019; JSESSIONID=47C77BC3ACAE6846F772E36C16241B8A; ALIPAYZAUTHJSESSIONID=GZ00JzFohrzRoQOVSXHaOFT9qZEyEropsslaGZ00; ctoken=OaVIjJVa__volea6; SSO_EMPID_HASH_V2=6681f9a3b77f2bfbdf50935ad3ad4d74; ZAUTH_REST_LOGIN_INFO=7b22666f7277617264223a302c226970223a2231312e3136362e3138392e313139222c226c6f67696e4e616d65223a22797568616f2e7a78222c226c6f67696e54696d65223a313534353035343132323532342c22746f6b656e223a2264613038343836642d663533392d343831622d393265622d323065626132613537386330222c2275726c223a22687474703a2f2f7a617574682e737461626c652e616c697061792e6e65742f2f726573742f6765744c6f67696e5573657241757468732e6a736f6e227d; session.cookieNameId=ALIPAYBUMNGJSESSIONID; _TRACERT_COOKIE___TRACERT_COOKIE__SESSION=7d7213d6-98c2-442b-bec8-e444dc394e53; _TRACERT_COOKIE__tree=a701%0178b396b3-9e75-429e-a1d4-77292f60f9ac%011";
        //String body="{\"pageSize\":\"10\",\"pageNo\":\"1\"}";

        Properties params = readParams(args[0]);

        String url = (String) params.get("url");

        String cookie = (String) params.get("cookie");

        String body = (String) params.get("body");

        System.out.println(url);
        System.out.println(cookie);
        System.out.println(body);

        post(url, cookie, body);
    }

    private static Properties readParams(String path) throws IOException {
        Properties properties = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        properties.load(bufferedReader);
        return properties;
    }

    public static void post(String url, String cookie, String body) {

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        Request request = new Request.Builder().url(url).addHeader("Cookie", cookie)
            .addHeader("Content-Type", "application/json").post(RequestBody.create(mediaType, body))
            .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse:" + response.body().string());
            }
        });
    }
}