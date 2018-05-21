package com.pidstudiodemo.service.imp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import com.pidstudiodemo.model.EmployeeManage;
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
	@Transactional//事务注释
	public List<EmployeeManage> queryEmployeeManage(int page,int size, String status) {
		// TODO Auto-generated method stub
		int count;
		count = employeeManageDao.countEmployee();
		//判断page的值
		if(page<(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		List<EmployeeManage> list = new ArrayList<EmployeeManage>();
		//分页查询条件page 页数 size 每页显示的条数 "id" 按哪个字段排序
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		list = employeeManageDao.queryEmployeeManage(status,pageable);
		return list;
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
		if (!file.isEmpty()) {
            try {
            	//文件名
            	String name = file.getOriginalFilename();
            	//文件储存的地址
            	url =System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\"+name;
                BufferedOutputStream out = new BufferedOutputStream(	 
                        new FileOutputStream(new File(url)));
                out.write(file.getBytes());
                out.flush();
                out.close();//关闭
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "" ;
            }
            return url ;
        } else {
            return "";
        }
	}

	public String insertEmployee(EmployeeManage em) {
		// TODO Auto-generated method stub
		try {
			employeeManageDao.insertEmployee(em);
			return "添加成功";
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
}
