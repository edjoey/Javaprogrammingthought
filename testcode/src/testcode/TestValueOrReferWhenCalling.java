package testcode;

import java.util.Arrays;
import java.util.Date;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.CorpReportListResponse.JsonObject;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import com.taobao.api.internal.parser.json.JsonConverter;

public class TestValueOrReferWhenCalling {
	public static void main(String[] args) throws ApiException  {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=566cc69da782ec33e42541b09b08551f09fbe864eb8008112e994b43887");
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("text");
		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
		text.setContent("测试文本消息 ");
		request.setText(text);
		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
		at.setAtMobiles(Arrays.asList("13261303345"));
		request.setAt(at);
//
//		request.setMsgtype("link");
//		OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//		link.setMessageUrl("https://www.dingtalk.com/");
//		link.setPicUrl("");
//		link.setTitle("时代的火车向前开");
//		link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
//		        "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
//		request.setLink(link);
//
//		request.setMsgtype("markdown");
//		OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//		markdown.setTitle("杭州天气");
//		markdown.setText("#### 杭州天气 @156xxxx8827\n" +
//		        "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//		        "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n"  +
//		        "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//		request.setMarkdown(markdown);
		OapiRobotSendResponse response = client.execute(request);
	
		
	}	 
}