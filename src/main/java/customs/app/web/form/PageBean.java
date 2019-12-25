package customs.app.web.form;

import io.swagger.annotations.ApiParam;

public class PageBean {
    
    public static final String PARAM_LIMIT = "limit";
    
    public static final String PARAM_OFFSET = "offset";
    
    public static final int DEFAULT_LIMIT = 100;
    
    public static final int DEFAULT_OFFSET = 0;
    
    
    @ApiParam(value="限制回傳資料筆數", required=true, example="100")
    private Integer limit;
    
    @ApiParam(value="從第幾筆開始回傳,可與limit使用達到分頁目的", required=true, example="0")
    private Integer offset;

    public PageBean() {
        super();
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
    

}
