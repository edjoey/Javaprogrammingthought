# 行政区域查询
---------------------------------------
<br />

行政区域查询是一类简单的HTTP接口，根据用户输入的搜索条件可以帮助用户快速的查找特定的行政区域信息。
目前高德的服务能够让用户查询到街道级别的信息。
例如：中国>山东省>济南市>历下区>舜华路街道（国>省>市>区>街道）。

## API列表

<div>
    <table>
        <tr>
            <th >API名称</th>
            <th >认证方式</th>
            <th >描述</th>
        </tr>
            <tr>
                <td>行政区域查询</td>
                <td>APP</td>
                <td>行政区域查询是一类简单的HTTP接口，根据用户输入的搜索条件可以帮助用户快速的查找特定的行政区域信息。
目前高德的服务能够让用户查询到街道级别的信息。
例如：中国>山东省>济南市>历下区>舜华路街道（国>省>市>区>街道）。</td>
            </tr>
    </table>
</div>

## API调用
### 公共入参
公共请求参数是指每个接口都需要使用到的请求参数。

参数名称 | 位置 |必须 | 描述
-------|------|--------|----
X-Ca-Key |Header| 是  | Appkey，调用API的身份标识，可以到阿里云[API网关控制台](https://apigateway.console.aliyun.com/#/apps/list)申请
X-Ca-Signature | Header| 是  | 通过签名计算规则计算的请求签名串，参照：<a href="#Signature">签名计算规则</a>
X-Ca-Timestamp | Header| 否  | API 调用者传递时间戳，值为当前时间的毫秒数，也就是从1970年1月1日起至今的时间转换为毫秒，时间戳有效时间为15分钟
X-Ca-Nonce|Header| 否  |API请求的唯一标识符，15分钟内同一X-Ca-Nonce不能重复使用，建议使用 UUID，结合时间戳防重放
Content-MD5|Header| 否  |当请求 Body 非 Form 表单时，需要计算 Body 的 MD5 值传递给云网关进行 Body MD5 校验
X-Ca-Signature-Headers|HeaX-Ca-Timestampder|否|指定哪些Header参与签名，支持多值以","分割，默认只有X-Ca-Key参与签名，为安全需要也请将X-Ca-Timestamp、X-Ca-Nonce进行签名，例如：X-X-Ca-Signature-Headers:Ca-Timestamp,X-Ca-Nonce

### <span id="Signature">签名计算规则</span>
请求签名，是基于请求内容计算的数字签名，用于API识别用户身份。客户端调用API时，需要在请求中添加计算的签名（X-Ca-Signature）。
#### 签名计算流程
_________________________________________________________
准备APPkey → 构造待签名字符串stringToSign → 使用Secret计算签名
_________________________________________________________

##### 1.准备APPKey
Appkey，调用API的身份标识，可以到阿里云[API网关控制台](https://apigateway.console.aliyun.com/#/apps/list)申请

##### 2.构造待签名字符串stringToSign

````
String stringToSign=
HTTPMethod + "\n" +
Accept + "\n" +                //建议显示设置 Accept Header。当 Accept 为空时，部分 Http 客户端会给 Accept 设置默认值为 */*，导致签名校验失败。
Content-MD5 + "\n"
Content-Type + "\n" +
Date + "\n" +
Headers +
Url
````

###### HTTPMethod
为全大写，如 POST。

````
Accept、Content-MD5、Content-Type、Date 如果为空也需要添加换行符”\n”，Headers如果为空不需要添加”\n”。
````

###### Content-MD5

Content-MD5 是指 Body 的 MD5 值，只有当 Body 非 Form 表单时才计算 MD5，计算方式为：

String content-MD5 = Base64.encodeBase64(MD5(bodyStream.getbytes("UTF-8")));
bodyStream 为字节数组。

###### Headers

Headers 是指参与 Headers 签名计算的 Header 的 Key、Value 拼接的字符串，建议对 X-Ca 开头以及自定义 Header 计算签名，注意如下参数不参与 Headers 签名计算：X-Ca-Signature、X-Ca-Signature-Headers、Accept、Content-MD5、Content-Type、Date。

###### Headers 组织方法：
先对参与 Headers 签名计算的 Header的Key 按照字典排序后使用如下方式拼接，如果某个 Header 的 Value 为空，则使用 HeaderKey + “:” + “\n”参与签名，需要保留 Key 和英文冒号。

````
String headers =
HeaderKey1 + ":" + HeaderValue1 + "\n"\+
HeaderKey2 + ":" + HeaderValue2 + "\n"\+
...
HeaderKeyN + ":" + HeaderValueN + "\n"
````

将 Headers 签名中 Header 的 Key 使用英文逗号分割放到 Request 的 Header 中，Key为：X-Ca-Signature-Headers。

###### Url

Url 指 Path + Query + Body 中 Form 参数，组织方法：对 Query+Form 参数按照字典对 Key 进行排序后按照如下方法拼接，如果 Query 或 Form 参数为空，则 Url = Path，不需要添加 ？，如果某个参数的 Value 为空只保留 Key 参与签名，等号不需要再加入签名。

````
String url =
Path +
"?" +
Key1 + "=" + Value1 +
"&" + Key2 + "=" + Value2 +
...
"&" + KeyN + "=" + ValueN
````

注意这里 Query 或 Form 参数的 Value 可能有多个，多个的时候只取第一个 Value 参与签名计算。

##### 3.使用Secret计算签名

````
Mac hmacSha256 = Mac.getInstance("HmacSHA256");
byte[] keyBytes = secret.getBytes("UTF-8");
hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));
String sign = new String(Base64.encodeBase64(Sha256.doFinal(stringToSign.getBytes("UTF-8")),"UTF-8"));
````

Secret 为 APP 的密钥，请在[应用管理](https://apigateway.console.aliyun.com/#/apps/list)中获取。



## API名称：行政区域查询

### *描述*

行政区域查询是一类简单的HTTP接口，根据用户输入的搜索条件可以帮助用户快速的查找特定的行政区域信息。
目前高德的服务能够让用户查询到街道级别的信息。
例如：中国>山东省>济南市>历下区>舜华路街道（国>省>市>区>街道）。

### *请求信息*

HTTP协议：HTTP

调用地址：district.market.alicloudapi.com/v3/config/district

方法：GET

<br />
### *请求参数*

<div>
<table>
<tr>
<th style="width: 20%;">名称</th>
<th style="width: 10%;">位置</th>
<th style="width: 10%;">类型</th>
<th style="width: 10%;">必填</th>
<th style="width: 30%;">描述</th>
</tr>
<tr>
<td>keywords</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>规则：只支持单个关键词语搜索关键词支持：行政区名称、citycode、adcode 例如，在subdistrict=2且showbiz=false时，搜索省份（例如山东），能够显示市（例如济南），区（例如历下区） 在subdistrict=1且showbiz=false时，搜索区（例如历下区）能够显示街道信息（例如舜华路街道）</td>
</tr>
<tr>
<td>subdistrict</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>规则：设置显示下级行政区级数（行政区级别包括：国家、省/直辖市、市、区/县、商圈、街道多级数据），其中街道数据仅在keywords为区/县、商圈的时候显示 可选值：0、1、2、3等数字，并以此类推 0：不返回下级行政区； 1：返回下一级行政区； 2：返回下两级行政区； 3：返回下三级行政区；</td>
</tr>
<tr>
<td>showbiz</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>可选为true/false，为了能够精准的定位到街道，特别是在快递、物流、送餐等场景下，强烈建议将此设置为false。</td>
</tr>
<tr>
<td>page</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>最外层的districts最多会返回20个数据，若超过限制，请用page请求下一页数据。 例如page=2；page=3。默认page=1</td>
</tr>
<tr>
<td>offset</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>最外层返回数据个数</td>
</tr>
<tr>
<td>extensions</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>此项控制行政区信息中返回行政区边界坐标点； 可选值：base、all; base:不返回行政区边界坐标点； all:只返回当前查询district的边界值，不返回子节点的边界值；</td>
</tr>
<tr>
<td>filter</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>按照指定行政区划进行过滤，填入后则只返回该省/直辖市信息 需填入adcode，为了保证数据的正确，强烈建议填入此参数</td>
</tr>
<tr>
<td>callback</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>callback值是用户定义的函数名称，此参数只在output=JSON时有效</td>
</tr>
<tr>
<td>output</td>
<td>QUERY</td>
<td>STRING</td>
<td>否</td>
<td>可选值：JSON，XML</td>
</tr>
</table>
</div>

<br />
### *返回信息*

#### 返回参数类型

JSON

#### 返回结果示例

````
{
	"status" :"1",
	"info" :"OK",
	"infocode" :"10000",
	"count" :"1",
	"suggestion" :	{
		"keywords" :[ ],
		"cities" :[ ]
	},
	"districts" :[
		"0" :{
			"citycode" :"010",
			"adcode" :"110000",
			"name" :"北京市",
			"center" :"116.407394,39.904211",
			"level" :"province",
			"districts" :[ ]
		}
	]
}
````

#### 异常返回示例

````

````

<br />
### *错误码*

<div>
<table>
<tr>
<th style="width: 15%;">错误码</th>
<th style="width: 20%;">错误信息</th>
<th style="width: 25%;">描述</th>
</tr>
<tr>
<td>公共错误码</td>
<td>--</td>
<td>所有API公用的错误码，请参照《 <a href="#pubError">公共错误码</a> 》</td>
</tr>
</table>
</div>




## <span id='pubError'>公共错误</span>
### 如何获取公共错误
所有的 API 请求只要到达了网关，网关就会返回请求结果信息。

用户需要查看返回结果的头部，即 Header 部分。返回参数如示例：

	//请求唯一ID，请求一旦进入API网关应用后，API网关就会生成请求ID并通过响应头返回给客户端，建议客户端与后端服务都记录此请求ID，可用于问题排查与跟踪
	X-Ca-Request-Id: 7AD052CB-EE8B-4DFD-BBAF-EFB340E0A5AF

	//API网关返回的错误消息，当请求出现错误时API网关会通过响应头将错误消息返回给客户端
	X-Ca-Error-Message: Invalid Url

	//当打开Debug模式后会返回Debug信息，此信息后期可能会有变更，仅用做联调阶段参考
	X-Ca-Debug-Info: {"ServiceLatency":0,"TotalLatency":2}

在 Header 中获得 X-Ca-Error-Message 可以基本明确报错原因，而 X-Ca-Request-Id 可以用于提供给这边的支持人员，供支持人员搜索日志。
### 公共错误码
#### 客户端错误

错误代码|Http 状态码|语义|解决方案
------|-----------|---|------
Throttled by USER Flow Control|403|因用户流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Throttled by APP Flow Control|403|因APP流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Throttled by API Flow Control|403	|因 API 流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Throttled by DOMAIN Flow Control	|403|	因二级域名流控被限制|直接访问二级域名调用 API，每天被访问次数上限1000次。
TThrottled by GROUP Flow Control|403|因分组流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Quota Exhausted	|403|	调用次数已用完	|购买的次数已用完。
Quota Expired	|403|	购买次数已过期	|购买的次数已经过期。
User Arrears	|403|	用户已欠费	|请尽快充值续费。
Empty Request Body	|400|	body 为空|	请检查请求 Body 内容。
Invalid Request Body	|400	|body 无效	|请检查请求 Body。
Invalid Param Location	|400|	参数位置错误|请求参数位置错误。
Unsupported Multipart	|400|	不支持上传|不支持上传文件。
Invalid Url	|400	|Url 无效	|请求的 Method、Path 或者环境不对。请参照错误说明 Invalid Url。
Invalid Domain	|400|	域名无效	|请求域名无效，根据域名找不到 API。请联系 API 服务商。
Invalid HttpMethod	|400	|HttpMethod 无效|输入的 Method 不合法。
Invalid AppKey|400|AppKey 无效或不存在	|请检查传入的 AppKey。注意左右空格的影响。
Invalid AppSecret	|400	|APP 的Secret 错误|	检查传入的 AppSecret。注意左右空格的影响。
Timestamp Expired|400|时间戳过时|请核对请求系统时间是否为标准时间。
Invalid Timestamp	|400|	时间戳不合法|请参照 请求签名说明文档。
Empty Signature	|404|签名为空|请传入签名字符串，请参照 请求签名说明文档。
Invalid Signature, Server StringToSign:%s|400|签名无效|签名无效，参照 Invalid Signature 错误说明
Invalid Content-MD5|400|	Content-MD5 值不合法|请求 Body 为空，但传入了 MD5 值，或 MD5 值计算错误。请参照 请求签名说明文档。
Unauthorized	|403|	未被授权|	APP 未获得要调用的 API 的授权。请参照错误说明 Unauthorized。
Nonce Used|400|	SignatureNonce| 已被使用|SignatureNonce 不能被重复使用。
API Not Found|	400	|找不到 API|传入的APIdi地址或者HttpMethod不正确，或已下线。

#### 服务器端错误（调用 API）
以下为API服务端错误，如果频繁错误，可联系服务商。

错误代码|Http状态码|语义|解决方案
------|----------|---|----
Internal Error	|500	|内部错误|建议重试,或者联系服务商
Failed To Invoke Backend Service	|500|	底层服务错误|API 提供者底层服务错误，建议重试，如果重试多次仍然不可用，可联系 API 服务商解决
Service Unavailable|503|	服务不可用	|建议重试,或者联系服务商
Async Service	|504	|后端服务超时|建议重试,或者联系服务商

