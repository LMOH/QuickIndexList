package com.lmoh.quickindex;

public class Friend implements Comparable<Friend> {
	// 实现接口Comparable，重写方法自己的成员变量与自己比较
	private String name;
	private String pinyin;

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Friend(String name) {
		super();
		this.name = name;
		this.pinyin = PinYinUtil.getPinyin(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//从小到大排序
	@Override
	public int compareTo(Friend another) {
		return getPinyin().compareTo(another.getPinyin());
	}
}
