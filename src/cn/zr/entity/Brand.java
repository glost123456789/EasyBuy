package cn.zr.entity;

import java.util.ArrayList;
import java.util.List;

public class Brand {
	private  static List<String> brand=new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{add("华为"); add("三星");add("苹果");add("得力");add("长城");add("德芙");add("联想");add("奔驰");add("奥迪");
																add("优衣库");add("耐克");add("乔丹");add("唐狮");add("阿迪达斯");add("360°");add("新百伦");add("捷安特");}};
	public static String getBrandById(int brandid){
		return brand.get(brandid-1);
	}
}
