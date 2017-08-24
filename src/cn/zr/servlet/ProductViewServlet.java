package cn.zr.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.zr.entity.EasybuyProduct;
import cn.zr.entity.EasybuyProductCategory;
import cn.zr.method.CookieMethod;
import cn.zr.method.UpAndLoad;
import cn.zr.service.EasybuyCategoryService;
import cn.zr.service.EasybuyproductService;
import cn.zr.serviceImpl.EasybuyCategoryServiceImpl;
import cn.zr.serviceImpl.EasybuyproductServiceImpl;

/**
 * Servlet implementation class ProductViewServlet
 */
@WebServlet("/ProductViewServlet")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EasybuyproductService easybuyproductService=new EasybuyproductServiceImpl();
    private EasybuyCategoryService categoryService=new EasybuyCategoryServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductViewServlet() {
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
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart)
		{
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		ServletFileUpload upload = new ServletFileUpload(factory); 
		List items=null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Map<String,String> param = new HashMap(); 
		for(Object object:items){ 
		    FileItem fileItem = (FileItem) object; 
		    if (fileItem.isFormField()) { 
		        param.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的 
		    } 
		} 
		int option=Integer.parseInt(param.get("option"));
		switch (option) {
			case 1:showProductView(request, response);break;  //1、显示单独某个商品
			case 2:ManageProduct(request,response);break;//2、进入商品管理界面
			case 3:DeleteProduct(request,response);break;//3、删除商品
			case 4:toModifyProduct(request,response);break;//4、进入修改商品界面
			case 5:doModifyProduct(request,response,param,items);break;//5、修改商品内容
			case 6:AddProduct(request,response,param,items);break;//6、添加商品
		}
		}else{
		int option=Integer.parseInt(request.getParameter("option"));
		switch (option) {
			case 1:showProductView(request, response);break;  //1、显示单独某个商品
			case 2:ManageProduct(request,response);break;//2、进入商品管理界面
			case 3:DeleteProduct(request,response);break;//3、删除商品
			case 4:toModifyProduct(request,response);break;//4、进入修改商品界面
			//case 5:doModifyProduct(request,response,null);break;//5、修改商品内容
			//case 6:AddProduct(request,response);break;//6、添加商品
			case 6:request.getRequestDispatcher("WEB-INF/manage/product-add.jsp").forward(request, response); //6、既然怒增加商品界面
			case 7:toProductListView(request,response);break;//7、进入某一分类商品
			case 8:ChangeListViewPage(request,response);break;//8、换页
		}
		}
	}
	
	public void showProductView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int productid=Integer.parseInt(request.getParameter("productname"));
		EasybuyProduct nowproduct=easybuyproductService.getProductById(productid);
		request.getSession().setAttribute("nowproduct", nowproduct);
		request.getRequestDispatcher("product-view.jsp").forward(request, response);
	}
	
	public void ManageProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
		int totalcount=easybuyproductService.GetCount();
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		
		request.setAttribute("prepage", prepage);
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		List<EasybuyProduct> products=easybuyproductService.getProductByPage(curpage, pageround);
		request.setAttribute("pdtlist", products);
		request.getRequestDispatcher("WEB-INF/manage/product.jsp").forward(request,response);	
	}
	
	public void DeleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int productid=Integer.parseInt(request.getParameter("productname"));
		int i=easybuyproductService.DeleteProduct(productid);
		request.getRequestDispatcher("WEB-INF/manage/product.jsp").forward(request, response);
	}
	
	public void toModifyProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id=Integer.parseInt(request.getParameter("productname"));
		EasybuyProduct modifyproduct=easybuyproductService.getProductById(id);
		request.setAttribute("modifyproduct", modifyproduct);
		request.getRequestDispatcher("WEB-INF/manage/product-modify.jsp").forward(request,response);
	}
	
	public void doModifyProduct(HttpServletRequest request, HttpServletResponse response,Map<String,String> param,@SuppressWarnings("rawtypes") List items) throws ServletException, IOException
	{
		int id=Integer.parseInt(param.get("moproduct"));
		String productname=param.get("productName");
		String productds=param.get("productDs");
		String productcategory=param.get("category");
		String productpcategory=param.get("parentCategory");
		String productbrand=param.get("productBrand");
		String productprice=param.get("productPrice");
		String productstock=param.get("productNumber");
		String productorigin=param.get("productOriginPrice");
		String filename=UpAndLoad.UploadContext(request, response, null, 0,items);
		@SuppressWarnings("unchecked")
		List<EasybuyProductCategory> categories=(List<EasybuyProductCategory>) request.getSession().getAttribute("categories");
		EasybuyProduct modifyproduct=easybuyproductService.getProductById(id);
		modifyproduct.setEp_name(productname);
		modifyproduct.setEp_brand(productbrand);
		modifyproduct.setEp_price(Float.parseFloat(productprice));
		modifyproduct.setEp_stock(Integer.parseInt(productstock));
		modifyproduct.setEp_origin_price(Float.parseFloat(productorigin));
		if((productds!=null) && !productds.equals(""))
		{
			modifyproduct.setEp_description(productds);
		}
		for(int i=0;i<categories.size();i++)
		{
			if(productcategory.equals(categories.get(i).getEpc_name()))
			{
				modifyproduct.setEpc_child_id(categories.get(i).getEpc_id());
				break;
			}
		}
		for(int i=0;i<categories.size();i++)
		{
			if(productpcategory.equals(categories.get(i).getEpc_name()))
			{
				modifyproduct.setEpc_id(categories.get(i).getEpc_id());
				break;
			}
		}
		modifyproduct.setEp_file_name(filename);
		int i=easybuyproductService.ModifyProduct(modifyproduct);
		if(i>0)
		{
			request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("WEB-INF/manage/product-modify.jsp").forward(request, response);
		}
		
	}
	
	public void AddProduct(HttpServletRequest request, HttpServletResponse response,Map<String,String> param,List items) throws ServletException, IOException
	{
		@SuppressWarnings("unchecked")
		List<EasybuyProductCategory> categories=(List<EasybuyProductCategory>) request.getSession().getAttribute("categories");
		String productname=param.get("productName");
		String productds=param.get("productDs");
		String productcategory=param.get("category");
		String productpcategory=param.get("parentCategory");
		String productbrand=param.get("productBrand");
		String productprice=param.get("productPrice");
		String productstock=param.get("productNumber");
		String productorigin=param.get("productOriginPrice");
		String filename=UpAndLoad.UploadContext(request, response, null, 0,items);
		EasybuyProduct newproduct=new EasybuyProduct();
		newproduct.setEp_name(productname);
		newproduct.setEp_brand(productbrand);
		newproduct.setEp_origin_price(Float.parseFloat(productorigin));
		newproduct.setEp_price(Float.parseFloat(productprice));
		newproduct.setEp_stock(Integer.parseInt(productstock));
		if((productds!=null) && !productds.equals(""))
		{
			newproduct.setEp_description(productds);
		}
		for(int i=0;i<categories.size();i++)
		{
			if(productcategory.equals(categories.get(i).getEpc_name()))
			{
				newproduct.setEpc_child_id(categories.get(i).getEpc_id());
				break;
			}
		}
		for(int i=0;i<categories.size();i++)
		{
			if(productpcategory.equals(categories.get(i).getEpc_name()))
			{
				newproduct.setEpc_id(categories.get(i).getEpc_id());
				break;
			}
		}
		newproduct.setEp_file_name(filename);
		int i=easybuyproductService.AddProduct(newproduct);
		if(i>0)
		{
			request.getRequestDispatcher("WEB-INF/manage/manage-result.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("WEB-INF/manage/product-add.jsp").forward(request, response);
		}
	}

	public void toProductListView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int category=Integer.parseInt(request.getParameter("categoryid"));
		request.getSession().setAttribute("thiscategoryid", category);
		EasybuyProductCategory thiscategory=categoryService.getCategoryById(category);
		EasybuyProductCategory thisparentcategory=categoryService.getCategoryById(thiscategory.getEpc_parent_id());
		request.getSession().setAttribute("thisparentcategoryid",thiscategory.getEpc_parent_id());
		request.setAttribute("thiscategory", thiscategory);
		request.setAttribute("thisparentcategory", thisparentcategory);
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
		int totalcount=easybuyproductService.GetCountByCategory(category);
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		request.setAttribute("prepage", prepage);
		List<EasybuyProduct> products=easybuyproductService.GetproductsbyCategoryAndPage(category, curpage,pageround);
		CookieMethod.getCookieProduct(request, response, "product");
		request.setAttribute("products", products);
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}
	
	public void ChangeListViewPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int category=(Integer)request.getSession().getAttribute("thiscategoryid");
		EasybuyProductCategory thiscategory=categoryService.getCategoryById(category);
		EasybuyProductCategory thisparentcategory=categoryService.getCategoryById(thiscategory.getEpc_parent_id());
		request.setAttribute("thiscategory", thiscategory);
		request.setAttribute("thisparentcategory", thisparentcategory);
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
		int totalcount=easybuyproductService.GetCountByCategory(category);
		int totalpage=(totalcount%pageround==0)?(totalcount/pageround):(totalcount/pageround+1); //总页数
		int prepage=(curpage==1)?(1):(curpage-1);  //前一页
		int nextpage=(curpage==totalpage)?(totalpage):(curpage+1);  //后一页
		request.setAttribute("currentpage", curpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("pageround", pageround);
		request.setAttribute("prepage", prepage);
		CookieMethod.getCookieProduct(request, response, "product");
		List<EasybuyProduct> products=easybuyproductService.GetproductsbyCategoryAndPage(category, curpage,pageround);
		request.setAttribute("products", products);
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}
}
