package cn.zr.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.zr.entity.EasyUser;

public class SessionListener implements HttpSessionAttributeListener,HttpSessionListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent e) {
		String sessionname=e.getName();
		switch (sessionname) {
		case "myuser":addMyUsers(e);break;
		default:
			break;
		}
		// TODO Auto-generated method stub
	
	}
	public void addMyUsers(HttpSessionBindingEvent e)
	{
		List<EasyUser> myusers=(List<EasyUser>)e.getSession().getServletContext().getAttribute("myusers");
		EasyUser myuser=(EasyUser) e.getValue();
		if(null==myusers)
		{
			myusers=new ArrayList<EasyUser>();
			myusers.add(myuser);
		}else{
			if(myusers.contains(myuser))
			{
				//用户已登录提示
				e.getSession().setAttribute("SameLoginErr", "该用户已在异地登录,是否先注销？");
				e.getSession().removeAttribute("myuser");
			}else{
				e.getSession().removeAttribute("SameLoginErr");
				myusers.add(myuser);
			}
		}
		e.getSession().getServletContext().setAttribute("myusers", myusers);
	}
	@Override
	public void attributeRemoved(HttpSessionBindingEvent e) {
		// TODO Auto-generated method stub
		String name=e.getName();
		switch (name) {
		case "myuser":removeMyuser(e);
			break;

		default:
			break;
		}
	}
	public void removeMyuser(HttpSessionBindingEvent e)
	{
		if(e.getSession().getAttribute("SameLoginErr")==null || e.getSession().getAttribute("SameLoginErr").equals(""))
		{
			List<EasyUser> myusers=(List<EasyUser>) e.getSession().getServletContext().getAttribute("myusers");
			EasyUser myuser=(EasyUser) e.getValue();
			for(int i=0;i<myusers.size();i++)
			{
				if(myusers.get(i).getEu_user_id().equals(myuser.getEu_user_id()))
				{
					myusers.remove(i);
					break;
				}
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionCreated(HttpSessionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		// TODO Auto-generated method stub
		EasyUser myuser=(EasyUser) e.getSession().getAttribute("myuser");
		if(myuser!=null)
		{
			List<EasyUser> myusers=(List<EasyUser>) e.getSession().getServletContext().getAttribute("myusers");
			for(int i=0;i<myusers.size();i++)
			{
				if(myusers.get(i).getEu_user_id().equals(myuser.getEu_user_id()))
				{
					myusers.remove(i);
					break;
				}
			}
		}
		
	}
}
