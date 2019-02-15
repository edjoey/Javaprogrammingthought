# OCR_营业执照
---------------------------------------
<br />

营业执照识别

## API list

<div>
    <table>
        <tr>
            <th >API name</th>
            <th >Authentication method</th>
            <th >Description</th>
        </tr>
            <tr>
                <td>OCR_营业执照识别</td>
                <td>APP</td>
                <td>传入营业执照照片，识别返回营业执照各项内容。适用于新旧各种营业执照</td>
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



## API name: OCR_营业执照识别

### *Description*

传入营业执照照片，识别返回营业执照各项内容。适用于新旧各种营业执照

### *Request information*

HTTP protocol: HTTP

Call address: qyocrbl.market.alicloudapi.com/clouds/ocr/businessLicense

Method: POST

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
</table>
</div>

<br />
### *Request Body description (non-Form)*
{
	"imageBase64": "/9j/4AAQSkZJRgABAQAASABIAAD/4QBYRXhpZgAATU0AKgAAAAgAAgESA......"
}
<br />
### *Response information*

#### Response parameter type

JSON

#### Returned result sample

````
{
    "code": 0,
    "message": "操作成功",
    "data": {
        "name": "杭州云桔科技有限公司",
        "legalperson": "陆**",
        "regaddress": "浙江省杭州市西湖区文三路****号",
        "regdate": "2017年09月01日",
        "canceldate": "长期",
        "creditno": "91330****E79 (1/1)",
        "regno": "无"
    }
}
````

#### Abnormal response sample

````
{
    "code": 1,
    "message": "系统繁忙",
    "data": null
}
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
<td>0</td>
<td>操作成功</td>
<td>成功</td>
</tr>
<tr>
<td>1</td>
<td>操作失败</td>
<td>调用接口失败</td>
</tr>
<tr>
<td>10002</td>
<td>系统繁忙</td>
<td>系统繁忙，请稍后重试或联系客服人员</td>
</tr>
<tr>
<td>10001</td>
<td>其他错误</td>
<td>其他错误，请联系客服人员</td>
</tr>
<tr>
<td>10004</td>
<td>用户信息不存在</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10005</td>
<td>调用用户信息失败</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10006</td>
<td>用户状态不正确</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10007</td>
<td>订购信息不存在</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10008</td>
<td>调用订购信息失败</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10009</td>
<td>订购状态不正确</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10010</td>
<td>商品信息不存在</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10011</td>
<td>调用商品信息失败</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10012</td>
<td>商品暂不可用</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10013</td>
<td>调用通道信息失败</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10014</td>
<td>商品未配通道</td>
<td>请联系客服人员</td>
</tr>
<tr>
<td>10018</td>
<td>通道调用失败</td>
<td>通道调用失败</td>
</tr>
<tr>
<td>10019</td>
<td>请求重复</td>
<td>请求重复</td>
</tr>
<tr>
<td>40001</td>
<td>参数错误</td>
<td>返回具体的参数错误信息，请根据信息核查参数</td>
</tr>
<tr>
<td>40003</td>
<td>权限不足</td>
<td>权限不足</td>
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

