package cn.zr.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import cn.zr.dao.EasybuyOrderDao;
import cn.zr.dao.EasybuyOrderDetailDao;
import cn.zr.dao.EasybuyproductDao;
import cn.zr.daoImpl.EasybuyOrderDaoImpl;
import cn.zr.daoImpl.EasybuyOrderDetailDaoImpl;
import cn.zr.daoImpl.EasybuyproductDaoImpl;
import cn.zr.entity.EasyBuyOrder;
import cn.zr.entity.EasyOrderAll;
import cn.zr.entity.EasybuyOrderdetail;
import cn.zr.entity.EasybuyProduct;
import cn.zr.service.EasybuyOrderService;

public class EasybuyOrderServiceImpl implements EasybuyOrderService{
	private EasybuyOrderDao orderdao=new EasybuyOrderDaoImpl();
	private EasybuyOrderDetailDao detialdao=new EasybuyOrderDetailDaoImpl();
	private EasybuyproductDao productdao=new EasybuyproductDaoImpl();
	@Override
	public List<EasyBuyOrder> getOrdersByUserId(String userId) {
		// TODO Auto-generated method stub
		if(userId==null || userId.equals(""))
		{
			return null;
		}
		return orderdao.getOrderByUserId(userId);
	}
	@Override
	public int AddOrder(EasyBuyOrder order, List<EasybuyOrderdetail> detail) {
		int id=orderdao.addOrder(order);
		for(int i=0;i<detail.size();i++)
		{
			detail.get(i).setEo_id(id);
			detialdao.AddOrderDetail(detail.get(i));	
		}
		return 0;
	}
	@Override
	public List<EasybuyOrderdetail> getOrderdetail(int id) {
		// TODO Auto-generated method stub
		if(id>0)
		{
			return detialdao.getDetailsbyOrderId(id);
		}
		return null;
	}
	@Override
	public List<EasyBuyOrder> getOrderbyPage(int currentpage, int size) {
		// TODO Auto-generated method stub
		return orderdao.getOrderByPage(currentpage, size);
	}
	@Override
	public EasyBuyOrder getOrderById(int id) {
		if(id>0)
		{
			return orderdao.getOrderById(id);
		}
		return null;
	}
	@Override
	public List<EasyBuyOrder> getOrdersByUserName(String name) {
		// TODO Auto-generated method stub
		if(name!=null && !name.equals(""))
		{
			return orderdao.getOrdersByUserName(name);
		}
		return null;
	}
	@Override
	public List<EasyOrderAll> getOrderDetailByPage(int currentpage, int size) {
		List<EasyBuyOrder> orders=orderdao.getAllOrder();
		List<EasyOrderAll> details=new ArrayList<EasyOrderAll>();
		int step=0;
		int start=(currentpage-1)*size;
		int end=currentpage*size;
		for(int i=0;i<orders.size();i++)
		{
			List<EasybuyOrderdetail> detail=detialdao.getDetailsbyOrderId(orders.get(i).getEo_id());
			int prestep=step;
			step+=detail.size();
			if(step>start && prestep<start)
			{
				EasyOrderAll sdetail=new EasyOrderAll();
				sdetail.setMyOrder(orders.get(i));
				for(int j=detail.size()-step+start-1;j<(detail.size()-step+start+size);j++)
				{
					if(j>=detail.size())
					{
						break;
					}
					sdetail.addDetail(detail.get(j));
					sdetail.addProduct(productdao.getProductById(detail.get(j).getEp_id()));
				}
				details.add(sdetail);
			}
			else if(prestep>=start && step<=end)
			{
				EasyOrderAll sdetail=new EasyOrderAll();
				sdetail.setMyOrder(orders.get(i));
				for(int j=0;j<(end-prestep+1);j++)
				{
					if(j>=detail.size())
					{
						break;
					}
					sdetail.addDetail(detail.get(j));
					sdetail.addProduct(productdao.getProductById(detail.get(j).getEp_id()));
				}
				details.add(sdetail);
			}
		}
		return details;
	}
	@Override
	public int GetCount() {
		// TODO Auto-generated method stub
		return detialdao.getCount();
	}
	@Override
	public List<EasyOrderAll> getDetailsByUserName(String name) {
		List<EasyBuyOrder> orders=orderdao.getOrdersByUserName(name);
		List<EasyOrderAll> detailsss=new ArrayList<EasyOrderAll>();
		for(int i=0;i<orders.size();i++)
		{
			List<EasybuyOrderdetail> details=detialdao.getDetailsbyOrderId(orders.get(i).getEo_id());
			EasyOrderAll detailss=new EasyOrderAll();
			detailss.setMyOrder(orders.get(i));
			detailss.addDetails(details);
			List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
			for(int j=0;j<details.size();j++)
			{
				products.add(productdao.getProductById(details.get(j).getEp_id()));
			}
			detailss.addProducts(products);
			detailsss.add(detailss);
		}
		return detailsss;
	}
	@Override
	public List<EasyOrderAll> getDetailsByOrder(int id) {
		EasyBuyOrder order=orderdao.getOrderById(id);
		List<EasyOrderAll> detailsss=new ArrayList<EasyOrderAll>();
		
		List<EasybuyOrderdetail> details=detialdao.getDetailsbyOrderId(order.getEo_id());
		EasyOrderAll detailss=new EasyOrderAll();
		detailss.setMyOrder(order);
		detailss.addDetails(details);
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		for(int j=0;j<details.size();j++)			{
			products.add(productdao.getProductById(details.get(j).getEp_id()));
		}
		detailss.addProducts(products);
		detailsss.add(detailss);
		return detailsss;
	}
	@Override
	public int ModifyOrder(EasyBuyOrder order) {
		// TODO Auto-generated method stub
		return orderdao.ModifyOrder(order);
	}

}
