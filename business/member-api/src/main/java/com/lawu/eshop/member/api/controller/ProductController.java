package com.lawu.eshop.member.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.util.UserUtil;
import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.MemberProductCommentDTO;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.CommentMerchantService;
import com.lawu.eshop.member.api.service.FansMerchantService;
import com.lawu.eshop.member.api.service.FavoriteProductService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.ProductModelService;
import com.lawu.eshop.member.api.service.ProductService;
import com.lawu.eshop.member.api.service.ShoppingCartService;
import com.lawu.eshop.product.dto.MemberProductCommentInfoDTO;
import com.lawu.eshop.product.dto.MemberProductModelDTO;
import com.lawu.eshop.product.dto.MemberProductStoreDTO;
import com.lawu.eshop.product.dto.ProductDetailDTO;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.product.dto.ProductModelDataDTO;
import com.lawu.eshop.product.dto.ProductShipmentsPlaceDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * Description:商品
 * </p>
 *
 * @author Yangqh
 * @date 2017年3月27日 下午2:40:23
 */
@Api(tags = "product")
@RestController
@RequestMapping(value = "product/")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private MerchantStoreService merchantStoreService;
	@Autowired
	private FansMerchantService fansMerchantService;
	@Autowired
	private CommentMerchantService commentMerchantService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private FavoriteProductService favoriteProductService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private MemberApiConfig memberApiConfig;
	@Autowired
	private ProductModelService productModelService;

	@Deprecated
	@AutoTesting
	@Audit(date = "2017-04-01", reviewer = "孙林青")
	@ApiOperation(value = "查询商品详情", notes = "根据商品ID查询商品详情信息，[]，（杨清华）", httpMethod = "GET")
	@RequestMapping(value = "{productId}", method = RequestMethod.GET)
	public Result<ProductInfoDTO> selectProductById(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@PathVariable @ApiParam(name = "productId", required = true, value = "商品ID") Long productId) {

		Result<ProductInfoDTO> result = productService.selectProductById(productId);
		if (result.getRet() != ResultCode.SUCCESS) {
			return result;
		}
		
		// 查询门店信息
		Result<MemberProductStoreDTO> storeDTOResult = merchantStoreService.getMemberProductDetailStore(result.getModel().getMerchantId());
		if (result.getRet() != ResultCode.SUCCESS) {
			return result;
		}
		if(storeDTOResult.getModel() != null){
			Result<Integer> productNumResult = productService.selectProductCount(result.getModel().getMerchantId());
			storeDTOResult.getModel().setUpProductNum(productNumResult.getModel()== null ? "0" : productNumResult.getModel().toString());//上架商品数量
			Result<Integer> fansNumResult = fansMerchantService.countFans(result.getModel().getMerchantId());
			storeDTOResult.getModel().setFansNum(fansNumResult.getModel() == null ? "0" : fansNumResult.getModel().toString());//商家粉丝数量
		}
		result.getModel().setStore(storeDTOResult.getModel());		

		List<MemberProductModelDTO> spec = result.getModel().getSpec();//用于下面查询评价时回显商品型号名称
		
		// 查询评价信息
		Result<List<MemberProductCommentDTO>> commentsResult = commentMerchantService.geNewlyProductComment(productId);
		List<MemberProductCommentInfoDTO> commentList = new ArrayList<MemberProductCommentInfoDTO>();
		if (commentsResult.getRet() == ResultCode.SUCCESS) {
			for (MemberProductCommentDTO comment : commentsResult.getModel()) {
				MemberProductCommentInfoDTO cidto = new MemberProductCommentInfoDTO();
				cidto.setContent(comment.getContent());
				cidto.setGmtCreate(comment.getGmtCreate());
				cidto.setGrade(comment.getGrade());
				cidto.setImageUrl(comment.getImgUrls());
				cidto.setIsAnonymous(comment.getIsAnonymous());
				cidto.setReplyContent(comment.getReplyContent());

				boolean flag = true;
				for(MemberProductModelDTO model : spec){
					if(comment.getProductModelId().longValue() == model.getId().longValue()){
						cidto.setProductModel(model.getName());
						flag = false;
						break;
					}
				}
				if(flag){
					cidto.setProductModel("");
				}

				Result<UserDTO> user = memberService.findMemberInfo(comment.getMemberId());
				String nickName = user.getModel().getNickname();
				if(cidto.getIsAnonymous()){
					cidto.setHeadUrl(memberApiConfig.getDefaultHeadimg());
					cidto.setName(StringUtil.anonymous(nickName));
				}else{
					cidto.setHeadUrl(user.getModel().getHeadimg());
					cidto.setName(nickName);
				}
				cidto.setLevel(String.valueOf(user.getModel().getLevel()));
				if(user.getModel().getGradeEnum() != null){
					cidto.setMemberGrade(user.getModel().getGradeEnum());
				}
				commentList.add(cidto);
			}
		} 
		result.getModel().setComments(commentList);

		Result<Integer> count = commentMerchantService.getProductCommentCount(productId);
		result.getModel().setCommentCount(count.getModel());

		Long userId = UserUtil.getCurrentUserId(getRequest());
		if (userId == 0L) {
			result.getModel().setFavoriteFlag(false);
			result.getModel().setShoppingCartNum("0");
		} else {
			boolean isFavorite = favoriteProductService.getUserFavorite(productId, userId);
			result.getModel().setFavoriteFlag(isFavorite);
			Result<Long> shoppingCart = shoppingCartService.findListByIds(userId);
			if(shoppingCart.getModel() == null){
				result.getModel().setShoppingCartNum("0");
			}else{
				result.getModel().setShoppingCartNum(shoppingCart.getModel().toString());
			}
		}
		return result;
	}

	@Audit(date = "2018-04-18", reviewer = "孙林青")
	@ApiOperation(value = "商品详情", notes = "商品详情。[1002|1004]（梅述全）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getProductDetail/{id}", method = RequestMethod.GET)
	public Result<ProductDetailDTO> getProductDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
													 @PathVariable @ApiParam(name = "id", required = true, value = "商品ID") Long id) {
		if (id == null) {
			return successGet(ResultCode.REQUIRED_PARM_EMPTY);
		}
		Result<ProductDetailDTO> result = productService.getProductDetail(id);
		if (!isSuccess(result)) {
			return successGet(ResultCode.RESOURCE_NOT_FOUND);
		}

		// 查询门店信息
		Result<MemberProductStoreDTO> storeDTOResult = merchantStoreService.getMemberProductDetailStore(result.getModel().getMerchantId());
		if (!isSuccess(storeDTOResult)) {
			return result;
		}
		if (storeDTOResult.getModel() != null) {
			Result<Integer> productNumResult = productService.selectProductCount(result.getModel().getMerchantId());
			storeDTOResult.getModel().setUpProductNum(productNumResult.getModel() == null ? "0" : productNumResult.getModel().toString());//上架商品数量
			Result<Integer> fansNumResult = fansMerchantService.countFans(result.getModel().getMerchantId());
			storeDTOResult.getModel().setFansNum(fansNumResult.getModel() == null ? "0" : fansNumResult.getModel().toString());//商家粉丝数量
		}
		result.getModel().setStoreDTO(storeDTOResult.getModel());

		List<ProductModelDataDTO> spec = productModelService.queryProductModel(id).getModel();//用于下面查询评价时回显商品型号名称
		// 查询评价信息
		Result<List<MemberProductCommentDTO>> commentsResult = commentMerchantService.geNewlyProductComment(id);
		List<MemberProductCommentInfoDTO> commentList = new ArrayList<>();
		if (isSuccess(commentsResult)) {
			for (MemberProductCommentDTO comment : commentsResult.getModel()) {
				MemberProductCommentInfoDTO cidto = new MemberProductCommentInfoDTO();
				cidto.setContent(comment.getContent());
				cidto.setGmtCreate(comment.getGmtCreate());
				cidto.setGrade(comment.getGrade());
				cidto.setImageUrl(comment.getImgUrls());
				cidto.setIsAnonymous(comment.getIsAnonymous());
				cidto.setReplyContent(comment.getReplyContent());

				boolean flag = true;
				for (ProductModelDataDTO model : spec) {
					if (comment.getProductModelId().longValue() == model.getId().longValue()) {
						cidto.setProductModel(model.getName());
						flag = false;
						break;
					}
				}
				if (flag) {
					cidto.setProductModel("");
				}

				Result<UserDTO> user = memberService.findMemberInfo(comment.getMemberId());
				String nickName = user.getModel().getNickname();
				if (cidto.getIsAnonymous()) {
					cidto.setHeadUrl(memberApiConfig.getDefaultHeadimg());
					cidto.setName(StringUtil.anonymous(nickName));
				} else {
					cidto.setHeadUrl(user.getModel().getHeadimg());
					cidto.setName(nickName);
				}
				cidto.setLevel(String.valueOf(user.getModel().getLevel()));
				if (user.getModel().getGradeEnum() != null) {
					cidto.setMemberGrade(user.getModel().getGradeEnum());
				}
				commentList.add(cidto);
			}
		}
		result.getModel().setComments(commentList);

		Result<Integer> count = commentMerchantService.getProductCommentCount(id);
		result.getModel().setCommentCount(count.getModel());

		Long userId = UserUtil.getCurrentUserId(getRequest());
		if (userId == 0L) {
			result.getModel().setFavoriteFlag(false);
			result.getModel().setShoppingCartNum(0);
		} else {
			boolean isFavorite = favoriteProductService.getUserFavorite(id, userId);
			result.getModel().setFavoriteFlag(isFavorite);
			Result<Long> shoppingCart = shoppingCartService.findListByIds(userId);
			if (shoppingCart.getModel() == null) {
				result.getModel().setShoppingCartNum(0);
			} else {
				result.getModel().setShoppingCartNum(shoppingCart.getModel().intValue());
			}
		}
		return result;
	}

	@Audit(date = "2018-04-20", reviewer = "孙林青")
	@ApiOperation(value = "商品发货地列表", notes = "商品发货地列表。（梅述全）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "listProductCity", method = RequestMethod.GET)
	public Result<ProductShipmentsPlaceDTO> listProductCity() {
		Result<ProductShipmentsPlaceDTO> result = productService.listProductCity();
		return successGet(result);
	}

}
