package cn.com.atnc.ioms.enums.duty.atm;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonValue;

import cn.com.atnc.ioms.enums.operstat.atm.EnumModel;

/**
 * 正常异常
 * 
 * @author shijiyong
 * @date 2016年9月26日 上午10:20:53
 * @since 1.0.0
 */
public enum StatusResultEnum {

	NORMAL("正常"), UNNORMAL("异常");

	private String value;

	public String getValue() {
		return value;
	}

	private StatusResultEnum(String value) {
		this.value = value;
	}

	public static StatusResultEnum getInstance(String value) {
		StatusResultEnum[] hn = StatusResultEnum.values();
		for (StatusResultEnum status : hn) {
			if (StringUtils.equalsIgnoreCase(status.getValue(), value)) {
				return status;
			}
		}
		throw new IllegalArgumentException("value值非法，没有符合工作状态的枚举对象");
	}

	@JsonValue
	public EnumModel toEnumModel() {
		EnumModel em = new EnumModel();
		em.setName(this.name());
		em.setOrdinal(this.ordinal());
		em.setValue(this.getValue());

		return em;
	}
}
