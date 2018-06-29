package com.lawu.eshop.ad.srv.solr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.OrderTypeEnum;
import com.lawu.eshop.ad.dto.AdPraiseDTO;
import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.eshop.ad.srv.solr.service.AdSolrService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;
import com.lawu.eshop.solr.impl.BaseSolrServiceImpl;
import com.lawu.eshop.solr.impl.entity.AdSolr;
import com.lawu.eshop.solr.impl.repository.AdSolrRepository;

@Service
public class AdSolrServiceImpl extends BaseSolrServiceImpl<AdSolr, Long> implements AdSolrService {
    
    private AdSolrRepository repository;
    
    @Autowired
    private void setRepository(AdSolrRepository repository) {
        this.repository = repository;
        super.init(repository);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AdSolr solrBean) {
        AdSolr adSolr = repository.findOne(solrBean.getId());
        repository.save(adSolr);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(AdSolr solrBean) {
        repository.save(solrBean);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> ids) {
        List<AdSolr> idsToDelete = new ArrayList<>();
        for (Long id : ids) {
            AdSolr item = new AdSolr();
            item.setId(id);
            idsToDelete.add(item);
        }
        repository.delete(idsToDelete);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(List<AdSolr> list) {
        repository.save(list);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Page<AdSolr> selectChoiceness(AdSolrRealParam param) {
        String sexAgeQuery;
        if (param.getAge() == 0) {
            sexAgeQuery = "minAge_i:0 AND maxAge_i:200";
        } else {
            sexAgeQuery = "minAge_i:[0 TO " + param.getAge() + "] AND maxAge_i:[" + param.getAge() + " TO 200]";
        }
        if (param.getSexEnum() == UserSexEnum.SEX_SECRET) {
            sexAgeQuery += " AND sex_i:1";
        } else {
            if (param.getSexEnum() == UserSexEnum.SEX_MALE) {
                sexAgeQuery += " AND sex_i:[0 TO 1]";
            } else {
                sexAgeQuery += " AND sex_i:[1 TO 2]";
            }
        }
        sexAgeQuery += " AND ";

        String areaQueryStr = sexAgeQuery + "putWay_i:1 AND -status_i:3";
        if (StringUtils.isEmpty(param.getRegionPath())) {
            areaQueryStr += " AND area_is:0";
        } else {
            String[] path = param.getRegionPath().split("/");
            if (path.length == 3) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:" + path[2] + " OR area_is:0)";
            } else if (path.length == 2) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:0)";
            } else if (path.length == 1) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:0)";
            }
        }

        String fansQueryStr = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (param.getMemberId() != null && param.getMemberId() > 0 && !param.getMerchantIds().isEmpty()) {
            List<Long> merchantIds = param.getMerchantIds();
            for (Long id : merchantIds) {
                stringBuilder.append(id).append(" OR ");
            }
            fansQueryStr = stringBuilder.substring(0, stringBuilder.length() - 4);
            fansQueryStr = sexAgeQuery + "putWay_i:2 AND -status_i:3 AND merchantId_l:(" + fansQueryStr + ")";
        }

        double pointMax = 0;
        double pointMin = 0;
        double clickPoint = 0;
        boolean isClick = true;
        int totalCount = 0;
        SolrQuery query = new SolrQuery();
        if (StringUtils.isEmpty(fansQueryStr)) {
            query.setParam("q", areaQueryStr);
        } else {
            query.setParam("q", "(" + areaQueryStr + ") OR (" + fansQueryStr + ")");
        }
        query.setParam("sort", "adMark_i desc,point_d desc");
        query.setStart(param.getOffset());
        query.setRows(param.getPageSize());
        Page<AdSolr> adSolrPage = query(query, METHOD.POST);
        if (adSolrPage != null) {
            totalCount = (int) adSolrPage.getTotalElements();
            if (adSolrPage.getContent() != null && !adSolrPage.getContent().isEmpty()) {
                pointMax = adSolrPage.getContent().get(0).getPoint();
                pointMin = adSolrPage.getContent().get(adSolrPage.getContent().size() - 1).getPoint();
            }
        }
        for (AdSolr adSolr : adSolrPage.getContent()) {
            if (adSolr.getAdMark() == 0) {
                isClick = false;
                clickPoint = adSolr.getPoint();
                break;
            }
        }

        List<AdSolr> latLonDTOS = new ArrayList<>();
        if (param.getLatitude() != null && param.getLongitude() != null) {
            double lat = BigDecimal.valueOf(param.getLatitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            double lon = BigDecimal.valueOf(param.getLongitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            String latLon = lat + "," + lon;
            query = new SolrQuery();
            query.setParam("pt", latLon);
            query.setParam("fq", "{!geofilt}");
            query.setParam("sfield", "latLon_p");
            query.setParam("d", "30");
            query.setParam("fl", "*,distance:geodist(latLon_p," + latLon + ")");
            String radiusQueryStr = sexAgeQuery + "putWay_i:3 AND -status_i:3";
            query.setParam("q", radiusQueryStr);
            Page<AdSolr> radiusPage = query(query);
            if (radiusPage != null && radiusPage.getContent() != null && !radiusPage.getContent().isEmpty()) {
                for (AdSolr item : radiusPage.getContent()) {
                    if (item.getDistance() <= item.getRadius()) {
                        latLonDTOS.add(item);
                    }
                }
            }
        }

        List<AdSolr> content = new ArrayList<>(adSolrPage.getContent());
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize());
        //获取总页数
        int totalPage;
        if (totalCount > 0 && totalCount % param.getPageSize() == 0) {
            totalPage = totalCount / param.getPageSize();
        } else {
            totalPage = totalCount / param.getPageSize() + 1;
        }

        //总条数
        totalCount += latLonDTOS.size();
        //如果当前页大于总页数，则直接返回
        if (param.getCurrentPage() > totalPage) {
            return new SolrResultPage(content, pageable, totalCount, null);
        }

        if (param.getCurrentPage() == totalPage) {
            if (param.getCurrentPage() == 1) {
                content.addAll(latLonDTOS);
            } else {
                for (AdSolr solrDTO : latLonDTOS) {
                    if (content.get(content.size() - 1).getAdMark() == 1) {
                        if (solrDTO.getAdMark() == 1 && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    } else {
                        if (solrDTO.getAdMark() == 0 && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    }
                }
            }
        } else {
            for (AdSolr solrDTO : latLonDTOS) {
                if (isClick) {
                    if (solrDTO.getAdMark() > 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                        content.add(solrDTO);
                    }
                } else {
                    if (content.get(0).getAdMark() == 1) {
                        if (solrDTO.getAdMark() > 0 && solrDTO.getPoint() <= clickPoint) {
                            content.add(solrDTO);
                        } else if (solrDTO.getPoint() == 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    } else {
                        if (solrDTO.getPoint() == 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    }
                }
            }
        }

        if (!content.isEmpty()) {
            Collections.sort(content, new Comparator<AdSolr>() {
                @Override
                public int compare(AdSolr o1, AdSolr o2) {
                    int adMark = o2.getAdMark() - o1.getAdMark();
                    if (adMark == 0) {
                        return o2.getPoint().compareTo(o1.getPoint());
                    }
                    return adMark;
                }
            });
        }
        
        return new SolrResultPage(content, pageable, totalCount, null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Page<AdSolr> queryAdByTitle(AdsolrFindParam param) {
        String sexAgeQuery;
        if (param.getAge() == 0) {
            sexAgeQuery = "minAge_i:0 AND maxAge_i:200";
        } else {
            sexAgeQuery = "minAge_i:[0 TO " + param.getAge() + "] AND maxAge_i:[" + param.getAge() + " TO 200]";
        }
        if (param.getSexEnum() == UserSexEnum.SEX_SECRET) {
            sexAgeQuery += " AND sex_i:1";
        } else {
            if (param.getSexEnum() == UserSexEnum.SEX_MALE) {
                sexAgeQuery += " AND sex_i:[0 TO 1]";
            } else {
                sexAgeQuery += " AND sex_i:[1 TO 2]";
            }
        }
        sexAgeQuery += " AND ";

        String areaQueryStr = sexAgeQuery + "putWay_i:1 AND -status_i:3 AND -type_i:3";
        if (StringUtils.isEmpty(param.getRegionPath())) {
            areaQueryStr += " AND area_is:0";
        } else {
            String[] path = param.getRegionPath().split("/");
            if (path.length == 3) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:" + path[2] + " OR area_is:0)";
            } else if (path.length == 2) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:0)";
            } else if (path.length == 1) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:0)";
            }
        }
        if (StringUtils.isNotEmpty(param.getAdSolrParam().getTitle())) {
            areaQueryStr += " AND title_s:*" + param.getAdSolrParam().getTitle() + "*";
        }

        String fansQueryStr = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (param.getMemberId() != null && param.getMemberId() > 0 && !param.getMerchantIds().isEmpty()) {
            List<Long> merchantIds = param.getMerchantIds();
            for (Long id : merchantIds) {
                stringBuilder.append(id).append(" OR ");
            }
            fansQueryStr = stringBuilder.substring(0, stringBuilder.length() - 4);
            fansQueryStr = sexAgeQuery + "putWay_i:2 AND -status_i:3 AND -type_i:3 AND merchantId_l:(" + fansQueryStr + ")";
            if (StringUtils.isNotEmpty(param.getAdSolrParam().getTitle())) {
                fansQueryStr += " AND title_s:*" + param.getAdSolrParam().getTitle() + "*";
            }
        }

        double pointMax = 0;
        double pointMin = 0;
        double clickPoint = 0;
        boolean isClick = true;
        int totalCount = 0;
        SolrQuery query = new SolrQuery();
        if (StringUtils.isEmpty(fansQueryStr)) {
            query.setParam("q", areaQueryStr);
        } else {
            query.setParam("q", "(" + areaQueryStr + ") OR (" + fansQueryStr + ")");
        }
        query.setParam("sort", "adMark_i desc,point_d desc");
        query.setStart(param.getAdSolrParam().getOffset());
        query.setRows(param.getAdSolrParam().getPageSize());
        Page<AdSolr> adSolrPage = query(query, METHOD.POST);
        if (adSolrPage != null) {
            totalCount = (int) adSolrPage.getTotalElements();
            if (adSolrPage.getContent() != null && !adSolrPage.getContent().isEmpty()) {
                pointMax = adSolrPage.getContent().get(0).getPoint();
                pointMin = adSolrPage.getContent().get(adSolrPage.getContent().size() - 1).getPoint();
            }
        }
        for (AdSolr adSolr : adSolrPage.getContent()) {
            if (adSolr.getAdMark() == 0) {
                isClick = false;
                clickPoint = adSolr.getPoint();
                break;
            }
        }

        List<AdSolr> latLonDTOS = new ArrayList<>();
        if (param.getAdSolrParam().getLatitude() != null && param.getAdSolrParam().getLongitude() != null) {
            double lat = BigDecimal.valueOf(param.getAdSolrParam().getLatitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            double lon = BigDecimal.valueOf(param.getAdSolrParam().getLongitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            String latLon = lat + "," + lon;
            query = new SolrQuery();
            query.setParam("pt", latLon);
            query.setParam("fq", "{!geofilt}");
            query.setParam("sfield", "latLon_p");
            query.setParam("d", "30");
            query.setParam("fl", "*,distance:geodist(latLon_p," + latLon + ")");
            String radiusQueryStr = sexAgeQuery + "putWay_i:3 AND -status_i:3 AND -type_i:3";
            if (StringUtils.isNotEmpty(param.getAdSolrParam().getTitle())) {
                radiusQueryStr += " AND title_s:*" + param.getAdSolrParam().getTitle() + "*";
            }
            query.setParam("q", radiusQueryStr);
            Page<AdSolr> radiusPage = query(query);
            if (radiusPage != null && radiusPage.getContent() != null && !radiusPage.getContent().isEmpty()) {
                for (AdSolr item : radiusPage.getContent()) {
                    if (item.getDistance() <= item.getRadius()) {
                        latLonDTOS.add(item);
                    }
                }
            }
        }

        List<AdSolr> content = new ArrayList<>(adSolrPage.getContent());
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize());
        //获取总页数
        int totalPage;
        if (totalCount > 0 && totalCount % param.getPageSize() == 0) {
            totalPage = totalCount / param.getPageSize();
        } else {
            totalPage = totalCount / param.getPageSize() + 1;
        }

        //总条数
        totalCount += latLonDTOS.size();
        //如果当前页大于总页数，则直接返回
        if (param.getCurrentPage() > totalPage) {
            return new SolrResultPage(content, pageable, totalCount, null);
        }

        if (param.getCurrentPage() == totalPage) {
            if (param.getCurrentPage() == 1) {
                content.addAll(latLonDTOS);
            } else {
                for (AdSolr solrDTO : latLonDTOS) {
                    if (content.get(content.size() - 1).getAdMark() == 1) {
                        if (solrDTO.getAdMark() == 1 && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    } else {
                        if (solrDTO.getAdMark() == 0 && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    }
                }
            }
        } else {
            for (AdSolr solrDTO : latLonDTOS) {
                if (isClick) {
                    if (solrDTO.getAdMark() > 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                        content.add(solrDTO);
                    }
                } else {
                    if (content.get(0).getAdMark() == 1) {
                        if (solrDTO.getAdMark() > 0 && solrDTO.getPoint() <= clickPoint) {
                            content.add(solrDTO);
                        } else if (solrDTO.getPoint() == 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    } else {
                        if (solrDTO.getPoint() == 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    }
                }
            }
        }

        if (!content.isEmpty()) {
            Collections.sort(content, new Comparator<AdSolr>() {
                @Override
                public int compare(AdSolr o1, AdSolr o2) {
                    int adMark = o2.getAdMark() - o1.getAdMark();
                    if (adMark == 0) {
                        return o2.getPoint().compareTo(o1.getPoint());
                    }
                    return adMark;
                }
            });
        }
        
        return new SolrResultPage(content, pageable, totalCount, null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Page<AdSolr> getRecommendAdByType(AdSolrRealParam param) {
        String sexAgeQuery;
        if (param.getAge() == 0) {
            sexAgeQuery = "minAge_i:0 AND maxAge_i:200";
        } else {
            sexAgeQuery = "minAge_i:[0 TO " + param.getAge() + "] AND maxAge_i:[" + param.getAge() + " TO 200]";
        }
        if (param.getSexEnum() == UserSexEnum.SEX_SECRET) {
            sexAgeQuery += " AND sex_i:1";
        } else {
            if (param.getSexEnum() == UserSexEnum.SEX_MALE) {
                sexAgeQuery += " AND sex_i:[0 TO 1]";
            } else {
                sexAgeQuery += " AND sex_i:[1 TO 2]";
            }
        }
        sexAgeQuery += " AND ";

        String areaQueryStr = sexAgeQuery + "putWay_i:1 AND status_i:2";
        if (StringUtils.isEmpty(param.getRegionPath())) {
            areaQueryStr += " AND area_is:0";
        } else {
            String[] path = param.getRegionPath().split("/");
            if (path.length == 3) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:" + path[2] + " OR area_is:0)";
            } else if (path.length == 2) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:0)";
            } else if (path.length == 1) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:0)";
            }
        }
        if (param.getTypeEnum().equals(AdTypeEnum.AD_TYPE_FLAT)) {
            areaQueryStr += " AND type_i:1";
        } else if (param.getTypeEnum().equals(AdTypeEnum.AD_TYPE_VIDEO)) {
            areaQueryStr += " AND type_i:2";
        }

        String fansQueryStr = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (param.getMemberId() != null && param.getMemberId() > 0 && !param.getMerchantIds().isEmpty()) {
            List<Long> merchantIds = param.getMerchantIds();
            for (Long id : merchantIds) {
                stringBuilder.append(id).append(" OR ");
            }
            fansQueryStr = stringBuilder.substring(0, stringBuilder.length() - 4);
            fansQueryStr = sexAgeQuery + "putWay_i:2 AND status_i:2 AND merchantId_l:(" + fansQueryStr + ")";
            if (param.getTypeEnum().equals(AdTypeEnum.AD_TYPE_FLAT)) {
                fansQueryStr += " AND type_i:1";
            } else if (param.getTypeEnum().equals(AdTypeEnum.AD_TYPE_VIDEO)) {
                fansQueryStr += " AND type_i:2";
            }
        }

        double pointMax = 0;
        double pointMin = 0;
        double clickPoint = 0;
        boolean isClick = true;
        int totalCount = 0;
        SolrQuery query = new SolrQuery();
        if (StringUtils.isEmpty(fansQueryStr)) {
            query.setParam("q", areaQueryStr);
        } else {
            query.setParam("q", "(" + areaQueryStr + ") OR (" + fansQueryStr + ")");
        }
        query.setParam("sort", "adMark_i desc,point_d desc");
        query.setStart(param.getOffset());
        query.setRows(param.getPageSize());
        Page<AdSolr> adSolrPage = query(query, METHOD.POST);
        if (adSolrPage != null) {
            totalCount = (int) adSolrPage.getTotalElements();
            if (adSolrPage.getContent() != null && !adSolrPage.getContent().isEmpty()) {
                pointMax = adSolrPage.getContent().get(0).getPoint();
                pointMin = adSolrPage.getContent().get(adSolrPage.getContent().size() - 1).getPoint();
            }
        }
        for (AdSolr adSolr : adSolrPage.getContent()) {
            if (adSolr.getAdMark() == 0) {
                isClick = false;
                clickPoint = adSolr.getPoint();
                break;
            }
        }

        List<AdSolr> latLonDTOS = new ArrayList<>();
        if (param.getLatitude() != null && param.getLongitude() != null) {
            double lat = BigDecimal.valueOf(param.getLatitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            double lon = BigDecimal.valueOf(param.getLongitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            String latLon = lat + "," + lon;
            query = new SolrQuery();
            query.setParam("pt", latLon);
            query.setParam("fq", "{!geofilt}");
            query.setParam("sfield", "latLon_p");
            query.setParam("d", "30");
            query.setParam("fl", "*,distance:geodist(latLon_p," + latLon + ")");
            String radiusQueryStr = sexAgeQuery + "putWay_i:3 AND status_i:2";
            if (param.getTypeEnum().equals(AdTypeEnum.AD_TYPE_FLAT)) {
                radiusQueryStr += " AND type_i:1";
            } else if (param.getTypeEnum().equals(AdTypeEnum.AD_TYPE_VIDEO)) {
                radiusQueryStr += " AND type_i:2";
            }
            query.setParam("q", radiusQueryStr);
            Page<AdSolr> radiusPage = query(query);
            if (radiusPage != null && radiusPage.getContent() != null && !radiusPage.getContent().isEmpty()) {
                for (AdSolr item : radiusPage.getContent()) {
                    if (item.getDistance() <= item.getRadius()) {
                        latLonDTOS.add(item);
                    }
                }
            }
        }

        List<AdSolr> content = new ArrayList<>(adSolrPage.getContent());
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize());
        //获取总页数
        int totalPage;
        if (totalCount > 0 && totalCount % param.getPageSize() == 0) {
            totalPage = totalCount / param.getPageSize();
        } else {
            totalPage = totalCount / param.getPageSize() + 1;
        }

        //总条数
        totalCount += latLonDTOS.size();
        //如果当前页大于总页数，则直接返回
        if (param.getCurrentPage() > totalPage) {
            return new SolrResultPage(content, pageable, totalCount, null);
        }

        if (param.getCurrentPage() == totalPage) {
            if (param.getCurrentPage() == 1) {
                content.addAll(latLonDTOS);
            } else {
                for (AdSolr solrDTO : latLonDTOS) {
                    if (content.get(content.size() - 1).getAdMark() == 1) {
                        if (solrDTO.getAdMark() == 1 && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    } else {
                        if (solrDTO.getAdMark() == 0 && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    }
                }
            }
        } else {
            for (AdSolr solrDTO : latLonDTOS) {
                if (isClick) {
                    if (solrDTO.getAdMark() > 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                        content.add(solrDTO);
                    }
                } else {
                    if (content.get(0).getAdMark() == 1) {
                        if (solrDTO.getAdMark() > 0 && solrDTO.getPoint() <= clickPoint) {
                            content.add(solrDTO);
                        } else if (solrDTO.getPoint() == 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    } else {
                        if (solrDTO.getPoint() == 0 && solrDTO.getPoint() >= pointMin && solrDTO.getPoint() <= pointMax) {
                            content.add(solrDTO);
                        }
                    }
                }
            }
        }

        if (!content.isEmpty()) {
            Collections.sort(content, new Comparator<AdSolr>() {
                @Override
                public int compare(AdSolr o1, AdSolr o2) {
                    int adMark = o2.getAdMark() - o1.getAdMark();
                    if (adMark == 0) {
                        return o2.getPoint().compareTo(o1.getPoint());
                    }
                    return adMark;
                }
            });
        }
        return new SolrResultPage(content, pageable, totalCount, null);
    }

    @Override
    public Page<AdSolr> getRecommendEgain(AdSolrRealParam param) {
        String sexAgeQuery;
        if (param.getAge() == 0) {
            sexAgeQuery = "minAge_i:0 AND maxAge_i:200";
        } else {
            sexAgeQuery = "minAge_i:[0 TO " + param.getAge() + "] AND maxAge_i:[" + param.getAge() + " TO 200]";
        }
        if (param.getSexEnum() == UserSexEnum.SEX_SECRET) {
            sexAgeQuery += " AND sex_i:1";
        } else {
            if (param.getSexEnum() == UserSexEnum.SEX_MALE) {
                sexAgeQuery += " AND sex_i:[0 TO 1]";
            } else {
                sexAgeQuery += " AND sex_i:[1 TO 2]";
            }
        }
        sexAgeQuery += " AND ";

        List<AdPraiseDTO> adPraiseDTOS = new ArrayList<>();
        String areaQueryStr = sexAgeQuery + "putWay_i:1 AND type_i:3";
        if (StringUtils.isEmpty(param.getRegionPath())) {
            areaQueryStr += " AND area_is:0";
        } else {
            String[] path = param.getRegionPath().split("/");
            if (path.length == 3) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:" + path[2] + " OR area_is:0)";
            } else if (path.length == 2) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:0)";
            } else if (path.length == 1) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:0)";
            }
        }
        if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_PUTING)) {
            areaQueryStr += " AND status_i:2";
        } else if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_ADD)) {
            areaQueryStr += " AND status_i:1";
        } else if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_PUTED)) {
            areaQueryStr += " AND status_i:3";
        }

        String fansQueryStr = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (param.getMemberId() != null && param.getMemberId() > 0 && !param.getMerchantIds().isEmpty()) {
            List<Long> merchantIds = param.getMerchantIds();
            for (Long id : merchantIds) {
                stringBuilder.append(id).append(" OR ");
            }
            fansQueryStr = stringBuilder.substring(0, stringBuilder.length() - 4);
            fansQueryStr = sexAgeQuery + "putWay_i:2 AND type_i:3 AND merchantId_l:(" + fansQueryStr + ")";
            if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_PUTING)) {
                fansQueryStr += " AND status_i:2";
            } else if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_ADD)) {
                fansQueryStr += " AND status_i:1";
            } else if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_PUTED)) {
                fansQueryStr += " AND status_i:3";
            }
        }

        SolrQuery query = new SolrQuery();
        if (StringUtils.isEmpty(fansQueryStr)) {
            query.setParam("q", areaQueryStr);
        } else {
            query.setParam("q", "(" + areaQueryStr + ") OR (" + fansQueryStr + ")");
        }
        if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_PUTING) || param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_ADD)) {
            query.setSort("beginTime_l", SolrQuery.ORDER.asc);
        } else if (param.getStatusEnum().equals(AdStatusEnum.AD_STATUS_PUTED)) {
            query.setSort("beginTime_l", SolrQuery.ORDER.desc);
        }
        query.setStart(param.getOffset());
        query.setRows(param.getPageSize());
        return query(query, METHOD.POST);
    }

    @Override
    public List<AdSolr> listAdRank(AdSolrRealParam param) {
        String sexAgeQuery;
        if (param.getAge() == 0) {
            sexAgeQuery = "minAge_i:0 AND maxAge_i:200";
        } else {
            sexAgeQuery = "minAge_i:[0 TO " + param.getAge() + "] AND maxAge_i:[" + param.getAge() + " TO 200]";
        }
        if (param.getSexEnum() == UserSexEnum.SEX_SECRET) {
            sexAgeQuery += " AND sex_i:1";
        } else {
            if (param.getSexEnum() == UserSexEnum.SEX_MALE) {
                sexAgeQuery += " AND sex_i:[0 TO 1]";
            } else {
                sexAgeQuery += " AND sex_i:[1 TO 2]";
            }
        }
        sexAgeQuery += " AND ";

        List<AdSolr> rtn = new ArrayList<>();
        String areaQueryStr = sexAgeQuery + "putWay_i:1 AND type_i:1";
        if (StringUtils.isEmpty(param.getRegionPath())) {
            areaQueryStr += " AND area_is:0";
        } else {
            String[] path = param.getRegionPath().split("/");
            if (path.length == 3) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:" + path[2] + " OR area_is:0)";
            } else if (path.length == 2) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:" + path[1] + " OR area_is:0)";
            } else if (path.length == 1) {
                areaQueryStr += " AND (area_is:" + path[0] + " OR area_is:0)";
            }
        }

        String fansQueryStr = "";
        StringBuilder stringBuilder = new StringBuilder();
        if (param.getMemberId() != null && param.getMemberId() > 0 && !param.getMerchantIds().isEmpty()) {
            List<Long> merchantIds = param.getMerchantIds();
            for (Long id : merchantIds) {
                stringBuilder.append(id).append(" OR ");
            }
            fansQueryStr = stringBuilder.substring(0, stringBuilder.length() - 4);
            fansQueryStr = sexAgeQuery + "putWay_i:2 AND type_i:1 AND merchantId_l:(" + fansQueryStr + ")";
        }

        double pointMax = 0;
        double pointMin = 0;
        SolrQuery query = new SolrQuery();
        if (StringUtils.isEmpty(fansQueryStr)) {
            query.setParam("q", areaQueryStr);
        } else {
            query.setParam("q", "(" + areaQueryStr + ") OR (" + fansQueryStr + ")");
        }
        if (param.getOrderTypeEnum().equals(OrderTypeEnum.AD_TORLEPOINT_DESC)) {
            query.setSort("totalPoint_d", SolrQuery.ORDER.desc);
        } else {
            query.setSort("point_d", SolrQuery.ORDER.desc);
        }
        query.setStart(param.getOffset());
        query.setRows(param.getPageSize());
        Page<AdSolr> adSolrPage = query(query, METHOD.POST);
        if (adSolrPage != null && adSolrPage.getContent() != null && !adSolrPage.getContent().isEmpty()) {
            rtn.addAll(adSolrPage.getContent());
            if (param.getOrderTypeEnum().equals(OrderTypeEnum.AD_TORLEPOINT_DESC)) {
                pointMax = adSolrPage.getContent().get(0).getTotalPoint();
                pointMin = adSolrPage.getContent().get(adSolrPage.getContent().size() - 1).getTotalPoint();
            } else {
                pointMax = adSolrPage.getContent().get(0).getPoint();
                pointMin = adSolrPage.getContent().get(adSolrPage.getContent().size() - 1).getPoint();
            }
        }
        
        if (param.getLatitude() != null && param.getLongitude() != null) {
            double lat = BigDecimal.valueOf(param.getLatitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            double lon = BigDecimal.valueOf(param.getLongitude()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            String latLon = lat + "," + lon;
            query = new SolrQuery();
            query.setParam("pt", latLon);
            query.setParam("fq", "{!geofilt}");
            query.setParam("sfield", "latLon_p");
            query.setParam("d", "30");
            query.setParam("fl", "*,distance:geodist(latLon_p," + latLon + ")");
            String radiusQueryStr = sexAgeQuery + "putWay_i:3 AND type_i:1";
            if (pointMax > 0) {
                if (param.getOrderTypeEnum().equals(OrderTypeEnum.AD_TORLEPOINT_DESC)) {
                    radiusQueryStr += " AND totalPoint_d:[" + pointMin + " TO " + pointMax + "]";
                } else {
                    radiusQueryStr += " AND point_d:[" + pointMin + " TO " + pointMax + "]";
                }
            }
            query.setParam("q", radiusQueryStr);
            Page<AdSolr> radiusPage = query(query);
            if (radiusPage != null && radiusPage.getContent() != null && !radiusPage.getContent().isEmpty()) {
                for (AdSolr item : radiusPage.getContent()) {
                    if (item.getDistance() <= item.getRadius()) {
                        rtn.add(item);
                    }
                }
            }
        }
        if (!rtn.isEmpty()) {
            Collections.sort(rtn, new Comparator<AdSolr>() {
                @Override
                public int compare(AdSolr o1, AdSolr o2) {
                    if (param.getOrderTypeEnum().equals(OrderTypeEnum.AD_TORLEPOINT_DESC)) {
                        if (o2.getTotalPoint() - o1.getTotalPoint() >= 0) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        if (o2.getPoint() - o1.getPoint() >= 0) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            });
        }
        return rtn;
    }
}
