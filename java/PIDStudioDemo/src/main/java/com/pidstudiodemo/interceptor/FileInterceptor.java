package com.pidstudiodemo.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FileInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//如果请求有文件的长传，则该请求是一个MultipartHttpServletRequest
				if(req instanceof MultipartHttpServletRequest){
				     HttpServletResponse httpResponse = (HttpServletResponse) res;
					MultipartHttpServletRequest multipartReq=(MultipartHttpServletRequest)req;
					//获取所有文件的键值对
					Map<String,MultipartFile> fileMap=multipartReq.getFileMap();
					Iterator<String> keyIt=fileMap.keySet().iterator();
					while(keyIt.hasNext()){
						String key=keyIt.next();
						MultipartFile mulFile=fileMap.get(key);
						if(mulFile.getSize()>1024000){
							 HttpServletRequest httpRequest = (HttpServletRequest) req;
						     HttpSession session = httpRequest.getSession();
							req.setAttribute("fileField", key);
							session.setAttribute("remind", "文件大小超出");
							httpResponse.sendRedirect("/employeeManage/");
							return false;
						}
						String contentType=mulFile.getContentType();
						if(mulFile.getSize()!=0){//文件大小等于0的时候直接通过
						if(!contentType.startsWith("image/")){
							 HttpServletRequest httpRequest = (HttpServletRequest) req;
						     HttpSession session = httpRequest.getSession();
							req.setAttribute("fileField", key);
							session.setAttribute("remind", "文件不是图片");
							httpResponse.sendRedirect("/employeeManage/");
							return false;
						}
					}return true;}
					return true;
				}else{
					return true;
				}
			}



}
