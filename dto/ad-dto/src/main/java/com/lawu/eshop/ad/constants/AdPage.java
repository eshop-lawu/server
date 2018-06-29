package com.lawu.eshop.ad.constants;

import java.util.ArrayList;
import java.util.List;

public class AdPage<T> {
	
	private List<T> result = new ArrayList<T>();
	
	 /**
     * 公用方法，手动分页
     * @param list
     * @param pageSize
     * @param currentPage
     * @return
     */
    public List<T> page(List<T> list ,Integer pageSize, Integer currentPage){
    	//数据总数
    	Integer totalCount = list.size();
    	//总的页数
    	Integer pageCount = 0;  
    	//每页显示的总数
    	Integer endNum = pageSize;
    	//当前页码
    	Integer startNum = currentPage;
    	/*计算出总共能分成多少页*/
    	if (totalCount % endNum > 0){
    	    pageCount = totalCount / endNum + 1;
    	}else {
    	    pageCount = totalCount / endNum;
    	}
    	if(totalCount > 0){
    	    if(startNum <= pageCount){
    	       if(startNum == 1){
			    	if(totalCount <= endNum){
			    	   //截止到总的数据条数(当前数据不足一页，按一页显示)，这样才不会出现数组越界异常
			    	    result = list.subList(0, totalCount);
			    	}else{
			    		result = list.subList(0, endNum);
			    	}
    	       }else{
       	    	//截取起始下标
       	    	int fromIndex = (startNum - 1) * endNum;
       	    	//截取截止下标
       	    	int toIndex = startNum * endNum;
       	    	/*计算截取截止下标*/
       	    	if ((totalCount - toIndex) % endNum >= 0){
       	    		toIndex = startNum * endNum;
       	    	}else{
       	    		toIndex = (startNum - 1) * endNum + (totalCount % endNum);
       	    	}
       	    	if (totalCount >= toIndex){
       	    		result = list.subList(fromIndex, toIndex);
       	    	}
           	  
           	}
    	    }
    	    
    	}
    	return result;
    	
    }

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

    
}
