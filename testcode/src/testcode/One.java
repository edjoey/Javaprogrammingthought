package testcode;

import java.io.BufferedReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class One {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("key", "e75c14b864eb47ad9eb228d39866200a");
	    querys.put("keywords", "");
	    querys.put("subdistrict", "2");
	    
	    StringBuilder strBuilder = new StringBuilder();
	    for (Map.Entry<String, String> e : querys.entrySet()) {
	    	strBuilder.append(e.getKey()).append("=").append(e.getValue()).append("&");
        }
	    
	
		
	    //System.out.print(querys.toString());
	    String HTTPMethod = "POST";
	    String Accept = "application/json";
	    String Url = "http://qyocrbl.market.alicloudapi.com/clouds/ocr/businessLicense";
	    String ContentMD5 = "";
	    String ContentType = "";
	    String Datee = "";
	    String Headers = "X-Ca-Key,X-Ca-Timestamp,X-Ca-Nonce";
	    
	    
	    String stringToSign=
	    		HTTPMethod + "\n" +
	    		Accept + "\n" +        
	    		ContentMD5 + "\n"+
	    		ContentType + "\n" +
	    		Datee + "\n" +
	    		Headers +Url;
	    System.out.println(stringToSign);
	    
	    String oDate = "2018-09-02";
	 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date t1 = sdf.parse(oDate);
	    System.out.println(t1);
	    System.out.println(sdf.format(t1));
	    
	    
	    Integer chanagerFailer = 500;
	    Integer result = null;

	    if (chanagerFailer.equals(result)) {
	    	 System.out.println(Integer.toString(2));
		}
	   
	    //页面
	    List<String> lista = new  ArrayList<>();
	    lista.add("A");
	    lista.add("D");
	    lista.add("B");
	    //DB
	    List<String> listb = new  ArrayList<>();
	    listb.add("A");
	    listb.add("G");
	    listb.add("B");
	    lista.retainAll(listb);
	    System.out.println(Arrays.asList(lista));

	    BigDecimal dss = new BigDecimal(300);
	    BigDecimal newdss = dss.multiply(new BigDecimal(-1));
	    System.out.println(newdss);
	   
	    String ky = null;
		if( ky.equals(null)) {
			  System.out.println(newdss);
		}
	}
	

	@SuppressWarnings("deprecation")
	public static Date getLastDayOfMonth(Date date) {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTime(date);
		int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		return lastDate;
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

	public static String getOneLine(String withr) {
		StringReader sr = new StringReader(withr);
		BufferedReader br = new BufferedReader(sr);
		String line = null;
		StringBuffer temp = new StringBuffer();
		try {
			while ((line = br.readLine()) != null) {
				temp.append(line);
			}
			br.close();
			sr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp.toString();
	}
}
