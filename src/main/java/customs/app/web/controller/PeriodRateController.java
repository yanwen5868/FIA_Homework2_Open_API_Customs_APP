package customs.app.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import customs.app.entity.PeriodRate;
import customs.app.service.PeriodRateService;
import customs.app.web.form.PeriodRateBean;
import customs.app.web.form.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(tags="每旬匯率資料API")
@RequestMapping("/PeriodRate")
public class PeriodRateController {
    
    // private static final Logger logger = Logger.getLogger(PeriodRateController.class);
    
    @Autowired
    private PeriodRateService PeriodRateService;
       
    @ApiResponses(value={
            @ApiResponse(code=500, message="查詢異常"),
            @ApiResponse(code=200, message="查詢成功"),
    })
    @ApiOperation(value="取得每旬匯率資料")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PeriodRateBean>> listAll(PageBean pageBean) {
        Map<String, Object> query = getQuery(null, -1, -1, -1, pageBean);
        List<PeriodRate> entitys = PeriodRateService.listByCondition(query);
        if (entitys != null && entitys.size() > 0) {
            List<PeriodRateBean> entityBeans = new ArrayList<PeriodRateBean>();
            for (PeriodRate entity : entitys) {
                entityBeans.add(new PeriodRateBean(entity));
            }
            return ResponseEntity.ok(entityBeans);
        }
        return ResponseEntity.status(500).build();
    }
     
    @ApiResponses(value={
            @ApiResponse(code=500, message="查詢異常"),
            @ApiResponse(code=200, message="查詢成功"),
    })
    @ApiImplicitParams(value={
            @ApiImplicitParam(name="currency", value="幣別", dataType="String", required=false, paramType="query"),
            @ApiImplicitParam(name="year", value="民國年", dataType="int", required=false, paramType="query"),
            @ApiImplicitParam(name="month", value="月", dataType="int", required=false, paramType="query"),
            @ApiImplicitParam(name="period", value="旬", dataType="int", required=false, paramType="query", allowableValues="1,2,3"),
    })
    @ApiOperation(value="依查詢條件取得每旬匯率資料")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PeriodRateBean>> listByCondition(String currency, int year, int month, int period,
            PageBean pageBean) 
    {
        Map<String, Object> query = getQuery(currency, year, month, period, pageBean);
        List<PeriodRate> entitys = PeriodRateService.listByCondition(query);
        if (entitys != null && entitys.size() > 0) {
            List<PeriodRateBean> entityBeans = new ArrayList<PeriodRateBean>();
            for (PeriodRate entity : entitys) {
                entityBeans.add(new PeriodRateBean(entity));
            }
            return ResponseEntity.ok(entityBeans);
        }
        return ResponseEntity.status(500).build();
    }
    
    @ApiResponses(value={
            @ApiResponse(code=500, message="新增失敗"),
            @ApiResponse(code=200, message="新增成功"),
    })
    @ApiOperation(value="新增每旬匯率資料")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PeriodRateBean> create(PeriodRateBean bean) {
    	
        Map<String, Object> query = getQuery(bean.getCurrency(), bean.getYear(), bean.getMonth(), bean.getPeriod(), null);
        List<PeriodRate> entitys = PeriodRateService.listByCondition(query);
        
        if (entitys.size() == 0) {
            int row = PeriodRateService.create(bean.toEntity());
            if (row == 1) {
                return ResponseEntity.ok(bean);
            }
        }    
        return ResponseEntity.status(500).build();
    }
    
    
    @ApiResponses(value={
            @ApiResponse(code=500, message="更新失敗"),
            @ApiResponse(code=200, message="更新成功"),
    })
    @ApiImplicitParams(value={
            @ApiImplicitParam(name="currency", value="幣別", dataType="String", required=true, paramType="path"),
            @ApiImplicitParam(name="year", value="民國年", dataType="int", required=true, paramType="query"),
            @ApiImplicitParam(name="month", value="月", dataType="int", required=true, paramType="query"),
            @ApiImplicitParam(name="period", value="旬", dataType="int", required=true, paramType="query", allowableValues="1,2,3"),
            @ApiImplicitParam(name="buyPrice", value="買進價格", dataType="double", required=true, paramType="query"),
            @ApiImplicitParam(name="sellPrice", value="賣出價格", dataType="double", required=true, paramType="query"),
    })
    @ApiOperation(value="更新每旬匯率資料")
    @RequestMapping(value = "/{currency}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<PeriodRateBean> update(@PathVariable String currency, int year, int month, 
            int period, double buyPrice, double sellPrice) {
    	
        Map<String, Object> query = getQuery(currency, year, month, period, null);
        PeriodRate entity = PeriodRateService.listByCondition(query).get(0);
    	
        if (entity != null) {
            if (StringUtils.isNotBlank(currency)) {
                entity.setCurrency(currency.trim());
            }
            entity.setYear(year);
            entity.setMonth(month);
            entity.setPeriod(period);
            entity.setBuyPrice(buyPrice);
            entity.setSellPrice(sellPrice);
   
            int row = PeriodRateService.update(entity);
            if (row == 1) {
                return ResponseEntity.ok(new PeriodRateBean(entity));
            }
        }
        return ResponseEntity.status(500).build();
    }
    
//    @ApiResponses(value={
//            @ApiResponse(code=500, message="刪除失敗"),
//            @ApiResponse(code=200, message="刪除成功"),
//    })
//    @ApiOperation(value="刪除非營利事業單位統一編號資訊")
//    @RequestMapping(value = "/{ban}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public ResponseEntity<String> delete(
//            @ApiParam(value="非營利事業單位統一編號", required=true) @PathVariable String ban) {
//        int row = PeriodRateService.delete(ban);
//        if (row == 1) {
//            return ResponseEntity.ok("Delete Successful");
//        }
//        return ResponseEntity.status(500).build();
//    }
    
    private Map<String, Object> getQuery(String currency, int year, int month, int period, PageBean pageBean) {
        Map<String, Object> query = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(currency)) {
            query.put(PeriodRate.PARAM_CURRENCY, currency.trim());
        }
        if (year != -1){
            query.put(PeriodRate.PARAM_YEAR, year);
        }        
        if (month != -1){        
           query.put(PeriodRate.PARAM_MONTH, month);
        }
        if (period != -1){
            query.put(PeriodRate.PARAM_PERIOD, period);
        }
        if (pageBean == null)
        	pageBean = new PageBean();
        
        if (pageBean.getLimit() != null && (pageBean.getLimit() < 0 || pageBean.getLimit() > PageBean.DEFAULT_LIMIT)) {
            pageBean.setLimit(PageBean.DEFAULT_LIMIT);
        }        
        query.put(PageBean.PARAM_LIMIT, pageBean.getLimit());
        
        if (pageBean.getOffset() == null || pageBean.getOffset() < 0) {
            pageBean.setOffset(PageBean.DEFAULT_OFFSET);
        }
        query.put(PageBean.PARAM_OFFSET, pageBean.getOffset());
        
        return query;
    }
    
    
}
