package cn.zr.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zr.entity.EasybuyNews;
import cn.zr.service.EasybuyNewsService;
import cn.zr.serviceImpl.EasybuyNewsServiceImpl;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyNewsService newsService=new EasybuyNewsServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
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
		case 1:toNews(request, response);break; //1、进入新闻页
		case 2:ManageNews(request,response);break;//2、进入管理新闻界面
		case 3:toModifyNews(request,response);break;//3、进入修改新闻界面
		case 4:ModifyNews(request, response);break;//4、修改新闻
		case 5:DeleteNews(request,response);break;//5、删除新闻
		case 6:toAddNews(request,response);break;//6、增加新闻页面
		case 7:AddNews(request, response);break;//7、增加新闻
		}
	}
	
	public void toNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		EasybuyNews news=newsService.getNewsById(id);
		request.setAttribute("news", news);
		request.getRequestDispatcher("news-view.jsp").forward(request, response);
	}
	public void ManageNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int totalcount=newsService.GetCount();
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		
		request.setAttribute("prepage", prepage);
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		List<EasybuyNews> newses=newsService.getNewsByPage(curpage, pageround);
		request.setAttribute("newses", newses);
		request.getRequestDispatcher("WEB-INF/manage/news.jsp").forward(request, response);
	}
	
	public void toModifyNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		EasybuyNews news=newsService.getNewsById(id);
		request.setAttribute("news", news);
		request.getRequestDispatcher("WEB-INF/manage/news-modify.jsp").forward(request, response);
	}
	public void ModifyNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		EasybuyNews news=newsService.getNewsById(id);
		news.setEn_title(title);
		news.setEn_content(content);
		int i=newsService.Modify(news);
		if(i>0)
		{
			request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}
	}
	public void DeleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		int i=newsService.DeleteNews(id);
		if(i>0)
		{
			ManageNews(request, response);
		}
		
	}
	public void toAddNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/manage/news-add.jsp").forward(request, response);
	}
	public void AddNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		EasybuyNews news=new EasybuyNews();
		news.setEn_title(title);
		news.setEn_content(content);
		int i=newsService.AddNews(news);
		if(i>0)
		{
			request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}	
	}
}
