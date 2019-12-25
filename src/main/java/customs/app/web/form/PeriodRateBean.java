package customs.app.web.form;

import customs.app.entity.PeriodRate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="每旬匯率")
public class PeriodRateBean {

    
    @ApiModelProperty(dataType="string", value="幣別")
	private String currency;
    
    @ApiModelProperty(dataType="int", value="年")
	private int year;
    
    @ApiModelProperty(dataType="int", value="月")
	private int month;
    
    @ApiModelProperty(dataType="int", value="旬")
	private int period;
    
    @ApiModelProperty(dataType="double", value="買進")
	private double buyPrice;
    
    @ApiModelProperty(dataType="double", value="賣出")
	private double sellPrice;
    
    public PeriodRateBean() {
        super();
    }
    
    public PeriodRateBean(customs.app.entity.PeriodRate entity) {
        super();
        this.currency = entity.getCurrency();
        this.year = entity.getYear();
        this.month = entity.getMonth();
        this.period = entity.getPeriod();
        this.buyPrice = entity.getBuyPrice();
        this.sellPrice = entity.getSellPrice();
    }
    
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
    
    public PeriodRate toEntity() {
        return new PeriodRate(this.currency, this.year, this.month,
                this.period, this.buyPrice, this.sellPrice);
    }

}
