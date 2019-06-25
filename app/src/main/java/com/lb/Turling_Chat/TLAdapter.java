package com.lb.Turling_Chat;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TLAdapter extends BaseAdapter {
	private List<TurLingBean> tls;
	private Context context;

	public TLAdapter(List<TurLingBean> tls, Context context) {
		this.tls = tls;
		this.context = context;
	}
	/**
	 * lv的长度
	 */
	public int getCount() {
		return tls.size();
	}
	/**
	 * 每一个Item View 对应的数据元素
	 */
	public Object getItem(int position) {
		return tls.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	/**
	 * 根据数据产生对应的View convertView. 其实就是每一个Item
	 */
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		// 初次进来
		if (convertView == null) {
			// 新产生
			// 所有的 View 都必须要有一个Context :parent.getContext()
			convertView = LayoutInflater.from(context).inflate(
					R.layout.lv_item_comu, null);
			holder = new ViewHolder();
			// 找控件
			holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			// holder和View绑定
			convertView.setTag(holder);
		} else {
			// 上面已经绑定，就直接拿
			holder = (ViewHolder) convertView.getTag();
			                    
		}
		// 加载数据
		TurLingBean turLingBean = tls.get(position);
		// 假如是机器人
		if (turLingBean.getFlag() == TurLingBean.TL) {
			holder.iv_icon.setBackgroundResource(R.drawable.tl);
			holder.tv_content.setBackgroundColor(Color.parseColor("#FFFFFF"));
		} else {
			holder.iv_icon.setBackgroundResource(R.drawable.ic_launcher);
			holder.tv_content.setBackgroundColor(Color.parseColor("#00FF00"));
		}
		// 加载对话内容
		holder.tv_content.setText(turLingBean.getContent());

		return convertView;
	}
	/**
	 * 代表每一个item View 对象
	 */
	class ViewHolder {
		public ImageView iv_icon;
		public TextView tv_content;
	}

}
