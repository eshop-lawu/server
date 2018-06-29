package com.lawu.eshop.cache.param;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/14.
 */
public class GameMindAttendRecordResultParam {
    @ApiModelProperty(value = "参与ID")
    private Long mindAttendId;

    @ApiModelProperty(value = "问题ID")
    private Long questionId;

    @ApiModelProperty(value = "答案下标")
    private Integer rightAnswer;

    @ApiModelProperty(value = "答案")
    private String answer;

    @ApiModelProperty(value = "是否正确")
    private Boolean flag;

    @ApiModelProperty(value = "分数")
    private Integer point;

    @ApiModelProperty(value = "用时")
    private Integer useTime;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getMindAttendId() {
        return mindAttendId;
    }

    public void setMindAttendId(Long mindAttendId) {
        this.mindAttendId = mindAttendId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
