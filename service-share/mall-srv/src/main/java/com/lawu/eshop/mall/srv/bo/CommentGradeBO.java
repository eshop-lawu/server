package com.lawu.eshop.mall.srv.bo;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */
public class CommentGradeBO {

    private Double averageConsumeAmount;

    private Double avgGrade;

    private Double goodGrad;

    public Double getAverageConsumeAmount() {
        return averageConsumeAmount;
    }

    public void setAverageConsumeAmount(Double averageConsumeAmount) {
        this.averageConsumeAmount = averageConsumeAmount;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Double getGoodGrad() {
        return goodGrad;
    }

    public void setGoodGrad(Double goodGrad) {
        this.goodGrad = goodGrad;
    }
}
