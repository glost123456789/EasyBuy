package cn.zr.serviceImpl;

import java.util.List;

import cn.zr.dao.EasybuyAddressDao;
import cn.zr.daoImpl.EasybuyAddressDaoImpl;
import cn.zr.entity.EasybuyAddress;
import cn.zr.service.EasybuyAddressService;

public class EasybuyAddressServiceImpl implements EasybuyAddressService {
	private EasybuyAddressDao addressdao=new EasybuyAddressDaoImpl();
	@Override
	public int Addaddress(EasybuyAddress address) {
		if(address!=null)
		{
		return addressdao.AddAddress(address);
		}
		return 0;
	}

	@Override
	public List<EasybuyAddress> getAddressbyUser(String id) {
		// TODO Auto-generated method stub
		if(id!=null && !id.equals(""))
		{
			return addressdao.getAddressByUserId(id);
		}
		return null;
	}

	@Override
	public int DeleteAddress(int id) {
		if(id>0)
		{
			return addressdao.DeleteAddress(id);
		}
		return 0;
	}

}
