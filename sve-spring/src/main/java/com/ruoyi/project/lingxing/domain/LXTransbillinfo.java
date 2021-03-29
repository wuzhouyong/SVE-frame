package com.ruoyi.project.lingxing.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @author solang
 * @date 2020-12-01 14:22
 */
@Data
public class LXTransbillinfo extends BaseEntity {
 private String bill_id;
 private String bill_no;
 private String bill_status;
 private String source_id;
 private String source_name;
 private String contact_info;
 private String address;
 private String trans_source_id;
 private String trans_source_name;
 private String trans_contect_info;
 private String trans_address;
 private String trans_postcode;
 private String proc_source_id;
 private String proc_source_name;
 private String proc_contact_info;
 private String proc_address;
 private String proc_postcode;
 private String wastewater_name;
 private String wastewater_type;
 private String wastewater_amt;
 private String factinamt;
 private String wastewater_nature;
 private String wastewater_shape;
 private String trans_type;
 private String trans_purpose;
 private String main_components;
 private String emergency_measure;
 private String ship_persign;
 private String trans_destination;
 private String trans_date;
 private String carrier1_name;
 private String carrier1_date;
 private String carrier1_type;
 private String carrier1_platenum;
 private String carrier1_transcert;
 private String start_place;
 private String via_place;
 private String end_place;
 private String carrie_r1sign;
 private String carrie_r2name;
 private String carrier2_date;
 private String carrier2_type;
 private String carrier2_platenum;
 private String carrier2_transcert;
 private String carrier2_sign;
 private String receive_sourcename;
 private String receive_personname;
 private String received_date;
 private String proc_type;
 private String receive_headsign;
 private String receive_headsigndate;
 private String invalid_reason;
 private String invalid_date;
 private String invalid_user;
 private String is_deleted;
 private String created_by;
 private String updated_by;
 private String date_created;
 private String date_updated;
 private String shipper_name;
 private String receive_head;
 private String upload_date;
}
