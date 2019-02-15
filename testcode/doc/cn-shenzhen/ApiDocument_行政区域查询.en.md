# 行政区域查询
---------------------------------------
<br />

行政区域查询是一类简单的HTTP接口，根据用户输入的搜索条件可以帮助用户快速的查找特定的行政区域信息。
目前高德的服务能够让用户查询到街道级别的信息。
例如：中国>山东省>济南市>历下区>舜华路街道（国>省>市>区>街道）。

## API list

<div>
    <table>
        <tr>
            <th >API name</th>
            <th >Authentication method</th>
            <th >Description</th>
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

## API call
### Public incoming parameters
Public request parameters are required for each API.

Parameter name | Location | Required? | Description
-------|------|--------|----
X-Ca-Key |Header| Yes | Appkey, ID for an API call, which can be applied for on the Alibaba Cloud [API Gateway console](https://apigateway.console.aliyun.com/#/apps/list).
X-Ca-Signature | Header| Yes | Request signature string calculated using the signature calculation rule. For more information, see <a href="#Signature">Signature calculation rule</a>.
X-Ca-Timestamp | Header| No| Time stamp in milliseconds passed by the API caller, that is, the milliseconds from January 1, 1970 till now. A time stamp is valid for 15 minutes by default.
X-Ca-Nonce|Header| No | Unique ID of an API request. An X-Ca-Nonce cannot be used repeatedly within 15 minutes. UUID is recommended. It is used together with the time stamp to prevent replay.
Content-MD5|Header| No | When the requested Body is not a Form, the MD5 value of Body needs to be calculated and delivered to the cloud gateway for Body MD5 verification.
X-Ca-Signature-Headers|Header| No |Headers containing signatures. Different values are separated by commas (,). By default, only X-Ca-Key contains a signature. To ensure security, add signatures to X-Ca-Timestamp and X-Ca-Nonce, for example, X-X-Ca-Signature-Headers:Ca-Timestamp,X-Ca-Nonce.

### &lt;span id=&quot;Signature&quot;&gt;Signature calculation rule</span>
Request signature, which is a digital signature calculated based on the request content. It is used by APIs to identify users. When the client calls an API, the client adds a calculated signature to the request (X-Ca-Signature).
### Signature calculation process
_________________________________________________________
Prepare for the APPkey -> Construct the stringToSign -> Use Secret to calculate the signature.
_________________________________________________________

#### 1. Prepare for the AppKey.
Appkey, ID for an API call, which can be applied for on the Alibaba Cloud [API Gateway console](https://apigateway.console.aliyun.com/#/apps/list).

##### 2. Construct the stringToSign.

````
String stringToSign=
HTTPMethod + "\n" +
Accept + "\n" +                //It is recommended to set Accept Header. If Accept is empty, some HTTP clients set Accept to the default value */*. As a result, signature verification will fail.
Content-MD5 + "\n"
Content-Type + "\n" +
Date + "\n" +
Headers +
Url
````

###### HTTPMethod
The value is in capitals, for example, POST.

````
If Accept, Content-MD5, Content-Type, and Date are empty, add a linefeed "\n". If Headers is empty, "\n" is not required.
````

###### Content-MD5

Content-MD5 refers to the MD5 value of Body. The MD5 value is calculated only when the Body is not Form. The calculation is as follows:

String content-MD5 = Base64.encodeBase64(MD5(bodyStream.getbytes("UTF-8")));
bodyStream indicates the byte array.

###### Headers

Headers refer to the string consisting of the key and value of the header with signature. It is recommended to calculate signatures for the headers starting with X-Ca and custom headers. Note that the following parameters cannot be used for headers signature calculation: X-Ca-Signature, X-Ca-Signature-Headers, Accept, Content-MD5, Content-Type, and Date.

###### Headers organization method:
Sort the keys used for headers signature calculation in alphabetical order, and construct the string according to the following rule: If a value of the header is empty, signature is calculated using HeaderKey + “:” + “\n”. Key and the colon (:) cannot be removed.

````
String headers =
HeaderKey1 + ":" + HeaderValue1 + "\n"\+
HeaderKey2 + ":" + HeaderValue2 + "\n"\+
...
HeaderKeyN + ":" + HeaderValueN + "\n"
````

The header keys used for headers signature calculation are separated by commas, and placed into the header of request. The Key is X-Ca-Signature-Headers.

###### Url

URL refers to the Form parameter in Path + Query + Body. The URL is organized as follows: For the Query + Form parameters, the keys are sorted in alphabetical order. If the Query or Form parameter is null, URL is set to Path. The question mark (?) is not required. If the value of a parameter is null, only the key is reserved for signature. The equal sign (=) does not need to be added to the signature.

````
String url =
Path +
"?" +
Key1 + "=" + Value1 +
"&" + Key2 + "=" + Value2 +
...
"&" + KeyN + "=" + ValueN
````

Note that Query or Form may have multiple values. If there are multiple values, the first value is used for signature calculation.

##### 3. Use Secret to calculate the signature.

````
Mac hmacSha256 = Mac.getInstance("HmacSHA256");
byte[] keyBytes = secret.getBytes("UTF-8");
hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));
String sign = new String(Base64.encodeBase64(Sha256.doFinal(stringToSign.getBytes("UTF-8")),"UTF-8"));
````

Secret is the APP's key, which can be obtained from [Application management](https://apigateway.console.aliyun.com/#/apps/list).



## API name: 行政区域查询

### *Description*

行政区域查询是一类简单的HTTP接口，根据用户输入的搜索条件可以帮助用户快速的查找特定的行政区域信息。
目前高德的服务能够让用户查询到街道级别的信息。
例如：中国>山东省>济南市>历下区>舜华路街道（国>省>市>区>街道）。

### *Request information*

HTTP protocol: HTTP

Call address: district.market.alicloudapi.com/v3/config/district

Method: GET

<br />
### *Request parameters*

<div>
<table>
<tr>
<th style="width: 20%;">Parameter</th>
<th style="width: 10%;">Location</th>
<th style="width: 10%;">Type</th>
<th style="width: 10%;">Required?</th>
<th style="width: 30%;">Description</th>
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
### *Response information*

#### Response parameter type

JSON

#### Returned result sample

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

#### Abnormal response sample

````

````

<br />
### *Error codes*

<div>
<table>
<tr>
<th style="width: 15%;">Error code</th>
<th style="width: 20%;">Error message</th>
<th style="width: 25%;">Description</th>
</tr>
<tr>
<td>Public error codes</td>
<td>--</td>
<td>For all the API public error codes, see <a href="#pubError">Public error codes</a>.</td>
</tr>
</table>
</div>




## <span id='pubError'>Public errors</span>
### How to obtain public errors
As long as the API request reaches the gateway, the gateway returns the request result message.

You need to check the request header in the retuned result. Returned parameter sample:

	//The unique ID of the request. Once the request reaches API Gateway, API Gateway generates a request ID and returns the request ID to the client through the response header. It is recommended that the request ID be recorded in both the client and backend servers for troubleshooting and tracing.
	X-Ca-Request-Id: 7AD052CB-EE8B-4DFD-BBAF-EFB340E0A5AF

	//Error message returned from API Gateway. When a request fails, API Gateway returns the error message to the client through the response header.
	X-Ca-Error-Message: Invalid Url

	//Debug message returned when the debug mode is enabled. The message may be changed later and is used only for reference at the debugging stage.
	X-Ca-Debug-Info: {"ServiceLatency":0,"TotalLatency":2}

The header in X-Ca-Error-Message basically clarifies the error cause. The X-Ca-Request-Id can be provided to technical support engineers for log searching.
## Public error codes
#### Client errors

Error code|HTTP status code|Meaning|Solution
------|-----------|---|------
Throttled by USER Flow Control|403|Restricted by user flow control|Flow control is triggered because of a high call frequency. Contact the API service provider to increase the flow control limit.
Throttled by APP Flow Control|403|Restricted by APP flow control|Flow control is triggered because of a high call frequency. Contact the API service provider to increase the flow control limit.
Throttled by API Flow Control|403	|Restricted by API flow control|Flow control is triggered because of a high call frequency. Contact the API service provider to increase the flow control limit.
Throttled by DOMAIN Flow Control	|403|	Restricted by access limit on the second-level domain name|The second-level domain name used for API calls can be accessed up to 1,000 times each day.
Throttled by GROUP Flow Control|403|Restricted by group-based flow control|Flow control is triggered because of a high call frequency. Contact the API service provider to increase the flow control limit.
Quota Exhausted	|403|	The call quota is exhausted.	|The call quota you have bought is exhausted.
Quota Expired	|403|	The quota you have bought expires.	|The quota you have bought expires.
User Arrears	|403|	The account is in arrears.	|Recharge your account as soon as possible.
Empty Request Body	|400|	The body is empty.|	Check the content of the request body.
Invalid Request Body	|400	|The body is invalid.	|Check the content of the request body.
Invalid Param Location	|400|	Incorrect parameter location|The location of the request parameter is incorrect.
Unsupported Multipart	|400|	File upload unsupported|The file upload is not supported.
Invalid Url	|400	|The URL is invalid.	|The requested method, path, or environment is incorrect. For more information about error description, see [Invalid URL].
Invalid Domain	|400|	Invalid domain name	|The request's domain name is invalid and the API cannot be found based on the domain name. Contact the API service provider.
Invalid HttpMethod	|400	|Invalid HTTPMethod|The method entered is incorrect.
Invalid AppKey|400|AppKey is invalid or does not exist.	|Check the input AppKey. There is no space on either side of the parameter.
Invalid AppSecret	|400	|App Secret is incorrect |	Check the input App Secret. There is no space on either side of the parameter.
Timestamp Expired|400| The time stamp expires.|Check whether the system time of the request is the standard time.
Invalid Timestamp	|400|	Invalid time stamp|For more information, see the request signature instruction.
Empty Signature	|404|Empty signature|Input the signature string. For more information, see the request signature instruction.
Invalid Signature, Server StringToSign:%s|400|Invalid signature|The signature is invalid. For the error description, see the Invalid Signature.
Invalid Content-MD5|400|	Invalid value of Content-MD5|The request body is empty but its MD5 value is input, or the MD5 value is incorrect. For more information, see the request signature instruction.
Unauthorized	|403|	Unauthorized operation|	The application has no permission to call the API. For the error description, see [Unauthorized].
Nonce Used|400|	SignatureNonce| SignatureNonce is in use.|The SignatureNonce cannot be repeatedly used.
API Not Found|	400	|The API is not found.|The input API address or HttpMethod is incorrect, or the API is offline.

## Server errors (API call)
The following are errors on the API server. If the errors frequently occur, contact the API service provider.

Error code|HTTP status code|Meaning|Solution
------|----------|---|----
Internal Error	|500	|Internal error|It is recommended to try again or contact the API service provider.
Failed To Invoke Backend Service	|500|	Underlying service error|An error occurs in the underlying API service. Try again, and contact the API service provider for a solution if the problem persists.
Service Unavailable|503|	The service is unavailable.	|It is recommended to try again or contact the API service provider.
Async Service	|504	|Backend server timeout|It is recommended to try again or contact the API service provider.

