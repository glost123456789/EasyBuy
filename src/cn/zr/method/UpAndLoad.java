package cn.zr.method;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpAndLoad {
	
	public  static boolean whether_in_round(String filename,List<String> round)
	{
		if(round!=null)
		{
			String ext=filename.substring(filename.lastIndexOf(".")+1);
			if(!round.contains(ext))
			{
				return false;
			}
			return true;
		}
		return true;

	}
	public static String UploadContext(HttpServletRequest request,HttpServletResponse response, List<String> round,int MaxSize,List items)throws ServletException, IOException
	{


		String uploadFileName = ""; // 上传的文件名
		String fieldName = ""; // 表单字段元素的name属性值
		PrintWriter out=response.getWriter();
		// 请求信息中的内容是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 上传文件的存储路径（服务器文件系统上的绝对文件路径）
		String uploadFilePath = request.getSession().getServletContext().getRealPath("files/");  //files是上传的文件夹，具体修改
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			if(MaxSize>0)
			{
				upload.setFileSizeMax(MaxSize);
			}
			try {
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) { // 依次处理每个文件
					System.out.println("执行到此处过");
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) { // 普通表单字段

						fieldName = item.getFieldName(); // 表单字段的name属性值
						if (fieldName.equals("user")) {
							// 输出表单字段的值
							out.print(item.getString("UTF-8") + "上传了文件。<br/>");
						}
					} else { // 文件表单字段
						String fileName = item.getName();
						System.out.println("执行到文件字段");
						if (fileName != null && !fileName.equals("")) {
							File fullFile = new File(item.getName());  //获取文件
							uploadFileName = fullFile.getName();		//获取文件名
							if(round==null || whether_in_round(uploadFileName,round))
							{
								File saveFile = new File(uploadFilePath, uploadFileName);
								item.write(saveFile);
								//out.print("上传成功后的文件名是：" + uploadFileName);
								System.out.println("执行到上传成功");
								return uploadFileName;
							}
							else{
								//out.println("上传失败！文件类型不符");
								System.out.println("执行到上传失败");
								return null;
							}
						}
					}
				}
			}catch(FileUploadBase.FileSizeLimitExceededException ex){
			    out.print("上传失败，文件太大，单个文件的最大限制是："+
			    	    MaxSize+"bytes!");
			    }
			catch (Exception e) {
				System.out.println("执行到此处异常跳出");
				e.printStackTrace();
			}

		}else {
			//上传成功的操作具体修改
			//request.getRequestDispatcher("WEB-INF/jsp/upload.jsp").forward(request, response);
			System.out.println("执行到输入流不对");
			return null;
		}
		return null;
	}
}
