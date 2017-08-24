package cn.zr.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zr.entity.EasybuyProductCategory;
import cn.zr.service.EasybuyCategoryService;
import cn.zr.serviceImpl.EasybuyCategoryServiceImpl;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyCategoryService easybuyCategoryService=new EasybuyCategoryServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
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
		int option=Integer.parseInt(request.getParameter("option"));
		switch(option)
		{
		case 1:toManageCategory(request, response);;break; //1、进入分类管理界面
		case 2:toAddCategory(request,response);break; //2、进入增进分类界面
		case 3:AddCategory(request, response);break;  //3、增加分类
		case 4:DeleteCategory(request,response);break;//4、删除分类
		case 5:toModifyCategory(request,response);break;//5、进入修改界面
		case 6:ModifyCategory(request, response);break; //6、修改分类
		}
	}
	
	public void toManageCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
		int totalcount=easybuyCategoryService.GetCount();
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		List<EasybuyProductCategory> showcategories=easybuyCategoryService.getSortCategoriesByPage(curpage, pageround);
		request.setAttribute("showcategories", showcategories);
		
		request.getRequestDispatcher("WEB-INF/manage/productClass.jsp").forward(request, response);
	}
	
	public void toAddCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("WEB-INF/manage/productClass-add.jsp").forward(request, response);
	}
	
	public void AddCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		EasybuyProductCategory category=new EasybuyProductCategory();
		category.setEpc_name(request.getParameter("className"));
		category.setEpc_parent_id(Integer.parseInt(request.getParameter("parentId")));
		int i=easybuyCategoryService.AddCategory(category);
		if(i>0)
		{
			request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("WEB-INF/manage/productClass-add.jsp").forward(request, response);
		}
	}
	
	public void DeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id=Integer.parseInt(request.getParameter("id"));
		int i=easybuyCategoryService.DeleteCategory(id);
		if(i>0)
		{
		request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("WEB-INF/manage/productClass.jsp").forward(request, response);
		}
	}
	
	public void toModifyCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id=Integer.parseInt(request.getParameter("id"));
		EasybuyProductCategory modifycategory=easybuyCategoryService.getCategoryById(id);
		request.getSession().setAttribute("modifycategory", modifycategory);
		request.getRequestDispatcher("WEB-INF/manage/productClass-modify.jsp").forward(request, response);
	}
	
	public void ModifyCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		EasybuyProductCategory modifiedcategory=(EasybuyProductCategory) request.getSession().getAttribute("modifycategory");
		int parenId=Integer.parseInt(request.getParameter("parentId"));
		String name=request.getParameter("className");
		modifiedcategory.setEpc_name(name);
		modifiedcategory.setEpc_parent_id(parenId);
		int i=easybuyCategoryService.ModifyCategory(modifiedcategory);
		System.out.println("i="+i);
		if(i>0){
			List<EasybuyProductCategory> categories=easybuyCategoryService.getAllCategory();
			request.getSession().removeAttribute("categories");
			request.getSession().setAttribute("categories", categories);
			request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("WEB-INF/manage/productClass-modify.jsp").forward(request, response);
		}
	}
}
