package testcode;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;

import org.omg.CORBA.Request;
public class One {


	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
	        
//	        Integer batchCount = 20; //每批插入数目
//	        Integer batchLastIndex = batchCount;
//	        for (int index = 0; index < list.size(); ) {
//	            if (batchLastIndex >= list.size()) {
//	                batchLastIndex = list.size();
//	                System.out.println(Arrays.toString(list.subList(index, batchLastIndex).toArray()));
//	                System.out.println("------------------------");
//	                break;
//	            } else {
//	            	System.out.println(Arrays.toString(list.subList(index, batchLastIndex).toArray()));
//	            	System.out.println("BB------------------------");
//	                index = batchLastIndex;// 设置下一批下标
//	                batchLastIndex = index + (batchCount - 1);
//	                System.out.println(batchCount);
//	                System.out.println(batchLastIndex);
//	            }
//	        }
		 String date = "2019-07-15T16:00:00.000Z";
		 date = date.replace("Z", " UTC");//是空格+UTC
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//格式化的表达式
		 Date d = format.parse(date);
		 System.out.print(d);
	}
	
	private static String generateExportCode(Integer num) {
		Long  timeMillis = System.currentTimeMillis();
		Integer beginIndex = timeMillis.toString().length()-3;
		Integer endIndex   = timeMillis.toString().length();
		String str = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int len = str.length();
        char []source = str.toCharArray();
        char []result = new char[len];
        int count;
        int rand_pos;
        for(int i=0;i<len;i++){
            count=0;
            rand_pos = (int)(Math.random()*(len-i)+1);
            for(int j=0;j<len;j++){
                if(source[j]!=' '){
                    count++;
                }
                if(count==rand_pos){
                    result[i]=source[j];
                    source[j]=' ';
                    break;
                }
            }
        }
        
        return new String(result).substring(0, num)+timeMillis.toString().substring(beginIndex, endIndex);
	}
    
	
    public static long getPreviousMonthLastDay() {
    	   Calendar cDay1 = Calendar.getInstance();
           cDay1.setTime(new Date());
           cDay1.set(Calendar.MILLISECOND, 0);
           return cDay1.getTimeInMillis();
    }

    public static void printList(List<Object> sourList){
        for(int j=0;j<sourList.size();j++){
            System.out.println(sourList.get(j));
        }
        System.out.println("------------------------");
    }
	
	/**
	 * 得到day的起始时间点。
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到day的终止时间点.
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}
}
 class DO{
	private String name;
	private String id;
	private BigDecimal am;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getAm() {
		return am;
	}
	public void setAm(BigDecimal am) {
		this.am = am;
	}
	
}