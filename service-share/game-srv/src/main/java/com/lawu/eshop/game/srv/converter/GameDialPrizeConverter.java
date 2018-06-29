package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.constants.GameDialPrizeTypeEnum;
import com.lawu.eshop.game.dto.GameDialPrizeAttendDTO;
import com.lawu.eshop.game.dto.GameDialPrizeDetailDTO;
import com.lawu.eshop.game.dto.GameDialPrizeInfoDTO;
import com.lawu.eshop.game.dto.GameDialPrizeLotteryDTO;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDO;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialPrizeConverter {

    public static GameDialPrizeBO converBO(GameDialPrizeDO prizeDO) {
        if (prizeDO == null) {
            return null;
        }

        GameDialPrizeBO prizeBO = new GameDialPrizeBO();
        prizeBO.setId(prizeDO.getId());
        prizeBO.setGameDialId(prizeDO.getGameDialId());
        prizeBO.setName(prizeDO.getName());
        prizeBO.setImgPath(prizeDO.getImgPath());
        prizeBO.setPrice(prizeDO.getPrice());
        prizeBO.setNumber(prizeDO.getNumber());
        prizeBO.setInventory(prizeDO.getInventory());
        prizeBO.setStatus(prizeDO.getStatus());
        prizeBO.setIsAddress(prizeDO.getIsAddress());
        prizeBO.setIsSendPrize(prizeDO.getIsSendPrize());
        prizeBO.setPrizeType(prizeDO.getPrizeType());
        prizeBO.setFreightPrice(prizeDO.getFreightPrice());
        prizeBO.setRate(prizeDO.getRate());
        prizeBO.setGmtModified(prizeDO.getGmtModified());
        prizeBO.setGmtCreate(prizeDO.getGmtCreate());
        return prizeBO;
    }
    
    
    public static List<GameDialPrizeBO> converBOS(List<GameDialPrizeDO> prizeDOS) {
        List<GameDialPrizeBO> prizeBOS = new ArrayList<>();
        if (prizeDOS == null || prizeDOS.isEmpty()) {
            return prizeBOS;
        }

        for (GameDialPrizeDO prizeDO : prizeDOS) {
            prizeBOS.add(converBO(prizeDO));
        }
        return prizeBOS;
    }

    public static List<GameDialPrizeInfoDTO> converPrizeInfoDTOS(List<GameDialPrizeBO> prizeBOS) {
        List<GameDialPrizeInfoDTO> infoDTOS = new ArrayList<>();
        if (prizeBOS == null || prizeBOS.isEmpty()) {
            return infoDTOS;
        }

        for (GameDialPrizeBO prizeBO : prizeBOS) {
            GameDialPrizeInfoDTO infoDTO = new GameDialPrizeInfoDTO();
            infoDTO.setName(prizeBO.getName());
            infoDTO.setImgPath(prizeBO.getImgPath());
            infoDTOS.add(infoDTO);
        }
        return infoDTOS;
    }
	
	public static List<GameDialPrizeAttendDTO> convertGameDialPrizeDTOS(List<GameDialPrizeBO> list) {
		List<GameDialPrizeAttendDTO> listDTO = new ArrayList<>();
		if(list.isEmpty()){
			return listDTO;
		}
		for (GameDialPrizeBO gameDialPrizeBO : list) {
			GameDialPrizeAttendDTO dto =converDTO(gameDialPrizeBO);
			listDTO.add(dto);
		}
		return listDTO;
	}
	
	
	 public static GameDialPrizeAttendDTO converDTO(GameDialPrizeBO prizeBO) {
	        if (prizeBO == null) {
	            return null;
	        }
	        GameDialPrizeAttendDTO prizeDTO = new GameDialPrizeAttendDTO();
	        prizeDTO.setId(prizeBO.getId());
	        prizeDTO.setGameDialId(prizeBO.getGameDialId());
	        prizeDTO.setName(prizeBO.getName());
	        prizeDTO.setImgPath(prizeBO.getImgPath());
	        prizeDTO.setPrice(prizeBO.getPrice());
	        prizeDTO.setNumber(prizeBO.getNumber());
	        prizeDTO.setInventory(prizeBO.getInventory());
	        prizeDTO.setStatus(GameDialPrizeStatusEnum.getEnum(prizeBO.getStatus()));
	        prizeDTO.setIsAddress(prizeBO.getIsAddress());
	        prizeDTO.setIsSendPrize(prizeBO.getIsSendPrize());
	        prizeDTO.setPrizeType(GameDialPrizeTypeEnum.getEnum(prizeBO.getPrizeType()));
	        prizeDTO.setFreightPrice(prizeBO.getFreightPrice());
	        prizeDTO.setRate(prizeBO.getRate());
	        prizeDTO.setGmtModified(prizeBO.getGmtModified());
	        prizeDTO.setGmtCreate(prizeBO.getGmtCreate());
	        return prizeDTO;
	    }

    public static List<GameDialPrizeLotteryDTO> converPrizeLotteryDTOS(List<GameDialPrizeBO> prizeBOS) {
        List<GameDialPrizeLotteryDTO> lotteryDTOS = new ArrayList<>();
        if (prizeBOS == null || prizeBOS.isEmpty()) {
            return lotteryDTOS;
        }

        for (GameDialPrizeBO prizeBO : prizeBOS) {
            GameDialPrizeLotteryDTO lotteryDTO = new GameDialPrizeLotteryDTO();
            lotteryDTO.setId(prizeBO.getId());
            lotteryDTO.setRate(prizeBO.getRate());
            lotteryDTOS.add(lotteryDTO);
        }
        return lotteryDTOS;
    }

    public static GameDialPrizeDetailDTO converLotteryPrizeDTO(GameDialPrizeBO prizeBO) {
        if (prizeBO == null) {
            return null;
        }

        GameDialPrizeDetailDTO detailDTO = new GameDialPrizeDetailDTO();
        detailDTO.setName(prizeBO.getName());
        detailDTO.setImgPath(prizeBO.getImgPath());
        detailDTO.setIsAddress(prizeBO.getIsAddress());
        detailDTO.setIsSendPrize(prizeBO.getIsSendPrize());
        detailDTO.setFreightPrice(prizeBO.getFreightPrice());
        return detailDTO;
    }
}
