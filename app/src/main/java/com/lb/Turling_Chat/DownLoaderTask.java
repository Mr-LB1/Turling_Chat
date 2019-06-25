package com.lb.Turling_Chat;
import android.os.AsyncTask;
import android.text.TextUtils;

public class DownLoaderTask extends AsyncTask<String, Void, String> {
	/**
	 * 第二步
	 */
	private DownLoaderLister  downLoaderLister;
	/**
	 * 第一步
	 */
	public interface  DownLoaderLister{
		 void onReady();
		 void success(String result);
		 void failed();
	}
	/**
	 *第三步 
	 */
	public  void  setOnDownLoaderLister(DownLoaderLister downLoaderLister){
		this.downLoaderLister = downLoaderLister;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		 downLoaderLister.onReady();
	}
	
	@Override
	protected String doInBackground(String... params) {
		return HttpTool.getStringFromURL(params[0], "UTF-8");
	}
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(!TextUtils.isEmpty(result)){
			downLoaderLister.success(result);
		}else{
            downLoaderLister.failed();
		}
		//任何情况都要取消
	}
}
