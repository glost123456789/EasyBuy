package cn.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zr.entity.EasyBuyOrder;
import cn.zr.entity.EasyUser;
import cn.zr.entity.EasybuyAddress;
import cn.zr.entity.EasybuyOrderdetail;
import cn.zr.entity.EasybuyProduct;
import cn.zr.entity.EasybuyShopbus;
import cn.zr.service.EasybuyAddressService;
import cn.zr.service.EasybuyOrderService;
import cn.zr.service.EasybuyShopbusService;
import cn.zr.service.EasybuyproductService;
import cn.zr.serviceImpl.EasyShopbusServiceImpl;
import cn.zr.serviceImpl.EasybuyAddressServiceImpl;
import cn.zr.serviceImpl.EasybuyOrderServiceImpl;
import cn.zr.serviceImpl.EasybuyproductServiceImpl;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyShopbusService shopbusService=new EasyShopbusServiceImpl();
    private EasybuyproductService easybuyproductService=new EasybuyproductServiceImpl();
    private EasybuyAddressService addressService=new EasybuyAddressServiceImpl();
    private EasybuyOrderService orderService=new EasybuyOrderServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingServlet() {
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
		switch(option)
		{
		case 1:toShoppingbus(request,response);break; //1、将物品放入购物车并进入购物车
		case 2:Modify(request,response);break; //2、购物车修改
		case 3:DeleteShop(request,response);break;//3、删除购物车物品
		case 4:ShopBusShopping(request,response);break;//4、购物车购买商品
		case 5:SingleShopping(request,response);break;//5、单个商品购买
		case 6:PostOrder(request,response);break;//6、提交订单
		case 7:AddAddress(request,response);break;//7、增加并设置
		case 8:ShowShopbus(request,response);break;//8、利用ajax实现漂浮购物车
		case 9:SeeShoppingbus(request,response);break;//9、进入购物车界面
		}
	}

	public void toShoppingbus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EasybuyProduct nowproduct=(EasybuyProduct) request.getSession().getAttribute("nowproduct");
		EasyUser myuser=(EasyUser) request.getSession().getAttribute("myuser");
		EasybuyShopbus shop=new EasybuyShopbus();
		shop.setUser_id(myuser.getEu_user_id());
		shop.setProduct_id(nowproduct.getEp_id());
		shop.setProduct_price(nowproduct.getEp_price());
		shop.setProduct_count(1);
		shop.setProduct_file_name(nowproduct.getEp_file_name());
		shopbusService.AddShopbus(shop);
		int shopbuscount=shopbusService.getCountByUserId(myuser.getEu_user_id());
		request.getSession().setAttribute("myshopbuscount",shopbuscount);
		List<EasybuyShopbus> myShopbus=shopbusService.getShopbusByUserId(myuser.getEu_user_id());
		request.setAttribute("myshopbus", myShopbus);
		List<EasybuyShopbus> shopbus=shopbusService.getShopbusByUserId(myuser.getEu_user_id());
		List<EasybuyProduct> shopproduct=new ArrayList<EasybuyProduct>();
		for(int i=0;i<shopbus.size();i++)
		{
			shopproduct.add(easybuyproductService.getProductById(shopbus.get(i).getProduct_id()));
		}
		request.getSession().setAttribute("myshopproduct", shopproduct);
		request.getSession().setAttribute("myshoppingbus", shopbus);
		request.getRequestDispatcher("shopping.jsp").forward(request, response);
	}
	
	public void Modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		int count=Integer.parseInt(request.getParameter("count"));
		float price=Float.parseFloat(request.getParameter("price"));
		EasybuyShopbus modified=shopbusService.getShopById(id);
		modified.setProduct_count(count);
		modified.setProduct_price(price);
		int i=shopbusService.ModifyShopbus(modified);
	}
	
	public void DeleteShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		shopbusService.DeleteShopbus(id);
	}
	
	public void ShopBusShopping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EasyUser myuser=(EasyUser) request.getSession().getAttribute("myuser");
		request.getSession().setAttribute("singleormore", 0);   //0表示购物车购买模式
		List<EasybuyAddress> addresses=addressService.getAddressbyUser(myuser.getEu_user_id());
		request.getSession().setAttribute("addresses", addresses);
		request.getRequestDispatcher("address.jsp").forward(request, response);
	}
	
	public void SingleShopping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int productid=Integer.parseInt(request.getParameter("id"));
		EasyUser myUser=(EasyUser)request.getSession().getAttribute("myuser");
		request.getSession().setAttribute("noworder", productid);
		request.getSession().setAttribute("singleormore", 1);   //1表示单件物品购买
		List<EasybuyAddress> addresses=addressService.getAddressbyUser(myUser.getEu_user_id());
		request.getSession().setAttribute("addresses", addresses);
		request.getRequestDispatcher("address.jsp").forward(request, response);
		
	}
	
	public void PostOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		{
			int singleormore=(int) request.getSession().getAttribute("singleormore");
			//List<EasybuyAddress> addresses=(List<EasybuyAddress>) request.getSession().getAttribute("addresses");
			EasyUser myuser=(EasyUser) request.getSession().getAttribute("myuser");
			String address=request.getParameter("address");
	        java.util.Date nDate = new java.util.Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String sDate = sdf.format(nDate);
	        java.sql.Date now = java.sql.Date.valueOf(sDate);
			if(singleormore==0)
			{//购物车购买
				List<EasybuyShopbus> shopbusorder=shopbusService.getShopbusByUserId(myuser.getEu_user_id());
				List<EasybuyOrderdetail> orderdetails=new ArrayList<EasybuyOrderdetail>();
				float eo_cost=0;
				for(int i=0;i<shopbusorder.size();i++)
				{
					EasybuyOrderdetail detail=new EasybuyOrderdetail();
					eo_cost+=shopbusorder.get(i).getProduct_price();
					detail.setEp_id(shopbusorder.get(i).getProduct_id());
					detail.setEod_quantity(shopbusorder.get(i).getProduct_count());
					detail.setEod_cost(shopbusorder.get(i).getProduct_price());
					orderdetails.add(detail);
				}
				EasyBuyOrder neworder=new EasyBuyOrder();
				neworder.setEo_user_id(myuser.getEu_user_id());
				neworder.setEo_user_name(myuser.getEu_user_name());
				neworder.setEo_user_address(address);
				neworder.setEo_create_time(now);
				neworder.setEo_cost(eo_cost);
				neworder.setEo_status(1);
				orderService.AddOrder(neworder,orderdetails);
				shopbusService.DeleteshopbusByUserId(myuser.getEu_user_id());
				
			}
			else if(singleormore==1){
				int productid=(int) request.getSession().getAttribute("noworder");
				EasybuyProduct orderproduct=easybuyproductService.getProductById(productid);
				EasyBuyOrder order=new EasyBuyOrder();
				order.setEo_user_id(myuser.getEu_user_id());
				order.setEo_user_name(myuser.getEu_user_name());
				order.setEo_user_address(address);
				order.setEo_create_time(now);
				order.setEo_cost(orderproduct.getEp_price());
				order.setEo_status(1);
				List<EasybuyOrderdetail> orderdetails=new ArrayList<EasybuyOrderdetail>();
				EasybuyOrderdetail detail=new EasybuyOrderdetail();
				detail.setEp_id(orderproduct.getEp_id());
				detail.setEod_quantity(1);
				detail.setEod_cost(orderproduct.getEp_price());
				orderdetails.add(detail);
				orderService.AddOrder(order,orderdetails);
			}
			request.getRequestDispatcher("shopping-res.jsp").forward(request, response);
		}
	}
	public void AddAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adaddress=request.getParameter("adaddress");
		EasyUser myuser=(EasyUser) request.getSession().getAttribute("myuser");
		EasybuyAddress address=new EasybuyAddress();
		address.setAd_user_id(myuser.getEu_user_id());
		address.setAd_address(adaddress);
		addressService.Addaddress(address);
	}
	
	public void ShowShopbus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out=response.getWriter();
		EasyUser myUser=(EasyUser) request.getSession().getAttribute("myuser");
		List<EasybuyShopbus> shopbus=shopbusService.getShopbusByUserId(myUser.getEu_user_id());
		String myajaxdata="";
		for(EasybuyShopbus myshop:shopbus)
		{
			EasybuyProduct myProduct=easybuyproductService.getProductById(myshop.getProduct_id());
			myajaxdata=(myajaxdata+"a%a"+myProduct.getEp_file_name()+"b%b"+myProduct.getEp_name()+"b%b"+myshop.getProduct_price());
			
		}
		out.print(myajaxdata);
	}
	
	public void SeeShoppingbus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EasybuyProduct nowproduct=(EasybuyProduct) request.getSession().getAttribute("nowproduct");
		EasyUser myuser=(EasyUser) request.getSession().getAttribute("myuser");
		List<EasybuyShopbus> shopbus=shopbusService.getShopbusByUserId(myuser.getEu_user_id());
		List<EasybuyProduct> shopproduct=new ArrayList<EasybuyProduct>();
		for(int i=0;i<shopbus.size();i++)
		{
			shopproduct.add(easybuyproductService.getProductById(shopbus.get(i).getProduct_id()));
		}
		request.getSession().setAttribute("myshopproduct", shopproduct);
		request.getSession().setAttribute("myshoppingbus", shopbus);
		request.getRequestDispatcher("shopping.jsp").forward(request, response);
	}
}
