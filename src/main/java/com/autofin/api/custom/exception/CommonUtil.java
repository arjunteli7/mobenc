package com.autofin.api.custom.exception;

import java.io.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.databind.ObjectMapper;

//import sun.misc.BASE64Encoder;
public class CommonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	 
	 public static Date removeTimeFromDate(Date pDate) {
	        Date rDate = null;
	        try {
	            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	            rDate = fmt.parse(fmt.format(pDate));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return rDate;
	    }
	 
	public static LocalDate dateConversion(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate parseDate; 
		parseDate= LocalDate.parse(dateString, formatter);
		return parseDate;
	}
	public static String getFileExtenssion(String filename) {
		String extenssion = null;
		if (filename != null || !filename.isEmpty() || !filename.contains(".")) {
			int lastIndexOf = filename.lastIndexOf(".");
			extenssion = filename.substring(lastIndexOf + 1);
		} else {

		}
		return extenssion;
	}
	public static List<Object[]> resultSetToListObjectArray(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Object[]> list = new ArrayList<Object[]>();
        while (rs.next()) {
            Object row[] = new Object[columns + 1];
            for (int i = 0; i < columns; i++) {
                row[i] = rs.getObject(i + 1);
            }
            list.add(row);
        }

        return list;
    }
	public static boolean isStringNotNull(String value) {
		boolean flag = false;
		if (value != null && !value.equals("")) {
			flag = true;
		}
		return flag;
	}

	public static boolean isNumber(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static java.sql.Date getSqlDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(dateFormat.parse(date).getTime());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	@SuppressWarnings("unused")
	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T jsonToJavaObject(String formData, final Class<?> classType) {
		T t = null;
		try {
			t = (T) new ObjectMapper().readValue(formData, classType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public static <T> T mapToPojo(Object map, final Class<?> classType) {
		T t = null;
		try {

			String writeValueAsString = new ObjectMapper().writeValueAsString(map);
			t = (T) new ObjectMapper().readValue(writeValueAsString, classType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return t;
	}

	public static Map resultSetUniqueEntityMap(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		HashMap row = new HashMap(columns);
		int count = 0;
		while (rs.next()) {
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));
				if (rs.getObject(i) != null) {
					count++;
				}
			}
		}
		if (count <= 0)
			row = null;
		return row;
	}
	
	public static List resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList();
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }
	
	@SuppressWarnings("unchecked")
	public static <T> T objectToPojo(Object map, final Class classType) {
        T t = null;
        try {

            String writeValueAsString = mapper.writeValueAsString(map);
            t = (T) mapper.readValue(writeValueAsString, classType);
        } catch (Exception ex) {
        	
            ex.printStackTrace();
        }
        return t;
    }
	public static Date convertToDate(String dateString) throws java.text.ParseException {
        Date formattedDate = null;
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            formattedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
	
	 public static java.sql.Date getSqlDateS(String date) throws ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        java.sql.Date sqlDate = null;
			try {
				sqlDate = new java.sql.Date(dateFormat.parse(date).getTime());
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
	        return sqlDate;
	    }
	 public static java.sql.Date getSqlDate1(String date) throws ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        java.sql.Date sqlDate = null;
			try {
				sqlDate = new java.sql.Date(dateFormat.parse(date).getTime());
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
	        return sqlDate;
	    }

	 public static double round(double value, int numberOfDigitsAfterDecimalPoint) {
	        BigDecimal bigDecimal = new BigDecimal(value);
	        bigDecimal = bigDecimal.setScale(numberOfDigitsAfterDecimalPoint,
	                BigDecimal.ROUND_HALF_UP);
	        return bigDecimal.doubleValue();
	    }
	 
	  private static final String ALGO = "AES";
	  private static final byte[] keyValue = 
	      new byte[] { 'A', 'b', 'c', 'd', 'e', 'f', 'g',
	      'h', 'i', 'j', 'k','l', 'm', 'n', 'o', 'p'};

	  public static String encrypt(String Data) throws Exception {
		SecretKeySpec key = generateKey();
	    Cipher c = Cipher.getInstance(ALGO);
	    c.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
	    byte[] encVal = c.doFinal(Data.getBytes());
//	    String encryptedValue = new BASE64Encoder().encode(encVal);
	    String encryptedValue = java.util.Base64.getEncoder().encodeToString(encVal);
	    return encryptedValue;
	  }
	  private static SecretKeySpec generateKey() throws Exception {
	    SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);
	    return key;
	  }
	  
	  public static char[] generatorOTP() { 
	    Random obj = new Random(); 
	    char[] otp = new char[6]; 
	    for (int i=0; i<6; i++) { 
	      otp[i]= (char)(obj.nextInt(10)+48); 
	    } 
	    return otp; 
	  } 
	 
}
