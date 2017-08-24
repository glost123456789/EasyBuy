package cn.zr.method;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zr.entity.EasybuyProduct;
import cn.zr.service.EasybuyproductService;
import cn.zr.serviceImpl.EasybuyproductServiceImpl;

public class CookieMethod {
	private static EasybuyproductService productService=new EasybuyproductServiceImpl();
	/*
	 * 获取cookie数据
	 */
	public  static String[] getCookie(HttpServletRequest request, HttpServletResponse response, String name)
	{
		Cookie[] cookies=request.getCookies();
		String lastproducts=null;
		if(cookies!=null){
		for(Cookie cookie:cookies)
		{
			if(cookie.getName().equals(name))
			{
				lastproducts=cookie.getValue();	
			}
			if(lastproducts!=null){
			return lastproducts.split("%2C");
			}
		}
		}
		return null;
	}
	/*
	 * 获取cookie中的最近商品
	 */
	public static void getCookieProduct(HttpServletRequest request, HttpServletResponse response, String name)
	{
		String[] idString=getCookie(request, response, name);
		if(idString!=null){
 		List<EasybuyProduct> mylastproducts=new ArrayList<EasybuyProduct>(); 
		for(int i=0;i<idString.length;i++)
		{
			if(i>=1){
				break;
			}
			mylastproducts.add(productService.getProductById(Integer.parseInt(idString[i])));
		}
		request.setAttribute("lastproducts", mylastproducts);
		}
	}
}
