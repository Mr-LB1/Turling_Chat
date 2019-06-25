package com.lb.Turling_Chat;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTool {
	/**
	 * 关闭流
	 * 
	 * @param is
	 * @param os
	 */
	public static void closeStream(InputStream is, OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 网络获取数据
	 * 
	 * @param urlStr
	 * @return
	 */
	public static byte[] getBytesFromHttp(String urlStr) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			int code = conn.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				bis = new BufferedInputStream(conn.getInputStream());
				baos = new ByteArrayOutputStream();
				int len = 0;
				byte[] buff = new byte[1024];
				while ((len = bis.read(buff)) != -1) {
					baos.write(buff, 0, len);
				}
				// 最后返回数据
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStream(bis, baos);
		}
		return null;
	}

	public static String getStringFromURL(String urlStr, String charset) {
		byte[] bytes = getBytesFromHttp(urlStr);
		
		try {
			String str = new String(bytes, charset);
			return str;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
