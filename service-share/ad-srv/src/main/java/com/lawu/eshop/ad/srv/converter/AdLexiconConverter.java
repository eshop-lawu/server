package com.lawu.eshop.ad.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.ad.dto.AdLexiconDTO;
import com.lawu.eshop.ad.srv.bo.AdLexiconBO;
import com.lawu.eshop.ad.srv.domain.AdLexiconDO;

public class AdLexiconConverter {
	
	/**
	 * DOS转BOS
	 * @param list
	 * @return
	 */
	public static List<AdLexiconBO> convertBOS(List<AdLexiconDO> list){
		if (list == null) {
	         return null;
	    }
		List<AdLexiconBO> BOS=new ArrayList<AdLexiconBO>();
		for (AdLexiconDO adLexiconDO : list) {
			AdLexiconBO bo=new AdLexiconBO();
			bo.setTitle(adLexiconDO.getTitle());
			BOS.add(bo);
		}
		return BOS;
		
	}

	/**
	 * BOS 转DTOS
	 * @param bOS
	 * @return
	 */
	public static List<AdLexiconDTO> convertDTOS(List<AdLexiconBO> bOS) {
		List<AdLexiconDTO> DTOS=new ArrayList<AdLexiconDTO>();
		if (bOS == null) {
            return DTOS;
	    }
		for (AdLexiconBO adLexiconBO : bOS) {
			AdLexiconDTO adLexiconDTO=new AdLexiconDTO();
			
			adLexiconDTO.setTitle(adLexiconBO.getTitle());
			DTOS.add(adLexiconDTO);
		}
		return DTOS;
	}

}
