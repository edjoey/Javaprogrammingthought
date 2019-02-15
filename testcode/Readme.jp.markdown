# API Gateway Java SDK ユーザーガイド

## 1 SDK概要

この SDK は Alibaba Cloud API Gateway より、ユーザーが設定した全ての API を基づいて、自動的に生成した Java 用の SDK です。

**注意：全ての API はグループ、リージョンで管理され、以下の {{groupName}} は全部 API のグループ名で、{{regionId}} はグループのリージョンです。**

API Gateway Java SDK は github 上にも公開しています。[Githubへ](https://github.com/aliyun/apigateway-sdk-core)

ソースコード構造は以下になります：

* SDK ディレクトリ
  	* sdk/{{regionId}}		`Java の SDK ディレクトリ，グループの全ての API リクエスト用コードを含めています`
  		* SyncApiClient_{{group}}.java	`グループの API の同期リクエストの実装`
  		* AsyncApiClient_{{group}}.java	`グループの API の非同期リクエストの実装`
  		* SyncDemo_{{group}}.java	`同期リクエストの例`
  		* AsyncDemo_{{group}}.java	`非同期リクエストの例` 	
  	* doc/{{regionId}}
  		* ApiDocument_{{group}}.md	`Group の API ドキュメント`
  	* lib
  		* sdk-core-java-1.1.0.jar `coreパッケージ、このSDKの依存パッケージ`
  		* sdk-core-java-1.1.0-sources.jar		`上記のパッケージのソースコード`
  	* Readme.md	`SDK ユーザーガイド`
  	* LICENSE `ライセンスの説明`
## 2 SDK使用

### 2.1 事前準備

1. Alibaba Cloud API Gateway の Java SDK を利用するには `JDK 1.6` 以上のバージョンが必要です。
2. 署名のために権限付与済みのキーペアを用意する必要があります。[AppKeyとAppSecretについて](https://jp.alibabacloud.com/help/doc-detail/29488.htm)

 **注意事項：APP_KEY と APP_SECRET は API Gateway がユーザーのリクエストを認証する鍵であり、クライアント側に保存する場合、暗号化する必要があります。**

1. pom.xmlファイルに追加：
    <dependency>
        <groupId>com.aliyun.api.gateway</groupId>
        <artifactId>sdk-core-java</artifactId>
        <version>1.1.0</version>
    </dependency>

### 2.2 API リクエスト用 SDK クラス導入

1. sdk フォルダの中の `AsyncApiClient_*.java` と `SyncApiClient_*.java` をプロジェクトフォルダにコピーします。
2. 必要に応じてpackageを更新します。
### 2.3 Api Client 初期化

API Gateway にリクエストを送信するため、`ApiClient` オブジェクトを作成する必要があります。`(A)SyncDemo_*.java` のサンプルコードをご参考できます。`newBuilder()` を使って `ApiClientBuilder` オブジェクトで `ApiClient` を作成できます。

    public AsyncDemo_{{group}}() {
        this.asyncClient = AsyncApiClient_{{group}}.newBuilder()
            .appKey("your app key here")
            .appSecret("your app secret here")
            .build();
    }

> #### 注意

> * `ApiClientBuilder` の大部分のメソッドはカレントオブジェクトを返すようにしています。メソッドを組み合わせて呼び出すと利便性と可読性が向上します。
> * 必要なプロパティを全部設定したら、`build()` をコールすることでクライアントを作成できます。作成した `ApiClient` は変更できません。
> * `ApiClientBuilder` は同じ設定を使って複数のクライアントを作成できます。利用する際に注意すべきなのは、`ApiClientBuilder` は可変ノンスレッドセーフであることです。
> * 作成した `ApiClient` は**スレッドセーフ**なもの，独立なコネクションプール/スレッドプールのリソースを持ち、パフォーマンス向上のため、永続オブジェクトにすべきです。
### 2.4 API リクエスト

SDK は API Gateway で定義したパラメータに基づいて生成したもの、全ての API をメソッドにパッケージングしました。Demo 内のサンプルコードをご参考にできます。
そして、SDK はシングルトンでパッケージングしました。`AsyncDemo_{{group}}.getInstance()` を利用することで ApiClient オブジェクトを取得できます。

> #### 注意

> * まず一つのオブジェクトを `build()` してから、`getInstance()` をご利用できるようになります。そうしないとエラーになります。
> * 複数の同じ ApiClient を `build()` したら、`getInstance()` は最後の一回の `build()` で生成したオブジェクトを返します。
## 3 高度な使用シーン

`sdk-core-java-1.0.3` は ApacheHttpClient_4.5.2 を基礎 HTTP クライアントとして用いて、いろんな設定を含まれています。`ApiClientBuilder` はメジャーなシーンしかカバーしていませんが、柔軟で便利なインタフェースを提供しています。それらのインタフェースを使って基礎 HTTP クライアントを利用することもできます。例えば OkHttp3。

### 3.1 もっと詳細な ApacheHttpClient の設定

こちらの [ApacheHttpClientドキュメント](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/index.html) の方法に基づいて自分で作成できます。自分で作成した [HttpClientBuilder](http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/impl/client/HttpClientBuilder.html) を、2.3章の `ApiClientBuilder` に `builder.setExtraParam("apache.httpclient.builder", ${apacheBuilder})` をコールします。そうすることで `HttpClientBuilder` の全てのパラメータを `ApiClientBuilder` に導入できます。

    HttpClientBuilder apacheHttpClientBuilder = HttpClientBuilder.create()
        .setHttpProcessor(new MyHttpProcessor())
        .setDefaultRequestConfig(
            RequestConfig.custom()
                .setConnectTimeout(5000)
                .build())
        .disableAuthCaching();

    SyncApiClient_{{group}} syncClient = SyncApiClient_{{group}}.newBuilder()
        .appKey("your app key here")
        .appSecret("your app secret here")
        .connectionTimeoutMillis(10000L) //this will overwrite 5000 to 10000
        .setExtParams("apache.httpclient.builder", apacheHttpClientBuilder)
        .build();

> **注意**
> もし `HttpClientBuilder` と `ApiClientBuilder` の同じパラメータに違う値を設定したら、順番に関係なく、`ApiClientBuilder` が優先されます。上記のサンプルコードの中に、クリエートした `SyncApiClient` の `connectionTimeout` は `10000L`。

### 3.2 カスタマイズ HttpClient の使用

カスタマイズ HttpClient (例えば OkHttp3) を利用したい場合、`com.alibaba.cloudapi.sdk.core.HttpClient` を継承すれば利用できます。

builder にパラメータを渡す場合、`setExtParams` でカスタマイズパラメータを渡すことができます。これらのパラメータは `HttpClient` の `init()` メソッドのパラメータとして渡されます。具体的にはこちらをご参考できます: `com.alibaba.cloudapi.sdk.core.http.ApacheHttpClient`。カスタマイズ HttpClient を使う場合、サービスを起動するときに、`-Daliyun.sdk.httpclient="${class}"` パラメータを追加します。`${class}` はカスタマイズ `HttpClient` のインタフェース実装クラスのフルネームです（パッケージを含む）。

> `-Daliyun.sdk.httpclient` のデフォルト値は `"com.alibaba.cloudapi.sdk.core.http.ApacheHttpClient"`

    import com.alibaba.cloudapi.sdk.core.HttpClient

    public class MyHttpClient extends HttpClient{

        private CustomHttpClient customHttpClient;

        @Override
        protected void init(BuilderParams builderParams){
            // init your customHttpClient with params
            Object config1 = builderParams.getExtra("key1");
            Object config2 = builderParams.getExtra("key2");
            customHttpClient = new CustomHttpClient(config1, config2);
        }

        @Override
        public ApiResponse syncInvoke(ApiRequest request) throws IOException{
            // parse request
            CustomeHttpRequest httpRequest = parseToHttpRequest(request);

            // send http request
            CustomeHttpResponse httpResponse = customHttpClient.execute(httpRequest);

            // parse response
            return parseToApiResponse(httpResponse);
        }

        @Override
        public Future<ApiResponse> asyncInvoke(ApiRequest request, ApiCallBack callback){
            // do async
        }

        @Override
        public void shutdown() {
            // release your custom httpclient
            customHttpClient.shutdown();
        }
    }

# 4	サポート

サポートが必要な場合は、サポートセンターに起票してください。

