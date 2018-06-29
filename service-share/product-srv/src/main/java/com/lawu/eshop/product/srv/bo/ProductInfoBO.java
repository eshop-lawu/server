package com.lawu.eshop.product.srv.bo;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.MemberProductImageDetailDTO;
import com.lawu.eshop.product.dto.MemberProductModelDTO;

/**
 * 产品类别BO
 *
 * @author Yangqh
 * @date 2017/3/22
 */
public class ProductInfoBO {

    private Long id;
    private Long merchantId;
    private String merchantNum;
    private Integer categoryId;
    private String name;
    private String content;
    private String featureImage;
    private List<String> imagesHeadUrl;
    private List<MemberProductModelDTO> spec;
    private List<MemberProductImageDetailDTO> imageDetail;
    private Date gmtCreate;
    private String maxPrice;
	private String minPrice;
	private Integer totalSalesVolume;
	private Integer totalInventory;
	
	private boolean isAllowRefund;
	
	private ProductStatusEnum productStatus;

	private String specInfo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

	public List<String> getImagesHeadUrl() {
		return imagesHeadUrl;
	}

	public void setImagesHeadUrl(List<String> imagesHeadUrl) {
		this.imagesHeadUrl = imagesHeadUrl;
	}

	public List<MemberProductModelDTO> getSpec() {
		return spec;
	}

	public void setSpec(List<MemberProductModelDTO> spec) {
		this.spec = spec;
	}

	public List<MemberProductImageDetailDTO> getImageDetail() {
		return imageDetail;
	}

	public void setImageDetail(List<MemberProductImageDetailDTO> imageDetail) {
		this.imageDetail = imageDetail;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getTotalSalesVolume() {
		return totalSalesVolume;
	}

	public void setTotalSalesVolume(Integer totalSalesVolume) {
		this.totalSalesVolume = totalSalesVolume;
	}

	public Integer getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isAllowRefund() {
		return isAllowRefund;
	}

	public void setAllowRefund(boolean isAllowRefund) {
		this.isAllowRefund = isAllowRefund;
	}

	public ProductStatusEnum getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatusEnum productStatus) {
		this.productStatus = productStatus;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}
}
