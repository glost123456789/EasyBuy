package cn.zr.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.Comment;

import cn.zr.entity.EasyUser;
import cn.zr.entity.EasybuyComment;
import cn.zr.service.EasybuyCommentService;
import cn.zr.serviceImpl.EasybuyCommentServiceImpl;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyCommentService commentService=new EasybuyCommentServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		case 1:toComment(request,response);break;//1、进入评论页面
		case 2:Comment(request,response);break;//2、评论
		case 3:toManageComment(request,response);break;//3、进入管理评论页面
		case 4:toModifyComment(request,response);break;//4、进入修改评论界面
		case 5:ModifyComment(request, response);break;//5、修改评论
		case 6:DeleteComment(request,response);break;
		default:
			break;
		}
	}
	public void toComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		int totalcount=commentService.GetCount();
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		
		request.setAttribute("prepage", prepage);
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		List<EasybuyComment> comments=commentService.getCommentByPage(curpage, pageround);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("guestbook.jsp").forward(request, response);
	}
	public void Comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EasyUser myUser=(EasyUser)request.getSession().getAttribute("myuser");
		String commentcontent=request.getParameter("guestContent");
		EasybuyComment comment=new EasybuyComment();
		comment.setEc_content(commentcontent);
		comment.setEc_nick_name(myUser.getEu_user_id());

        int i=commentService.AddComment(comment);
        if(i>0)
        {
        	toComment(request, response);
        }
	}
	
	public void toManageComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		int totalcount=commentService.GetCount();
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		
		request.setAttribute("prepage", prepage);
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		List<EasybuyComment> comments=commentService.getCommentByPage(curpage,pageround);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("WEB-INF/manage/guestbook.jsp").forward(request, response);
	}
	public void toModifyComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		EasybuyComment comment=commentService.getCommentById(id);
		request.setAttribute("comment",comment);
		request.getRequestDispatcher("WEB-INF/manage/guestbook-modify.jsp").forward(request, response);
	}
	public void ModifyComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		EasybuyComment comment=commentService.getCommentById(id);
		String content=request.getParameter("replyContent");
		comment.setEc_reply(content);
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comment.setEc_reply_time(Timestamp.valueOf(df.format(d)));
		int i=commentService.ModifyComment(comment);
		if(i>0)
		{
			request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}
	}
	
	public void DeleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		int i=commentService.DeleteCommentById(id);
		if(i>0)
		{
			toManageComment(request, response);
		}
	}
}
