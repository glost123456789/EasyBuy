package cn.zr.serviceImpl;

import java.util.List;

import cn.zr.dao.EasybuyShopbusDao;
import cn.zr.daoImpl.EasybuyShopbusDaoImpl;
import cn.zr.entity.EasybuyShopbus;
import cn.zr.service.EasybuyShopbusService;

public class EasyShopbusServiceImpl implements EasybuyShopbusService {
	private EasybuyShopbusDao shopbusdao=new EasybuyShopbusDaoImpl();
	@Override
	public List<EasybuyShopbus> getShopbusByUserId(String id) {
		if(id!=null && !id.equals(""))
		{
			return shopbusdao.getShopBusByUserId(id);
		}
		return null;
	}
	@Override
	public int AddShopbus(EasybuyShopbus shop) {
		if(shop!=null)
		{
			return shopbusdao.AddShopbus(shop);
		}
		return 0;
	}
	@Override
	public EasybuyShopbus getShopById(int id) {
		if(id>0)
		{
			return shopbusdao.getShopById(id);
		}
		return null;
	}
	@Override
	public int ModifyShopbus(EasybuyShopbus shop) {
		if(shop!=null)
		{
			return shopbusdao.Modify(shop);
		}
		return 0;
	}
	@Override
	public int DeleteShopbus(int id) {
		// TODO Auto-generated method stub
		if(id>0)
		{
			return shopbusdao.RemoveShopById(id);
		}
		return 0;
	}
	@Override
	public int DeleteshopbusByUserId(String id) {
		if(id!=null && !id.equals(""))
		{
			return shopbusdao.RemoveShopByUserId(id);
		}
		return 0;
	}
	@Override
	public int getCountByUserId(String id) {
		// TODO Auto-generated method stub
		return shopbusdao.getShopBusByUserId(id).size();
	}

}
