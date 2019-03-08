package netty;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class IOClient {
	
	public static void main(String[] args) {
		   new Thread(() -> {
	            try {
	                Socket socket = new Socket("127.0.0.1", 8000);
	                while (true) {
	                    try {
	                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
	                        socket.getOutputStream().flush();
	                        Thread.sleep(2000);
	                    } catch (Exception e) {
	                    }
	                }
	            } catch (IOException e) {
	            }
	        }).start();
	    }
	
//	客户端的代码相对简单，连接上服务端8000端口之后，每隔2秒，我们向服务端写一个带有时间戳的 "hello world"。
//	IO编程模型在客户端较少的情况下运行良好，但是对于客户端比较多的业务来说，单机服务端可能需要支撑成千上万的连接，IO模型可能就不太合适了，我们来分析一下原因。
//	上面的demo，从服务端代码中我们可以看到，在传统的IO模型中，每个连接创建成功之后都需要一个线程来维护，每个线程包含一个while死循环，那么1w个连接对应1w个线程，继而1w个while死循环，这就带来如下几个问题：
//
//	线程资源受限：线程是操作系统中非常宝贵的资源，同一时刻有大量的线程处于阻塞状态是非常严重的资源浪费，操作系统耗不起
//	线程切换效率低下：单机cpu核数固定，线程爆炸之后操作系统频繁进行线程切换，应用性能急剧下降。
//	除了以上两个问题，IO编程中，我们看到数据读写是以字节流为单位，效率不高。
//	为了解决这个问题，JDK在1.4以后提出了NIO.
}


