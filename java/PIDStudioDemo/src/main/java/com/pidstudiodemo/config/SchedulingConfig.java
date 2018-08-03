package com.pidstudiodemo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.EmployeeManageDao;
import com.pidstudiodemo.dao.EmployeePositionBackupDao;
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.model.EmployeePositionBackup;
import com.pidstudiodemo.service.SalaryService;

/**
 * 定时事务30天执行一次	
 * */
@Configuration
@EnableScheduling
public class SchedulingConfig {
	 	@Autowired
	 	@Qualifier(value="salaryService")
	 	private SalaryService salaryService;
	 	@Autowired
	 	@Qualifier(value="employeePositionBackupDao")
	 	private EmployeePositionBackupDao epbd;
	 	@Autowired
	 	@Qualifier(value="employeeManageDao")
	 	private EmployeeManageDao emd;
	 	//@Scheduled(fixedRate = 5000) 
	 	@Scheduled(cron="0 0 0 1 * ?")
	 	@Transactional
	    public void getToken() {
	 		//将员工工资记录到工资表并写入消费记录
	    	salaryService.insertSalary();
	    	//对缓存表的操作
	    	//查询所有的数据
	    	List<EmployeePositionBackup>  list = epbd.query();
	    	//遍历
	    	for(EmployeePositionBackup epb : list){
	    		//根据表中有的id查询员工表对应的记录
	    	List<EmployeeManage> listMe = emd.queryDetailed(epb.getId());
	    	//修改员工表的记录
	    	for(EmployeeManage em : listMe){
	    		em.setTypeId(epb.getPositionId());
	    		emd.insertEmployee(em);
	    	}
	    	}
	    	//删除备份表
	    	epbd.delete();
	    }
}
