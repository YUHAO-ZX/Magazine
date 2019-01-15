/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.emerge;

import com.woom.magazine.util.excel.ExcelReader;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yuhao.zx
 * @version $Id: InformationGenerator.java, v 0.1 2018年12月28日 3:50 PM yuhao.zx Exp $
 */
public class InformationGenerator {

    static String           key                                 = "PROD";

    protected static String CONTENT_INSERT                      = "insert into pc_base_content_00( content_id,  business_domain,  business_line,  out_biz_no,  content_type,  content_code,  content_name,  memo,  nomo_crowd_id,  valid_start_time,  valid_end_time,  status,  operator_account,  source,  env_type,  gmt_create,  gmt_modified,  biz_target_id) values('%s', '19', '545', '%s', 'INFORMATION', 'INFORMATION', '%s', null, null, '%s', '%s', 'ENABLED', '祐子', 'PROMOCONTENT', '%s', now(6), now(6), null);";

    protected static String INFORMATION_INSERT                  = "insert into pc_information_content_00( id,  content_id,  information_type,  title,  summary,  pic_logo,  detail_url,  extend_info,  gmt_create,  gmt_modified) values('%s', '%s', 'DEFAULT', '%s', '%s', null, null, null, now(6), now(6));";

    protected static String CATEGORY_RELATION_INSERT            = "insert into pc_category_relation_00( category_relation_id,  relation_type,  target_relation_id,  category_info_id,  status,  gmt_create,  gmt_modified) values('%s', 'BASE_CONTENT', '%s', 101, 'ENABLED', now(6), now(6));";

    protected static String BOOTH_RELATION_INSERT               = "insert into hzf_booth_relation(   gmt_create,  gmt_modified,  content_id,  "
                                                                  + "booth_id,  tf_frequency,  tf_priority,  beta_white_list,  status,  audit_status,  creator_name,  creator_id,  "
                                                                  + "business_extend_info,  style_extend_info,  related_booth_id,  city_id,  biz_time) values( now(6), now(6), '%s', '201709260038A732CC9D531C091E', '1,2,3,4,"
                                                                  + "5,6,0', 0, '%s', 'ACTIVE', 'CREATED', '祐子', '195297', null, '{\"buttonDescription\":\"%s\",\"buttonUrl\":\"%s\",\"cardUrl\":\"%s\",\"equityPic\":\"%s\",\"firstTitle\":\"%s\","
                                                                  + "\"logo\":\"%s\",\"promoInfo\":\"%s\",\"remark\":\"%s\"}', '', 'ALL', null);\n";

    protected static String CONTENT_TIME_UPDATE                 = "UPDATE pc_base_content_00 SET gmt_modified = now(6),valid_start_time='%s' WHERE content_id='%s';";

    protected static String CONTENT_DELETE                      = "DELETE FROM  pc_base_content_00 where content_id = '%s' ;";

    protected static String INFORMATION_DELETE                  = "DELETE FROM pc_information_content_00 where id = '%s';";

    protected static String CATEGORY_RELATION_DELETE            = "DELETE FROM pc_category_relation_00 where category_relation_id = '%s';";

    protected static String BOOTH_RELATION_DELETE               = "DELETE FROM hzf_booth_relation where content_id = '%s' and booth_id = '201709260038A732CC9D531C091E';";

    protected static String BOOTH_RELATION_AUDIT_PASS           = "UPDATE hzf_booth_relation set gmt_modified = now(6),beta_white_list='ALL',audit_status='PASSED' WHERE content_id = '%s' AND booth_id = '201709260038A732CC9D531C091E';";

    protected static String BOOTH_AUDIT_DETAIL                  = "insert into hzf_audit_detail( gmt_create,  gmt_modified,  audit_detail_id,  biz_type,  biz_id,  audit_status,  audit_creator_id,  audit_creator_nick,  auditor_id,  auditor_nick,  audit_time) values(now(), now(), '%s', 'BOOTH-STYLE-AUDIT', '%s-201709260038A732CC9D531C091E', 'PASSED', '31879', '步非', '2703233', null, now());";

    protected static String BOOTH_RELATION_AUDIT_PASS_ROLL_BACK = "UPDATE hzf_booth_relation set gmt_modified = now(6),beta_white_list='%s',audit_status='CREATED' WHERE content_id = '%s' AND booth_id = '201709260038A732CC9D531C091E';";

    protected static String BOOTH_AUDIT_DETAIL_ROLL_BACK        = "DELETE FROM hzf_audit_detail where audit_detail_id='%s';";

    //!!!!!!!!!!!!!!!!环境!!!!!!!!!!!!!!!!!!!!
    protected static String ENV_TYPE                            = "PROD";                                                                                                                                                                                                                                                                                                                                                                                                                                                  //PROD

    protected static String NOW_TIME                            = "2018-12-28 00:00:01";

    static String           BETA_WHITE_LIST                     = "2088702204318221,2088112728338834,2088202882339977";

    public static void main(String[] args) throws Exception {

        //String outBizNo = md5(String.valueOf(System.currentTimeMillis()),key);
        //System.out.println(outBizNo);

        List<String[]> datas = ExcelReader
            .readExcel("/Users/yuhao.zx/Desktop/限时秒杀资讯内容录入_0115-0116.xlsx");

        List<String> contentIds = new LinkedList<>();
        List<String> informationIds = new LinkedList<>();
        List<String> categoryRelationIds = new LinkedList<>();
        List<String> auditDetailIds = new LinkedList<>();
        List<String> contentIdAndStartTime = new LinkedList<>();

        for (int i = 0; i < datas.size(); i++) {
            logInsertSql(datas.get(i), contentIds, informationIds, categoryRelationIds,
                auditDetailIds, contentIdAndStartTime);
        }

        logRollback(contentIds, informationIds, categoryRelationIds);

        logBatchAuditPass(contentIds, auditDetailIds);

        logUpdateTime(contentIdAndStartTime);

        logBatchAuditPassRollback(contentIds, auditDetailIds);
    }

    private static void logRollback(List<String> contentIds, List<String> informationIds,
                                    List<String> categoryRelationIds) {
        System.out.println("###########内容&展位样式回滚###########");
        contentIds.stream().forEach(o -> System.out.println(String.format(CONTENT_DELETE, o)));
        informationIds.stream()
            .forEach(o -> System.out.println(String.format(INFORMATION_DELETE, o)));
        categoryRelationIds.stream()
            .forEach(o -> System.out.println(String.format(CATEGORY_RELATION_DELETE, o)));
        contentIds.stream()
            .forEach(o -> System.out.println(String.format(BOOTH_RELATION_DELETE, o)));
    }

    private static void logBatchAuditPass(List<String> contentIds, List<String> auditDetailIds) {
        System.out.println("###########全量生效###########");
        for (int i = 0; i < contentIds.size(); i++) {
            System.out.println(String.format(BOOTH_RELATION_AUDIT_PASS, contentIds.get(i)));
            System.out.println(
                String.format(BOOTH_AUDIT_DETAIL, auditDetailIds.get(i), contentIds.get(i)));
        }
    }

    private static void logBatchAuditPassRollback(List<String> contentIds,
                                                  List<String> auditDetailIds) {
        System.out.println("###########全量生效回滚###########");
        contentIds.stream().forEach(o -> System.out
            .println(String.format(BOOTH_RELATION_AUDIT_PASS_ROLL_BACK, BETA_WHITE_LIST, o)));

        auditDetailIds.stream()
            .forEach(o -> System.out.println(String.format(BOOTH_AUDIT_DETAIL_ROLL_BACK, o)));
    }

    private static void logUpdateTime(List<String> contentIdAndStartTime) {
        System.out.println("###########更新内容时间操作###########");

        contentIdAndStartTime.stream().forEach(o -> System.out
            .println(String.format(CONTENT_TIME_UPDATE, o.split("_")[1], o.split("_")[0])));

    }

    private static void logInsertSql(String[] data, List<String> contentIds,
                                     List<String> informationIds, List<String> categoryRelationIds,
                                     List<String> auditDetailIds,
                                     List<String> contentIdAndStartTime) throws Exception {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(2);
        String prefix = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));


        System.out.println("###########内容&展位样式创建###########");
        Information information = InfomationConvertor.convert(data);

        String tail = String.format("%04d", information.getId());
        String contentId = prefix+"0001010000900000" + tail;
        String outBizNo = md5(String.valueOf(System.currentTimeMillis()), key);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //这里用NOW_TIME来白名单测试可见
        String contentSql = String.format(CONTENT_INSERT, contentId, outBizNo,
            information.getContentName(), NOW_TIME,
            sd.format(information.getEndTime()), ENV_TYPE);

        String informationId = prefix+"0001050000900000" + tail;

        String informationSql = String.format(INFORMATION_INSERT, informationId, contentId,
            information.getContentDescription(), information.getContentDescription());

        String categoryRelationId = prefix+"0001080000900000" + tail;

        String categoryRelationSql = String.format(CATEGORY_RELATION_INSERT, categoryRelationId,
            contentId);

        String boothRelationSql = String.format(BOOTH_RELATION_INSERT, contentId, BETA_WHITE_LIST,
            information.getButtonDesc(), information.getButtonUrl(), information.getCardUrl(),
            information.getPromoLogo(), information.getMainTitle(), information.getLogo(),
            information.getPromoInfo(), information.getRemark());

        String auditDetailId = prefix+"0002010000900000" + tail;

        System.out.println(contentSql);
        System.out.println(informationSql);
        System.out.println(categoryRelationSql);
        System.out.println(boothRelationSql);

        contentIds.add(contentId);
        informationIds.add(informationId);
        categoryRelationIds.add(categoryRelationId);
        auditDetailIds.add(auditDetailId);

        contentIdAndStartTime.add(contentId + "_" + sd.format(information.getStartTime()));

        Thread.sleep(10);
    }

    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr = DigestUtils.md5Hex(text + key);
        //System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
        return encodeStr;
    }

}