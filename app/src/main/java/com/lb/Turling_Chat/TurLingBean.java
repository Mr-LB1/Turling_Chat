package com.lb.Turling_Chat;


public class TurLingBean {
	private  String  content;
	/**
	 * 做区别    {@link TL}  {@link IM}
	 */
	private  int flag;
	/**
	 * 这个代表此内容是机器人说的
	 */
	public static final int  TL = 10086;
	/**
	 * 这个代表此内容是用户说的
	 */
	public static final int  IM = 10010;
	
	public TurLingBean() {
		super();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "TurLingBean [content=" + content + ", flag=" + flag + "]";
	}
}
