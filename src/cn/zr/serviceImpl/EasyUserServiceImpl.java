package cn.zr.serviceImpl;

import java.util.List;

import cn.zr.dao.EasyBuyUserDao;
import cn.zr.daoImpl.EasyBuyUserDaoImpl;
import cn.zr.entity.EasyUser;
import cn.zr.service.EasyUserService;

public class EasyUserServiceImpl implements EasyUserService {
	private EasyBuyUserDao easyBuyUserDao=new EasyBuyUserDaoImpl();
	@Override
	public int IncreaseUser(EasyUser easyUser) {
		int i=0;
		if(easyUser!=null)
		{
			return easyBuyUserDao.Increase(easyUser);
		}
		return 0;
	}
	@Override
	public EasyUser LoginAndGetUser(String id, String pwd) {
		String cpwd=easyBuyUserDao.getUserPwdById(id);
		EasyUser easyUser=new EasyUser();
		if(pwd.equals(cpwd))
		{
			easyBuyUserDao.setUserLoginState(id,1.0f);
			easyUser=easyBuyUserDao.getUserById(id);
			return easyUser;
		}
		return null;
	}
	@Override
	public int Logout(String id) {
		if(id==null || id.equals(""))
		{
			return 0;
		}
		return easyBuyUserDao.setUserLoginState(id,0.0f);
	}
	@Override
	public List<EasyUser> getUsersByPage(int currentpage, int pageround) {
		// TODO Auto-generated method stub
		return easyBuyUserDao.getUsersByPage(currentpage, pageround);
	}
	@Override
	public int DeleteUserById(String id) {
		if(id!=null && !id.equals(""))
		{
			return easyBuyUserDao.DeleteUserById(id);
		}
		return 0;
	}
	@Override
	public EasyUser getUserById(String id) {
		if(id!=null && !id.equals(""))
		{
			return easyBuyUserDao.getUserById(id);
		}
		return null;
	}
	@Override
	public int ModifyUserById(EasyUser modifyuser) {
		if(modifyuser!=null)
		{
			return easyBuyUserDao.ModifyUserByUserId(modifyuser);
		}
		return 0;
	}
	@Override
	public int GetCount() {
		// TODO Auto-generated method stub
		return easyBuyUserDao.getCount();
	}

}
