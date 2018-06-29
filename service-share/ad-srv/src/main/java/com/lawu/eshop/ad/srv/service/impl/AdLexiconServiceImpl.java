package com.lawu.eshop.ad.srv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.srv.bo.AdLexiconBO;
import com.lawu.eshop.ad.srv.converter.AdLexiconConverter;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.AdLexiconDO;
import com.lawu.eshop.ad.srv.domain.AdLexiconDOExample;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.AdLexiconDOMapper;
import com.lawu.eshop.ad.srv.service.AdLexiconService;

@Service
public class AdLexiconServiceImpl implements AdLexiconService {
	
	@Autowired
	private AdLexiconDOMapper adLexiconDOMapper;
	
	@Autowired
	private AdDOMapper adDOMapper;

	@Override
	public Integer save(String title) {
		AdLexiconDO adLexicon=new AdLexiconDO();
		adLexicon.setGmtCreate(new Date());
		adLexicon.setTitle(title);
		int i=adLexiconDOMapper.insert(adLexicon);
		return i;
	}

	@Override
	public List<AdLexiconBO> selectList(Long adId) {
		AdDO adDO = adDOMapper.selectByPrimaryKey(adId);
		AdLexiconDOExample example = new AdLexiconDOExample();
		List<AdLexiconDO> dos = adLexiconDOMapper.selectByExample(example);
		List<AdLexiconDO> newDOS = new ArrayList<>();
	
		if(dos.size()<3){
			for (AdLexiconDO adLexiconDO : dos) {
				newDOS.add(adLexiconDO);
			}
			
		}else{
			int s = 0;
			String str = "";
			while (true) {
				Integer r = ThreadLocalRandom.current().nextInt(0, dos.size());
				if (r != s) {
					AdLexiconDO adLexiconDO = dos.get(r);
					if (adLexiconDO.getTitle() != str){
						newDOS.add(adLexiconDO);
						str = adLexiconDO.getTitle();
					}
				}
				s = r;
				if (newDOS.size() > 2)
					break;
			}
		}
		List<AdLexiconBO> list = AdLexiconConverter.convertBOS(newDOS);
		AdLexiconBO bo = new AdLexiconBO();
		bo.setTitle(adDO.getTitle());
		list.add(bo);
		Collections.shuffle(list); // 随机打乱顺序
		return list;
	}

}
