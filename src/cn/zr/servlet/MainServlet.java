package cn.zr.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import cn.zr.entity.Brand;
import cn.zr.entity.EasybuyProduct;
import cn.zr.method.CookieMethod;
import cn.zr.service.EasybuyproductService;
import cn.zr.serviceImpl.EasybuyproductServiceImpl;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyproductService productService=new EasybuyproductServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int option=Integer.parseInt(request.getParameter("option"));
		switch (option) {
		case 1: ShowBrand(request,response);break;  //1、品牌页面
		case 2: ChangePage(request,response);break; //2、换页
		case 3: QueryBrand(request,response);break; //3、搜索某一品牌下的商品
		case 4: QueryPromotive(request,response);break;//4、查询促销商品
		case 5: AutoLayout(request,response);break;//5、自动退出登录
		default:
			break;
		}
	}
	
	public void ShowBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentpage=request.getParameter("currentpage");
		int pageround=8;//每个页面显示的数量
		int curpage=-1;//当前页
		if(currentpage==null)
		{
			curpage=1;
		}else {
			curpage=Integer.parseInt(currentpage);
		}

		if(currentpage!=null && !currentpage.equals(""))
		{
			curpage=Integer.parseInt(currentpage);
		}
		int totalcount=productService.GetCountByBrand(null);
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		request.setAttribute("prepage", prepage);
		List<EasybuyProduct> products=productService.GetproductsByBrandAndPage(null, curpage, pageround);
		request.setAttribute("products", products);
		CookieMethod.getCookieProduct(request, response, "product");
		request.getRequestDispatcher("product-list-pinpai.jsp").forward(request, response);
	}

	public void ChangePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String thisbrand=(String) request.getSession().getAttribute("thisbrand");
		if(thisbrand==null || thisbrand.equals(""))
		{
			ShowBrand(request, response);
		}
		String currentpage=request.getParameter("currentpage");
		int pageround=8;//每个页面显示的数量
		int curpage=-1;//当前页
		if(currentpage==null)
		{
			curpage=1;
		}else {
			curpage=Integer.parseInt(currentpage);
		}

		if(currentpage!=null && !currentpage.equals(""))
		{
			curpage=Integer.parseInt(currentpage);
		}
		int totalcount=productService.GetCountByBrand(thisbrand);
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		request.setAttribute("prepage", prepage);
		List<EasybuyProduct> products=productService.GetproductsByBrandAndPage(thisbrand, curpage, pageround);
		request.setAttribute("products", products);
		CookieMethod.getCookieProduct(request, response, "product");
		request.getRequestDispatcher("product-list-pinpai.jsp").forward(request, response);
		
	}

	public void QueryBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String brandid=request.getParameter("brandid");
		String thisbrand="";
		if(brandid!=null && !brandid.equals(""))
		{
			thisbrand=Brand.getBrandById(Integer.parseInt(brandid));
		}else{
			thisbrand=request.getParameter("querybrand");
		}
		String currentpage=request.getParameter("currentpage");
		int pageround=8;//每个页面显示的数量
		int curpage=-1;//当前页
		if(currentpage==null)
		{
			curpage=1;
		}else {
			curpage=Integer.parseInt(currentpage);
		}

		if(currentpage!=null && !currentpage.equals(""))
		{
			curpage=Integer.parseInt(currentpage);
		}
		int totalcount=productService.GetCountByBrand(thisbrand);
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		request.setAttribute("prepage", prepage);
		List<EasybuyProduct> products=productService.GetproductsByBrandAndPage(thisbrand, curpage, pageround);
		request.setAttribute("products", products);
		CookieMethod.getCookieProduct(request, response, "product");
		request.getRequestDispatcher("product-list-pinpai.jsp").forward(request, response);
	}

	public void QueryPromotive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryid=request.getParameter("promotecategory");
		int promotecategory=0;
		if(categoryid!=null && !categoryid.equals(""))
		{
			promotecategory=Integer.parseInt(categoryid);
		}
		String currentpage=request.getParameter("currentpage");
		int pageround=8;//每个页面显示的数量
		int curpage=-1;//当前页
		if(currentpage==null)
		{
			curpage=1;
		}else {
			curpage=Integer.parseInt(currentpage);
		}

		if(currentpage!=null && !currentpage.equals(""))
		{
			curpage=Integer.parseInt(currentpage);
		}
		int totalcount=productService.getPromotiveCountByCategory(promotecategory);
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		request.setAttribute("prepage", prepage);
		List<EasybuyProduct> products=productService.GetPromotiveByCategoryAndPage(promotecategory,curpage, pageround);
		request.setAttribute("products", products);
		CookieMethod.getCookieProduct(request, response, "product");
		request.getRequestDispatcher("product-list-cuxiao.jsp").forward(request, response);
	}
	
	public void AutoLayout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("哈哈哈哈哈哈哈哈");
	}
}
