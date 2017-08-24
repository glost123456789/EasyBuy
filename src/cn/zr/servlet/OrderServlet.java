package cn.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zr.entity.EasyBuyOrder;
import cn.zr.entity.EasyOrderAll;
import cn.zr.entity.EasybuyOrderdetail;
import cn.zr.service.EasybuyOrderService;
import cn.zr.serviceImpl.EasybuyOrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyOrderService orderService=new EasybuyOrderServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		case 1:ManageOrder(request,response);break;//1、管理订单
		case 2:QueryDetail(request,response);break;//2、查询某一个订单
		case 3:ChangeOrderStatus(request,response);break;//ajax异步修改订单状态
		default:
			break;
		}
	}
	public void ManageOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentpage=request.getParameter("currentpage");
		int pageround=3;//每个页面显示的数量
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
		int totalcount=orderService.GetCount();
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		
		request.setAttribute("prepage", prepage);
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		List<EasyOrderAll> detailsss=orderService.getOrderDetailByPage(curpage, pageround);
		request.setAttribute("details", detailsss);
		request.getRequestDispatcher("WEB-INF/manage/order.jsp").forward(request, response);
		
	}
	
	public void QueryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("entityId");
		String userid=request.getParameter("userName");
		List<EasybuyOrderdetail> orderdetails=null;
		if(ids!=null && !ids.equals(""))
		{
			int id=Integer.parseInt(ids.trim());
			orderdetails=orderService.getOrderdetail(id);
			if(orderdetails!=null && orderdetails.size()!=0)
			{
				request.getSession().removeAttribute("orderMsg");
				List<EasyOrderAll> details=orderService.getDetailsByOrder(id);
				request.setAttribute("details", details);
				request.getRequestDispatcher("WEB-INF/manage/order.jsp").forward(request, response);
			}else{
			request.getSession().setAttribute("orderMsg", "订单不存在");
			ManageOrder(request, response);
			}
		}else if(userid!=null && !userid.equals("")){
			List<EasyBuyOrder> orders=orderService.getOrdersByUserName(userid);
			if(orders!=null && orders.size()!=0)
			{
				request.getSession().removeAttribute("orderMsg");
				List<EasyOrderAll> details=orderService.getDetailsByUserName(userid);
				request.setAttribute("details", details);
				request.getRequestDispatcher("WEB-INF/manage/order.jsp").forward(request, response);
			}else{
			request.getSession().setAttribute("orderMsg", "订货人不存在");
			ManageOrder(request, response);
			}
		}
		else{
		request.getSession().setAttribute("orderMsg", "请输入正确的订单和订货人");
		ManageOrder(request, response);
		}
	}
	
	public void ChangeOrderStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("orderid"));
		int status=Integer.parseInt(request.getParameter("status"));
		EasyBuyOrder modifyorder=orderService.getOrderById(id);
		modifyorder.setEo_status(status);
		orderService.ModifyOrder(modifyorder);
		PrintWriter out=response.getWriter();
		switch (status) {
		case 1:out.println("待审核");break;
		case 2:out.print("审核通过");break;
		case 3:out.print("配货");break;
		case 4:out.print("卖家已发货");break;
		case 5:out.print("已收货");break;
		default:
			break;
		}
	}
}
