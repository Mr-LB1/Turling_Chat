package com.lb.Turling_Chat;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.lb.Turling_Chat.DownLoaderTask.DownLoaderLister;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,
		DownLoaderLister {
	public final static String TL_API = "http://www.tuling123.com/openapi/api?key=aa35412ad44a482a8030899686355ca9&info=";
	private ListView lv_comu;
	private EditText et_content;
	private Button btn_send;
	private List<TurLingBean> tls;
	private TLAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_comu = (ListView) findViewById(R.id.lv_comu);
		et_content = (EditText) findViewById(R.id.et_content);
		btn_send = (Button) findViewById(R.id.btn_send);
		// 设置监听器
		btn_send.setOnClickListener(this);
		tls = new ArrayList<TurLingBean>();
		adapter = new TLAdapter(tls, this);
		// LV要设置适配器
		lv_comu.setAdapter(adapter);

	}
	public void onClick(View v) {
         //获取内容
		 String  content =et_content.getText().toString();
		 //封装数据
		 TurLingBean bean= new TurLingBean();
		 bean.setFlag(TurLingBean.IM);
		 bean.setContent(content);
		 tls.add(bean);
		 //告诉LV数据更新
		 adapter.notifyDataSetChanged();
		 //记得清空数据
		 et_content.setText("");
		 //拿着数据问Turling
		 DownLoaderTask downLoaderTask= new DownLoaderTask();
		 //记得设置数据回调
		 downLoaderTask.setOnDownLoaderLister(this);
         downLoaderTask.execute(TL_API + URLEncoder.encode(content));    		 
		
	}
	public void onReady() {
     
	}
	public void success(String result) {
		try {
			JSONObject object = new JSONObject(result);
			String content= object.getString("text");
			//封装数据
			 TurLingBean bean= new TurLingBean();
			 bean.setFlag(TurLingBean.TL);
			 bean.setContent(content);
			 tls.add(bean);
			 //告诉LV数据更新
			 adapter.notifyDataSetChanged();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}  
	}

	public void failed() {
           Toast.makeText(this, "暂不提供服务~~~", Toast.LENGTH_LONG).show();
	}
}
