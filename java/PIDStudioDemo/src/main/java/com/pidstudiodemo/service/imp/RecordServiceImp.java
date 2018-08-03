package com.pidstudiodemo.service.imp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pidstudiodemo.dao.EmployeeTypeDao;
import com.pidstudiodemo.dao.RecordDao;
import com.pidstudiodemo.dao.ServiceItemDao;
import com.pidstudiodemo.model.Customer;
import com.pidstudiodemo.model.EmployeeManage;
import com.pidstudiodemo.model.Record;
import com.pidstudiodemo.model.ServiceItem;
import com.pidstudiodemo.service.CustomerService;
import com.pidstudiodemo.service.EmployeeManageServic;
import com.pidstudiodemo.service.RecordService;
import com.pidstudiodemo.service.ServiceItemService;


@Service(value="recordService")
public class RecordServiceImp  implements RecordService{
	@Autowired
	@Qualifier(value="recordDao")
	private RecordDao recordDao;
	@Autowired
	@Qualifier(value="employeeTypeDao")
	private EmployeeTypeDao employeeTypeDao;
	@Autowired
	@Qualifier(value="serviceItemDao")
	private ServiceItemDao serviceItemDao;
	@Autowired
	@Qualifier(value="customerService")
	private CustomerService customerService;
	@Autowired
	@Qualifier(value="serviceItemService")
	private ServiceItemService serviceItemService;
	@Autowired
	@Qualifier(value="employeeManageServic")
	private EmployeeManageServic employeeManageServic;
	//查询
	@Transactional
	public Page<Record> queryRecord(int page, int size) {
		// TODO Auto-generated method stub
		try {
		int count;
		count = recordDao.countRecord();
		//判断page的值
		if(page<=(count/size)){page=page+0;}
			else{if(count%size==0){page = count/size;}
			else{page = count/size+1;}}
		Pageable pageable = new  PageRequest(page,size,Sort.Direction.DESC, "id");
		Page<Record> pageRecord = recordDao.queryRecord(pageable);
		return pageRecord;} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	//添加消费记录修改余额
	@Transactional
	public String insertRecord(Record record,int[] serviceIemId,String[] number_1,HttpSession session) {
		// TODO Auto-generated method stub
		//调用DecimalFormat方法让double类型保留两位小数得到String类型 调用double.value方法强转为double类型
		try{
			if(!record.getUsername().equals("")){
				System.out.println("输入了用户名");
			String[] number = new String[serviceIemId.length];
			int j = 0;
			for(int i= 0 ; i < number_1.length;i++){
				if(number_1[i]!=""){
					number[j] = number_1[i];
					j++;
				}
			}
			DecimalFormat df = new DecimalFormat("#0.00");
			String username = record.getUsername();//获取用户名或者电话号码
			String name = record.getName();//获取消费者姓名
			Record[] recordArray = new  Record[serviceIemId.length];
			double[] actuallyPaid = new double[serviceIemId.length];//实付金额
			double[] payavle = new double[serviceIemId.length];//应付金额
			double sumPayavle = 0;//应付金额总和
			double[] weightingFactor = new double[number.length];
			Customer customer = customerService.quserCUserName(username);
			username = customer.getUserName();//存入获取的用户名
			double discount = customer.getDiscount();//获取折扣系数
			double balance = customer.getBalance();//获取余额
			double sum = 0 ;
			int id = 0;
			//遍历数组查询选中id对应的实际金额
			for(int i=0;i<serviceIemId.length;i++){
				id = serviceIemId[i];//将数组的的值存入id
				ServiceItem si = serviceItemService.queryServiceItem(id);//根据id返回记录
				EmployeeManage employeeManage = employeeManageServic.queryNumber(number[i]);//获取employeeManage对象
				weightingFactor[i] = employeeManage.getEmployeeType().getWeightingFactor();//获取折扣系数
				actuallyPaid[i] =si.getPrice()*weightingFactor[i];//获取实付金额
				sum = sum +actuallyPaid[i];
				payavle[i] = actuallyPaid[i] * discount ;//* weightingFactor[i];//计算每一个实付金额
				sumPayavle = sumPayavle + payavle[i];//累加计算总的实付金额
			}
			//根据Username查询出关于该用户的信息
			session.setAttribute("sum",Double.valueOf(df.format(sum)));
			session.setAttribute("discount", discount);
			session.setAttribute("sumPayavle",Double.valueOf(df.format(sumPayavle)));
			balance = balance - sumPayavle;//计算用户余额
			session.setAttribute("balance1","余额" + Double.valueOf(df.format(balance)));//总的余额
			//计算出余额判断余额是否大于零
			if(balance>=0){
			for(int i=0;i<serviceIemId.length;i++){
			recordArray[i] = new Record();//实例record[i]对象
			recordArray[i].setName(name);//将消费者姓名写入record[i]对象
			recordArray[i].setUsername(username);//将用户号写入record[i]对象
			recordArray[i].setService(serviceIemId[i]);//将服务id写入record[i]对象
			recordArray[i].setNumber(number[i]);//将服务的员工写入record[i]对象
			recordArray[i].setActuallyPaid( Double.valueOf(df.format(actuallyPaid[i])));//将应付金额record[i]对象保留小数点后两位
			recordArray[i].setPayavle( Double.valueOf(df.format(payavle[i])));//将实付金额写入record[i]对象保留小数点后两位
			recordDao.insertRecord(recordArray[i]);//提交不同的对象保证创建不同得记录
			}
			customer.setBalance(Double.valueOf(df.format(balance)));//将余额写入cstomer对象保留小数点后两位
			customerService.updateCustomer(customer);//修改用户余额
			}else{
				return "余额不足";
			}
			return "添加成功";
			}else{
				System.out.println("没输入用户名");
				String[] number = new String[serviceIemId.length];
				int j = 0;
				for(int i= 0 ; i < number_1.length;i++){
					if(number_1[i]!=""){
						number[j] = number_1[i];
						j++;
					}
				}
				DecimalFormat df = new DecimalFormat("#0.00");
				String username = "非会员用户";//获取用户名或者电话号码
				String name = record.getName();//获取消费者姓名
				Record[] recordArray = new  Record[serviceIemId.length];
				double[] actuallyPaid = new double[serviceIemId.length];//实付金额
				double[] payavle = new double[serviceIemId.length];//应付金额
				double sumPayavle = 0;//应付金额总和
				double[] weightingFactor = new double[number.length];
				Customer customer = customerService.quserCUserName(username);
				username = customer.getUserName();//存入获取的用户名
				double discount = customer.getDiscount();//获取折扣系数
				double balance = customer.getBalance();//获取余额
				double sum = 0;
				int id = 0;
				//遍历数组查询选中id对应的实际金额
				for(int i=0;i<serviceIemId.length;i++){
					id = serviceIemId[i];//将数组的的值存入id
					ServiceItem si = serviceItemService.queryServiceItem(id);//根据id返回记录
					EmployeeManage employeeManage = employeeManageServic.queryNumber(number[i]);//获取employeeManage对象
					weightingFactor[i] = employeeManage.getEmployeeType().getWeightingFactor();//获取折扣系数
					actuallyPaid[i] =si.getPrice()*weightingFactor[i];//获取实付金额
					sum = sum + actuallyPaid[i] ;//获取付款总金额
					payavle[i] = actuallyPaid[i] * discount ;//* weightingFactor[i];//计算每一个实付金额
					sumPayavle = sumPayavle + payavle[i];//累加计算总的实付金额
				}
				//根据Username查询出关于该用户的信息
				session.setAttribute("sum",Double.valueOf(df.format(sum)));
				session.setAttribute("discount", discount);
				session.setAttribute("sumPayavle",Double.valueOf(df.format(sumPayavle)));
				//计算出余额判断余额是否大于零
				for(int i=0;i<serviceIemId.length;i++){
				recordArray[i] = new Record();//实例record[i]对象
				recordArray[i].setName(name);//将消费者姓名写入record[i]对象
				recordArray[i].setUsername(username);//将用户号写入record[i]对象
				recordArray[i].setService(serviceIemId[i]);//将服务id写入record[i]对象
				recordArray[i].setNumber(number[i]);//将服务的员工写入record[i]对象
				recordArray[i].setActuallyPaid( Double.valueOf(df.format(actuallyPaid[i])));//将应付金额record[i]对象保留小数点后两位
				recordArray[i].setPayavle( Double.valueOf(df.format(payavle[i])));//将实付金额写入record[i]对象保留小数点后两位
				recordDao.insertRecord(recordArray[i]);//提交不同的对象保证创建不同得记录
				}
				customer.setBalance(Double.valueOf(df.format(balance)));//将余额写入cstomer对象保留小数点后两位
				customerService.updateCustomer(customer);//修改用户余额
				return "添加成功";
			}
			}catch(Exception e){
				return "添加失败";
				
			}
	}
	public int countRecord() {
		// TODO Auto-generated method stub
		return recordDao.countRecord();
	}
	public int queryMaxPage(int page, int size) {
		// TODO Auto-generated method stub
		int count;
		count = recordDao.countRecord();
		if(count%size==0){page=count/size-1;}
		else{page = count/size;}
		return page;
	}
	public List<Record> queryConditions(String conditions) {
		// TODO Auto-generated method stub
		return recordDao.queryConditions(conditions);
	}
	public double queryDalance(String userName) {
		// TODO Auto-generated method stub
		return recordDao.queryDalance(userName);
	}
	public List<String> queryService() {
		// TODO Auto-generated method stub
		return recordDao.queryService();
	}
	public String queryNumber() {
		// TODO Auto-generated method stub
		String number = null;
		List<String> lst = recordDao.queryNumber();
		for(String l : lst){
			number = l;
			break;
		}
		return number;
	}
	public List<EmployeeManage> queryEmployee(int typrId) {
		// TODO Auto-generated method stub
		ServiceItem si = serviceItemDao.queryServiceItem(typrId);//得到siList对象 存储数据库中的服务类型记录
		List<EmployeeManage> list1 = new ArrayList<EmployeeManage>();
		String[] typeId = si.getType().split(",");//截取type字段储存为数组形式
		for(int i = 0 ; i<typeId.length;i++){
			List<EmployeeManage> list =  employeeManageServic.queryEmployeeId(Integer.parseInt(typeId[i]));
			for(EmployeeManage em : list){
				list1.add(em);
			}
		}
		return list1;
	}
}
