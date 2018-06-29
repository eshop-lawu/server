package com.lawu.eshop.member.api.service.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.lawu.eshop.mall.dto.IndustryTypeDTO;
import com.lawu.eshop.member.api.service.IndustryTypeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import feign.hystrix.FallbackFactory;

/**
 * Created by john on 2017/12/7.
 */
@Component
public class IndustryTypeServiceHystrixFactory extends BaseController implements FallbackFactory<IndustryTypeService>{
    @Override
    public IndustryTypeService create(Throwable throwable) {
        return new IndustryTypeService(){

            @Override
            public Result<List<IndustryTypeDTO>> listIndustryType() {
                System.out.println("======================" +throwable);
                List<IndustryTypeDTO> list=new ArrayList<>();
                IndustryTypeDTO dto=new IndustryTypeDTO();
                dto.setId(-1);
                list.add(dto);
                return successGet(list);
            }

            @Override
            public Result<List<IndustryTypeDTO>> listIndustryTypeByParentId(@PathVariable("parentId") Short parentId) {
                return null;
            }
        };
    }
}
