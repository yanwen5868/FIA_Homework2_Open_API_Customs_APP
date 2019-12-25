package customs.app.entity;

public class PeriodRate  {
    
    public static final String PARAM_CURRENCY = "currency";    
    public static final String PARAM_YEAR = "year";
    public static final String PARAM_MONTH = "month";    
    public static final String PARAM_PERIOD = "period";
	
	private String currency;
	private int year;
	private int month;
	private int period;
	private double buyPrice;
	private double sellPrice;
    

    public PeriodRate() {
        super();
    }

	public PeriodRate(String currency, int year, int month, int period, double buyPrice, double sellPrice) {
		super();
		this.currency = currency;
		this.year = year;
		this.month = month;
		this.period = period;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
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

    @Override
    public String toString() {
        return "PeriodRate [currency=" + currency + ", year=" + year + ", month=" + month + ", period=" + period
                + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + "]";
    }
    
}
