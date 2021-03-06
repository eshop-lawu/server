package com.lawu.eshop.user.srv.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MerchantStoreProfileDOExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public MerchantStoreProfileDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Long value) {
            addCriterion("merchant_id =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Long value) {
            addCriterion("merchant_id <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Long value) {
            addCriterion("merchant_id >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("merchant_id >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Long value) {
            addCriterion("merchant_id <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("merchant_id <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Long> values) {
            addCriterion("merchant_id in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Long> values) {
            addCriterion("merchant_id not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Long value1, Long value2) {
            addCriterion("merchant_id between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andRegNumberIsNull() {
            addCriterion("reg_number is null");
            return (Criteria) this;
        }

        public Criteria andRegNumberIsNotNull() {
            addCriterion("reg_number is not null");
            return (Criteria) this;
        }

        public Criteria andRegNumberEqualTo(String value) {
            addCriterion("reg_number =", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberNotEqualTo(String value) {
            addCriterion("reg_number <>", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberGreaterThan(String value) {
            addCriterion("reg_number >", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberGreaterThanOrEqualTo(String value) {
            addCriterion("reg_number >=", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberLessThan(String value) {
            addCriterion("reg_number <", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberLessThanOrEqualTo(String value) {
            addCriterion("reg_number <=", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberLike(String value) {
            addCriterion("reg_number like", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberNotLike(String value) {
            addCriterion("reg_number not like", value, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberIn(List<String> values) {
            addCriterion("reg_number in", values, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberNotIn(List<String> values) {
            addCriterion("reg_number not in", values, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberBetween(String value1, String value2) {
            addCriterion("reg_number between", value1, value2, "regNumber");
            return (Criteria) this;
        }

        public Criteria andRegNumberNotBetween(String value1, String value2) {
            addCriterion("reg_number not between", value1, value2, "regNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateIsNull() {
            addCriterion("license_indate is null");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateIsNotNull() {
            addCriterion("license_indate is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateEqualTo(Date value) {
            addCriterionForJDBCDate("license_indate =", value, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateNotEqualTo(Date value) {
            addCriterionForJDBCDate("license_indate <>", value, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateGreaterThan(Date value) {
            addCriterionForJDBCDate("license_indate >", value, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("license_indate >=", value, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateLessThan(Date value) {
            addCriterionForJDBCDate("license_indate <", value, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("license_indate <=", value, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateIn(List<Date> values) {
            addCriterionForJDBCDate("license_indate in", values, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateNotIn(List<Date> values) {
            addCriterionForJDBCDate("license_indate not in", values, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("license_indate between", value1, value2, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andLicenseIndateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("license_indate not between", value1, value2, "licenseIndate");
            return (Criteria) this;
        }

        public Criteria andManageTypeIsNull() {
            addCriterion("manage_type is null");
            return (Criteria) this;
        }

        public Criteria andManageTypeIsNotNull() {
            addCriterion("manage_type is not null");
            return (Criteria) this;
        }

        public Criteria andManageTypeEqualTo(Byte value) {
            addCriterion("manage_type =", value, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeNotEqualTo(Byte value) {
            addCriterion("manage_type <>", value, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeGreaterThan(Byte value) {
            addCriterion("manage_type >", value, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("manage_type >=", value, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeLessThan(Byte value) {
            addCriterion("manage_type <", value, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeLessThanOrEqualTo(Byte value) {
            addCriterion("manage_type <=", value, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeIn(List<Byte> values) {
            addCriterion("manage_type in", values, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeNotIn(List<Byte> values) {
            addCriterion("manage_type not in", values, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeBetween(Byte value1, Byte value2) {
            addCriterion("manage_type between", value1, value2, "manageType");
            return (Criteria) this;
        }

        public Criteria andManageTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("manage_type not between", value1, value2, "manageType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeIsNull() {
            addCriterion("certif_type is null");
            return (Criteria) this;
        }

        public Criteria andCertifTypeIsNotNull() {
            addCriterion("certif_type is not null");
            return (Criteria) this;
        }

        public Criteria andCertifTypeEqualTo(Byte value) {
            addCriterion("certif_type =", value, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeNotEqualTo(Byte value) {
            addCriterion("certif_type <>", value, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeGreaterThan(Byte value) {
            addCriterion("certif_type >", value, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("certif_type >=", value, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeLessThan(Byte value) {
            addCriterion("certif_type <", value, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeLessThanOrEqualTo(Byte value) {
            addCriterion("certif_type <=", value, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeIn(List<Byte> values) {
            addCriterion("certif_type in", values, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeNotIn(List<Byte> values) {
            addCriterion("certif_type not in", values, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeBetween(Byte value1, Byte value2) {
            addCriterion("certif_type between", value1, value2, "certifType");
            return (Criteria) this;
        }

        public Criteria andCertifTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("certif_type not between", value1, value2, "certifType");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdIsNull() {
            addCriterion("operator_card_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdIsNotNull() {
            addCriterion("operator_card_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdEqualTo(String value) {
            addCriterion("operator_card_id =", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdNotEqualTo(String value) {
            addCriterion("operator_card_id <>", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdGreaterThan(String value) {
            addCriterion("operator_card_id >", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("operator_card_id >=", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdLessThan(String value) {
            addCriterion("operator_card_id <", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdLessThanOrEqualTo(String value) {
            addCriterion("operator_card_id <=", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdLike(String value) {
            addCriterion("operator_card_id like", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdNotLike(String value) {
            addCriterion("operator_card_id not like", value, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdIn(List<String> values) {
            addCriterion("operator_card_id in", values, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdNotIn(List<String> values) {
            addCriterion("operator_card_id not in", values, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdBetween(String value1, String value2) {
            addCriterion("operator_card_id between", value1, value2, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorCardIdNotBetween(String value1, String value2) {
            addCriterion("operator_card_id not between", value1, value2, "operatorCardId");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNull() {
            addCriterion("operator_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNotNull() {
            addCriterion("operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameEqualTo(String value) {
            addCriterion("operator_name =", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotEqualTo(String value) {
            addCriterion("operator_name <>", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThan(String value) {
            addCriterion("operator_name >", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_name >=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThan(String value) {
            addCriterion("operator_name <", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("operator_name <=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(String value) {
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotLike(String value) {
            addCriterion("operator_name not like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIn(List<String> values) {
            addCriterion("operator_name in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotIn(List<String> values) {
            addCriterion("operator_name not in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameBetween(String value1, String value2) {
            addCriterion("operator_name between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotBetween(String value1, String value2) {
            addCriterion("operator_name not between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table merchant_store_profile
     *
     * @mbg.generated do_not_delete_during_merge 2017-03-29 13:19:21
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table merchant_store_profile
     *
     * @mbg.generated 2017-03-29 13:19:21
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}