package cn.com.atnc.ioms.mng.duty.statelliteauto.day;

import java.lang.reflect.InvocationTargetException;

import cn.com.atnc.common.entity.Pagination;
import cn.com.atnc.ioms.entity.duty.satelliteauto.day.SatTxjdOverseaHW;
import cn.com.atnc.ioms.model.duty.satelliteauto.SatTxjdOverseaHwModel;
import cn.com.atnc.ioms.model.duty.satelliteauto.SatelliteAutoModel;

/**
 * 通信基地-境外站-主站华为路由器状态 业务层接口
 *
 * @author 段衍林
 * @2016年11月11日 下午4:04:32
 */
public interface SatTxjdOverseaHWService {

	/**
	 * 通信基地-境外站-主站华为路由器状态 卫星日检自检分页查询
	 * 
	 * @author 段衍林
	 * @2016年11月11日 下午4:04:32
	 * @param queryModel
	 *            卫星自检model
	 * @return Pagination
	 */
	Pagination queryPageByModel(SatelliteAutoModel queryModel);

	/**
	 * 通信基地-境外站-主站华为路由器状态 冒泡查询
	 * 
	 * @author 段衍林
	 * @2016年11月11日 上午10:04:16
	 * @param queryModel
	 * @return Pagination
	 */
	int getAjaxLoad();

	/**
	 * 保存或更新
	 * 
	 * @author 段衍林
	 * @2016年11月21日 下午1:27:55
	 * @param satTxjdOverseaCdmModel
	 *            void
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	SatTxjdOverseaHW saveOrUpdate(SatTxjdOverseaHwModel satTxjdOverseaHwModel)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	/**
	 * 删除
	 * 
	 * @author 段衍林
	 * @2016年11月21日 下午2:12:07
	 * @param satTxjdOverseaCdmModel
	 *            void
	 */
	void delete(SatTxjdOverseaHwModel satTxjdOverseaHwModel);
}