package com.lawu.eshop.agent.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.agent.dto.AgentUserListDTO;
import com.lawu.eshop.agent.dto.LoginUserDTO;
import com.lawu.eshop.agent.srv.bo.AgentUserBO;
import com.lawu.eshop.agent.srv.domain.AgentUserDO;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
public class AgentUserConverter {

    public static AgentUserBO convertBO(AgentUserDO agentUserDO) {
        if (agentUserDO == null) {
            return null;
        }
        AgentUserBO agentUserBO = new AgentUserBO();
        agentUserBO.setId(agentUserDO.getId());
        agentUserBO.setStatus(agentUserDO.getStatus());
        agentUserBO.setNum(agentUserDO.getNum());
        agentUserBO.setAccount(agentUserDO.getAccount());
        agentUserBO.setGmtCreate(agentUserDO.getGmtCreate());
        agentUserBO.setName(agentUserDO.getName());
        agentUserBO.setRegionPath(agentUserDO.getRegionPath());
        agentUserBO.setRegionName(agentUserDO.getRegionName());
        agentUserBO.setMobile(agentUserDO.getMobile());
        return agentUserBO;
    }

    public static LoginUserDTO convertDTO(AgentUserBO userBO) {
        if (userBO == null) {
            return null;
        }
        LoginUserDTO userDTO = new LoginUserDTO();
        userDTO.setId(userBO.getId());
        userDTO.setNum(userBO.getNum());
        userDTO.setAccount(userBO.getAccount());
        userDTO.setMobile(userBO.getMobile());
        userDTO.setRegionPath(userBO.getRegionPath());
        return userDTO;
    }

    public static List<AgentUserListDTO> convertDTOS(List<AgentUserBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<AgentUserListDTO> listDTOS = new ArrayList<>();
        AgentUserListDTO userListDTO;
        for (AgentUserBO agentUserBO : records) {
            userListDTO = new AgentUserListDTO();
            userListDTO.setId(agentUserBO.getId());
            userListDTO.setGmtCreate(agentUserBO.getGmtCreate());
            userListDTO.setAccount(agentUserBO.getAccount());
            userListDTO.setMobile(agentUserBO.getMobile());
            userListDTO.setName(agentUserBO.getName());
            userListDTO.setRegionName(agentUserBO.getRegionName());
            userListDTO.setRegionPath(agentUserBO.getRegionPath());
            listDTOS.add(userListDTO);
        }
        return listDTOS;
    }
}
