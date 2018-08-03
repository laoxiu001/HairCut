package com.pidstudiodemo.service.imp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pidstudiodemo.dao.EmployeeManageDao;
import com.pidstudiodemo.dao.EmployeePositionBackupDao;
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.model.EmployeePositionBackup;
import com.pidstudiodemo.service.EmployeeManageServic;
import com.pidstudiodemo.service.EmployeeTypeService;
@Service(value="employeeManageServic")
public class EmployeeManageServicImp implements EmployeeManageServic {
	@Autowired
	@Qualifier(value="employeeManageDao")
	private EmployeeManageDao employeeManageDao;
	@Autowired
	@Qualifier(value="employeeTypeService")
	private EmployeeTypeService employeeTypeService;
	@Autowired
	@Qualifier(value="employeePositionBackupDao")
	private EmployeePositionBackupDao epd;
	@Transactional//事务注释
	public List<EmployeeManage> queryEmployeeManage(int page,int size, String status) {
		// TODO Auto-generated method stub
		try{int count;
		count = employeeManageDao.countEmployee();
		//判断page的值
		if(page<=(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		List<EmployeeManage> list = new ArrayList<EmployeeManage>();
		//分页查询条件page 页数 size 每页显示的条数 "id" 按哪个字段排序
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		list = employeeManageDao.queryEmployeeManage(status,pageable);
		return list;}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<EmployeeManage> queryDetailed(int id) {
		// TODO Auto-generated method stub
		List<EmployeeManage> list = new ArrayList<EmployeeManage>();
		list = employeeManageDao.queryDetailed(id);
		return list;
	}

	public String uploadFile(MultipartFile file) {
		// TODO Auto-generated method stub
		String url;
		String visit = "";
		if (!file.isEmpty()) {
            try {
            	System.out.println("进入文件上传");
            	//获取上传文件的文件类型
            	String contentType=file.getContentType();
            	if(contentType.startsWith("image/")){
            	//获取上传文件的文件名
            	String name = file.getOriginalFilename();
            	//获取文件的后缀
            	int lastPoiIndex=name.lastIndexOf(".");
            	String extendNam = null;
            	if(lastPoiIndex>-1){
            		extendNam = name.substring(lastPoiIndex);
    			}
            	//确定新的文件名
    			Date now=new Date();
    			//获取当前时间
    			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
    			String newFileName=sdf.format(now)+extendNam;
            	//文件储存的地址/WEB-INF/classes/resources/
    			url =System.getProperty("user.dir")+"/src/main/resources/static/img/login/"+newFileName;
            	visit = "../img/login/"+newFileName;
//            	url =System.getProperty("user.dir")+"/"+newFileName;
//				replace替换字符串
//            	url =url1.replace("bin","webapps/PIDStudioDemo/WEB-INF/classes/static/img/login");
            	visit = "../img/login/"+newFileName;
            	System.out.println(url);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(url)));//储存文件到指定地址	  
                out.write(file.getBytes());
                out.flush();
                out.close();//关闭
                System.out.println("上传成功");
                }else {
                	System.out.println("文件不是图片");
                	 return "";
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "" ;
            }
            return visit ;
        } else {
            return "";
        }
	}

	public String insertEmployee(EmployeeManage em) {
		// TODO Auto-generated method stub
		try {
			String prompt ="执行操作成功";
			String url ="";
			System.out.println("图片地址"+em.getPhoto());
			if(em.getPhoto().equals("")||em.getPhoto().equals(null)){
				List<EmployeeManage> list = employeeManageDao.queryDetailed(em.getId());
				if(list.size()!=0){
				for(EmployeeManage e : list){
					if(e.getTypeId() == em.getTypeId()){
						try{
						epd.delete(e.getTypeId());//修改回原来的类容删除备份表的类容
						}
						catch (Exception e1) {
							// TODO: handle exception
						}
					}else{
						Date date = new Date();
						SimpleDateFormat sdf=new SimpleDateFormat("dd");
						if(sdf.format(date).equals("01")){
							
						}else{
							EmployeePositionBackup ems = new EmployeePositionBackup();
							ems.setId(em.getId());
							ems.setEmployeeId(em.getId());
							ems.setPositionId(em.getTypeId());
							prompt="执行操作成功你的类型将在下月1号更改";
							epd.insert(ems);
							em.setTypeId(e.getTypeId());
						}
					}
					url = e.getPhoto();
					}
				}else{
					url = "../util/AdminLTE/dist/img/user2-160x160.jpg";		
			}
			}else{
				url = em.getPhoto();
			}
			System.out.println("地址url"+url);
			em.setPhoto(url);
			employeeManageDao.insertEmployee(em);
			return prompt;
		} catch (Exception e) {
			// TODO: handle exception
			return "是否有该员工类型";
		}
	}
	//修改
	public String updateEmployee(EmployeeManage em) {
		// TODO Auto-generated method stub
		try {
			employeeManageDao.updateEmployee(em);
			return "修改成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "修改失败";
		}
		
	}
	//获取员工总数
	public int countEmployee(){
		return employeeManageDao.countEmployee();
	}

	public EmployeeManage queryNumber(String number) {
		// TODO Auto-generated method stub
		return employeeManageDao.queryNumber(number);
	}
	public int queryMaxPage(int page, int size,String stasus) {
		// TODO Auto-generated method stub
		int count;
		count = employeeManageDao.countEmployee(stasus);
		if(count%size==0){page=count/size-1;}
		else{page = count/size;}
		return page;
	}

	public List<EmployeeManage> queryConditions(String conditions,String status) {
		// TODO Auto-generated method stub
		return employeeManageDao.queryConditions(conditions,status);
	}

	public List<EmployeeManage> queryEmployeeId(int id) {
		// TODO Auto-generated method stub
		return 	employeeManageDao.queryEmployeeId(id);
	}

}
