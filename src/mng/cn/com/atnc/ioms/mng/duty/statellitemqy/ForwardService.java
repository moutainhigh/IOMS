package cn.com.atnc.ioms.mng.duty.statellitemqy;

import cn.com.atnc.common.entity.Pagination;
import cn.com.atnc.ioms.entity.duty.environment.EnvironmentEquipCheck;
import cn.com.atnc.ioms.entity.duty.satellite.Forward;
import cn.com.atnc.ioms.model.duty.environment.EnvironmentCheckQueryModel;
import cn.com.atnc.ioms.model.duty.statellite.mqy.ForwardModel;

public interface ForwardService {
	/**
	 * 保存
	 * 
	 * @author shijiyong
	 * @param txjddatastate
	 *            保存内容
	 * @return
	 * @throws
	 */
	ForwardModel save(ForwardModel forward);

	/**
	 * 显示
	 * 
	 * @author shijiyong
	 * @param queryModel
	 *            查询条件
	 * @return Pagination
	 * @throws
	 */
	Pagination queryPageByModel(EnvironmentCheckQueryModel queryModel);

	/**
	 * 根据id查找TxjdDataState
	 * 
	 * @author shijiyong
	 * @param datastate
	 * @param txjddatastate
	 * @return void
	 * @throws
	 */
	Forward findById(String id);

	/**
	 * 根据id查找EnvironmentEquipCheck
	 * 
	 * @author shijiyong
	 * @param datastate
	 * @param txjddatastate
	 * @return void
	 * @throws
	 */
	EnvironmentEquipCheck findByIden(String checkid);

	/**
	 * 更新
	 * 
	 * @author shijiyong
	 * @param datastate
	 * @param txjddatastate
	 * @return void
	 * @throws
	 */
	ForwardModel update(Forward forward, EnvironmentEquipCheck enviroment,
			ForwardModel forwardmodel);

	/**
	 * 删除-根据网管数据哭内容
	 * 
	 * @author shijiyong 2016年10月21日 上午9:01:08
	 * @param enviroment
	 *            删除内容
	 * @return void
	 * @throws
	 */
	void delete(Forward forward, EnvironmentEquipCheck enviroment);

}
