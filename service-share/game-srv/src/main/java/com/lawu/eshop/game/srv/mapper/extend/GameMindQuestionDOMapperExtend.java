package com.lawu.eshop.game.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface GameMindQuestionDOMapperExtend {
	
	List<String> rebuildQuestion(Byte lever,RowBounds rowBounds);

}
