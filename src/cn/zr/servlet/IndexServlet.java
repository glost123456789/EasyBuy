package cn.zr.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.zr.entity.EasyUser;
import cn.zr.entity.EasybuyNews;
import cn.zr.entity.EasybuyProduct;
import cn.zr.entity.EasybuyProductCategory;
import cn.zr.method.CookieMethod;
import cn.zr.service.EasybuyCategoryService;
import cn.zr.service.EasybuyNewsService;
import cn.zr.service.EasybuyproductService;
import cn.zr.serviceImpl.EasybuyCategoryServiceImpl;
import cn.zr.serviceImpl.EasybuyNewsServiceImpl;
import cn.zr.serviceImpl.EasybuyproductServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyproductService easybuyproductService=new EasybuyproductServiceImpl();   
    private EasybuyCategoryService easybuyCategoryService=new EasybuyCategoryServiceImpl();
    private EasybuyNewsService newsService=new EasybuyNewsServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		List<EasybuyProduct> products=easybuyproductService.getProductByPage(1, 8);
		//request.setAttribute("products", products);
		HttpSession session=request.getSession();
		session.setAttribute("products", products);
		List<EasybuyProductCategory> categories=easybuyCategoryService.getAllCategory();
		List<EasybuyNews> newses=newsService.getNewsByPage(1, 10);
		request.getSession().setAttribute("newses", newses);
		request.getSession().setAttribute("categories", categories);
		CookieMethod.getCookieProduct(request, response, "product");
		request.getRequestDispatcher("index.jsp").forward(request,response);		
	}

}
