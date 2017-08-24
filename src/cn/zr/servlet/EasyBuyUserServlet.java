package cn.zr.servlet;

import java.awt.print.Printable;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.print.attribute.standard.PrinterLocation;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.oracle.jrockit.jfr.RequestableEvent;
import com.sun.glass.ui.Application;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import cn.zr.entity.EasyUser;
import cn.zr.entity.EasybuyShopbus;
import cn.zr.service.EasyUserService;
import cn.zr.service.EasybuyShopbusService;
import cn.zr.serviceImpl.EasyShopbusServiceImpl;
import cn.zr.serviceImpl.EasyUserServiceImpl;

/**
 * Servlet implementation class EasyBuyUserServlet
 */
@WebServlet("/EasyBuyUserServlet")
public class EasyBuyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasyUserService easyUserService=new EasyUserServiceImpl();
    private EasybuyShopbusService shopbusService=new EasyShopbusServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EasyBuyUserServlet() {
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
		case 1: request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);break;    //1、转到注册页面
		case 2: IncreaseUser(request, response);break;     //2、实现增加注册用户
		case 3: request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);break;    //3、注册完之后转到注册成功
		case 4: Login(request, response);break;    //4、用户登录登陆后转到主页
		case 5: Logout(request, response);break;   //5、用户注销
		case 6: request.getRequestDispatcher("WEB-INF/manage/index.jsp").forward(request, response);break; //6、进入后台管理
		case 7: ManageUser(request, response);break; //7、进入用户管理
		case 8: DeleteUser(request, response);break;//8、删除用户
		case 9: toModifyUserPage(request,response);break;// 9、进入修改用户界面
		case 10: doModifyUser(request, response);break; //10、修改用户数据
		case 11: AutoLayout(request,response);break;//11、ajax退出之前登录
		case 12:AutoDeleteUser(request, response);break;
		}

	}

	
	public void IncreaseUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		EasyUser easyUser=new EasyUser();
		easyUser.setEu_user_name(request.getParameter("userName"));
		easyUser.setEu_user_id(request.getParameter("userId"));
		easyUser.setEu_password(request.getParameter("password"));
		easyUser.setEu_sex(request.getParameter("sex"));
		easyUser.setEu_birthday(Date.valueOf(request.getParameter("birthday")));
		easyUser.setEu_identity_code(request.getParameter("identityCode"));
		easyUser.setEu_email(request.getParameter("email"));
		easyUser.setEu_mobile(request.getParameter("mobile"));
		easyUser.setEu_address(request.getParameter("address"));
		easyUser.setEu_status(0);
		easyUser.setEu_login(0);
		int i=easyUserService.IncreaseUser(easyUser);
		if(i>0)
		{
			response.sendRedirect("reg-result.jsp");
		}
		else{
			request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
		}
	}
	
	public void Login(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		String id=request.getParameter("userId");
		String pwd=request.getParameter("password");
		String safecode=request.getParameter("code");
		String checkcode=(String) request.getSession().getAttribute("validateCode");
		if(!checkcode.equals(safecode))
		{
			request.setAttribute("checkcodeMsg", "验证码输入错误！");
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}else{
			EasyUser easyUser=easyUserService.LoginAndGetUser(id, pwd);
			if(easyUser==null)
			{
				request.setAttribute("checkcodeMsg", "账号或密码错误！");
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
			}
			else
			{
				request.getSession().setAttribute("myuser", easyUser);
				String SameLoginErr=(String) request.getSession().getAttribute("SameLoginErr");
				request.setAttribute("checkcodeMsg", SameLoginErr);
				if (SameLoginErr==null || SameLoginErr.equals("")) {
					//获取该用户的购物车
					int shopbuscount=shopbusService.getCountByUserId(easyUser.getEu_user_id());
					List<EasybuyShopbus> myShopbus=shopbusService.getShopbusByUserId(easyUser.getEu_user_id());
					request.setAttribute("myshopbus", myShopbus);
					request.getSession().setAttribute("myshopbuscount",shopbuscount);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					request.setAttribute("myuser", easyUser);
					request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
				}
			}
		}
		
	}
	
	public void Logout(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		EasyUser myUser=(EasyUser) request.getSession().getAttribute("myuser");
		int i=easyUserService.Logout(myUser.getEu_user_id());
		request.getSession().removeAttribute("myuser");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	public void ManageUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String currentpage=request.getParameter("currentpage");
		int pageround=5;//每个页面显示的数量
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
		int totalcount=easyUserService.GetCount();
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
 		request.setAttribute("prepage", prepage);
		List<EasyUser> userlist=easyUserService.getUsersByPage(curpage, pageround);
		request.setAttribute("userlist", userlist);
		request.getRequestDispatcher("WEB-INF/manage/user.jsp").forward(request, response);
	}
	
	public void DeleteUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String userid=request.getParameter("userid");
		easyUserService.DeleteUserById(userid);
		ManageUser(request, response);
	}
	
	public void toModifyUserPage(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String userid=request.getParameter("userid");
		EasyUser user=easyUserService.getUserById(userid);
		request.getSession().setAttribute("modifyuser", user);
		request.getRequestDispatcher("WEB-INF/manage/user-modify.jsp").forward(request,response);
	}
	
	public void doModifyUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		EasyUser modifyuser=(EasyUser) request.getSession().getAttribute("modifyuser");
		modifyuser.setEu_user_id(request.getParameter("userId"));
		modifyuser.setEu_password(request.getParameter("password"));
		modifyuser.setEu_user_name(request.getParameter("userName"));
		modifyuser.setEu_birthday(Date.valueOf(request.getParameter("birthday")));
		modifyuser.setEu_mobile(request.getParameter("mobile"));
		modifyuser.setEu_address(request.getParameter("address"));
		int i=easyUserService.ModifyUserById(modifyuser);
		if(i>0)
		{
			 request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}
		else{
			toModifyUserPage(request, response);
		}
		
	}
	
	public void AutoLayout(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		List<EasyUser> myusers=(List<EasyUser>) request.getSession().getServletContext().getAttribute("myusers");
		String	myuser=request.getParameter("myuser");
		for(int i=0;i<myusers.size();i++)
		{
			if(myusers.get(i).getEu_user_id().equals(myuser))
			{
				myusers.remove(i);
				break;
			}
		}
		request.getSession().removeAttribute("myuser");
	}
	
	public void AutoDeleteUser(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		request.getSession().removeAttribute("myuser");
	}
}
