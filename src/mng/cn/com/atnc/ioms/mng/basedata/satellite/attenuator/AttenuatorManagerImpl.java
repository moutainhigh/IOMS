package cn.com.atnc.ioms.mng.basedata.satellite.attenuator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.com.atnc.common.entity.Pagination;
import cn.com.atnc.common.service.AbstractService;
import cn.com.atnc.ioms.dao.basedata.attenuator.IAttenuatorDao;
import cn.com.atnc.ioms.dao.basedata.equip.IEquipDao;
import cn.com.atnc.ioms.dao.basedata.satellite.ISatelliteSiteDao;
import cn.com.atnc.ioms.entity.basedata.attenuator.Attenuator;
import cn.com.atnc.ioms.model.basedata.satellite.AttenuatorQueryModel;
import cn.com.atnc.ioms.util.ExcelPoiTools;

@Service("AttenuatorManager")
public class AttenuatorManagerImpl extends AbstractService implements IAttenuatorManager {

	@Autowired
	private IAttenuatorDao AttenuatorDao;
	@Autowired
	private IEquipDao equipDao;
	@Autowired
	private ISatelliteSiteDao siteDao;

	
	
	
	@Override
	public Attenuator findById(String id) {
		// TODO Auto-generated method stub
		Attenuator node = AttenuatorDao.findById(id);
/*		if (node.getEquip() == null) {
			return node;
		}		
		node.setEquipId(node.getEquip().getId());
		if (node.getEquip().getSatellite() == null) {
			return node;
		}		
		node.setSiteCode(node.getEquip().getSatellite().getSiteCode());//?
		node.setSiteId(node.getEquip().getSatellite().getId());//?
*/		
		
	/*	if (node.getAntenna()== null) {
			return node;
		}		
		node.setAntennaId(node.getAntenna().getId());*/
	
		if (node.getSite() == null) {
			return node;
		}		
		node.setSiteCode(node.getSite().getSiteCode());
		node.setSiteId(node.getSite().getId());
		
		return node;
	}

	


	
	@Override
	public Pagination queryPage(AttenuatorQueryModel model) {
		// TODO Auto-generated method stub
		return AttenuatorDao.queryPage(model);
	}

	
	@Override
	public Long querySize(AttenuatorQueryModel model) {
		// TODO Auto-generated method stub
		return AttenuatorDao.querySize(model);
	}


	//目前 可以 实现 全部 数据的 导出 ，查询数据 导出 不能实现
	@Override
	public Workbook exportExcel(File template,AttenuatorQueryModel queryModel) throws IOException,
			InvalidFormatException {
		// TODO Auto-generated method stub
		List<Attenuator> os = AttenuatorDao.queryList(queryModel);		
		
		Workbook wb = null;
		Sheet sheet = null;
		// 判断模板是否存在
		if (template == null) {
			// 不存在，则自己到处抬头行
			wb = new HSSFWorkbook();
			sheet = wb.createSheet("衰减器统计表");// 注意获取sheet的方式不一样
			Row titleRow = sheet.createRow(0);			
			
			titleRow.createCell(0).setCellValue("序号");
			titleRow.createCell(1).setCellValue("站点名称");
			titleRow.createCell(2).setCellValue("站点代码");
			titleRow.createCell(3).setCellValue("所属区域");
			///titleRow.createCell(4).setCellValue("天线代码");
			titleRow.createCell(4).setCellValue("中频发射固定/可调衰减值");
			titleRow.createCell(5).setCellValue("中频接收固定/可调衰减值");		
			titleRow.createCell(6).setCellValue("备注信息");			
			
		} else {
			// 存在，则直接获取模板中的抬头样式		
			wb = ExcelPoiTools.create(new FileInputStream(template));
			if (!CollectionUtils.isEmpty(os)) {
				sheet = wb.getSheetAt(0);
			}			
		}
		if (CollectionUtils.isEmpty(os)) {		
			return wb;
		}
		
		for (int i = 0; i < os.size(); i++) {
			Row row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(i + 1);	
			row.createCell(1).setCellValue(os.get(i)
					.getSite()== null ? "" : os.get(i)
							.getSite().getSiteName());
			
			row.createCell(2).setCellValue(os.get(i)
					.getSite()== null ? "" : os.get(i)
							.getSite().getSiteCode());
			
			
			row.createCell(3).setCellValue(os.get(i)
					.getSite()== null ? "" :os.get(i)
							.getSite().getBureau().toString());	
			
		/*	row.createCell(4).setCellValue(
					os.get(i).getAntenna() == null ? "" : (os.get(i)
							.getAntenna().getAntennaCode()== null ? "" : os
							.get(i).getAntenna().getAntennaCode()));	
			*/
			
			row.createCell(4).setCellValue(
					os.get(i).getSolidLanch() == null ? "":os.get(i).getSolidLanch() );
		
			row.createCell(5).setCellValue(
					os.get(i).getSolidReceive() == null ? "":os.get(i).getSolidReceive() );
	
			row.createCell(6).setCellValue(
					os.get(i).getInfo()== null ? "":os.get(i).getInfo());	


		}
		return wb;
	}





	@Override
	public Workbook exportExcel(File template) throws IOException,
			InvalidFormatException {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
//	public Workbook exportExcel(File template) throws IOException,
//			InvalidFormatException {
//		// TODO Auto-generated method stub
//		List<Attenuator> os = AttenuatorDao.queryList(new AttenuatorQueryModel());		
//		if (CollectionUtils.isEmpty(os)) {
//			System.out.println("CollectionUtils.isEmpty(os) ");
//			return null;
//		}
//		Workbook wb = null;
//		Sheet sheet = null;
//		// 判断模板是否存在
//		if (template == null) {
//			// 不存在，则自己到处抬头行
//			wb = new HSSFWorkbook();
//			sheet = wb.createSheet("衰减器统计表");// 注意获取sheet的方式不一样
//			Row titleRow = sheet.createRow(0);
//			titleRow.createCell(0).setCellValue("设备编码");
//			titleRow.createCell(1).setCellValue("设备名称");
//			titleRow.createCell(2).setCellValue("节点类型");
//			titleRow.createCell(3).setCellValue("中频发射固定/可调衰减值");
//			titleRow.createCell(4).setCellValue("中频接收固定/可调衰减值");
//			titleRow.createCell(5).setCellValue("站点名称");
//			titleRow.createCell(6).setCellValue("三字代码");					
//		} else {
//			// 存在，则直接获取模板中的抬头样式		
//			wb = ExcelPoiTools.create(new FileInputStream(template));
//			if (!CollectionUtils.isEmpty(os)) {
//				sheet = wb.getSheetAt(0);
//			}			
//		}
//		if (CollectionUtils.isEmpty(os)) {		
//			return wb;
//		}
//		
//		for (int i = 0; i < os.size(); i++) {
//			Row row = sheet.createRow(i + 1);
//			// 给这一行的第一列赋值
//			// 设备编码
//		
//			row.createCell(0).setCellValue(
//					os.get(i).getEquip() == null ? "" : (os.get(i)
//							.getEquip().getCode() == null ? "" : os
//							.get(i).getEquip().getCode()));
//			
//			
//			// 设备名称
//		//	row.createCell(1).setCellValue(os.get(i).getEquip().getName());
//			row.createCell(1).setCellValue(
//					os.get(i).getEquip() == null ? "" : (os.get(i)
//							.getEquip().getName() == null ? "" : os
//							.get(i).getEquip().getName()));
//			
//			//节点类型
//			//row.createCell(2).setCellValue(os.get(i).getEquip().getType().toString());
//			//中频发射固定/可调衰减值
//			//row.createCell(3).setCellValue(os.get(i).getSolidLanch());
//		
//			row.createCell(2).setCellValue(
//					os.get(i).getEquip() == null ? "" : (os.get(i)
//							.getEquip().getModel() == null ? "" : os
//							.get(i).getEquip().getModel()));
//			
//			row.createCell(3).setCellValue(
//					os.get(i).getSolidLanch() == null ? "":os.get(i).getSolidLanch() );
//			
//			//中频接收固定/可调衰减值
//			//row.createCell(4).setCellValue(os.get(i).getSolidReceive());
//			
//			row.createCell(4).setCellValue(
//					os.get(i).getSolidReceive() == null ? "":os.get(i).getSolidReceive() );
//			//站点名称
//		//	row.createCell(5).setCellValue(os.get(i).getEquip().getSatellite().getSiteName());
//			
//			
//			// 站名
//			row.createCell(5).setCellValue(
//					os.get(i).getEquip() == null ? "" : (os.get(i)
//							.getEquip().getSatellite() == null ? "" : os
//							.get(i).getEquip().getSatellite().getSiteName()));
//			//三字代码
//		//	row.createCell(6).setCellValue(os.get(i).getEquip().getSatellite().getSiteCode());
//			// 三字代码
//			row.createCell(6).setCellValue(
//					os.get(i).getEquip() == null ? "" : (os.get(i)
//							.getEquip().getSatellite() == null ? "" : os
//							.get(i).getEquip().getSatellite().getSiteCode()));
//
//		}
//		return wb;
//	}

}