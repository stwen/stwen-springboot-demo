package com.example.demo.result;

import lombok.Getter;
import lombok.ToString;

/**
 * @author ludonglue
 * @date 2019/1/3
 */
@ToString
@Getter
public enum ResultCode {
    SUCCESS(0,"操作成功"),
    ERROR(-1,"服务异常"),
    EXIST(1000,"数据已存在"),
    NOT_EXIST(1001,"数据为空"),
    GET_FAIL(1002,"查询失败"),
    PARAM_NULL(1003,"参数为空"),

    NO_PERMISSION(401,"无操作权限"),
    TOKEN_EXPIRED(402,"token已过期，请重新登录"),
    TOKEN_INVALID(403,"token错误"),
    TOKEN_EMPTY(405,"token为空，请登录"),

    /**
     *  密码错误
     */
    PWD_ERROR(2,"密码错误"),

    VISIT_NOT_ALLOW(1004,"非法访问"),
    /**
     * 数据库操作
     */
    DB_INSERT_ERROR(2000, "数据插入失败"),
    DB_UPDATE_ERROR(2001, "数据更新失败"),
    DB_DELETE_ERROR(2002, "数据删除失败"),

    /**
     * 文件操作
     */
    FILE_UPLOAD_ERROR(3000, "文件上传失败"),
    FILE_DELETE_ERROR(3001, "文件删除失败"),
    FILE_NULL(3002, "文件为空"),

    /**
     * 分类
     */
    DELETE_RELATION_FIRST(4000,"请先删除关联关系"),
    /**
     * 商品
     */
    SPEC_GOODS_CODE_EMPTY(5000,"合伙人商品必须提供sap商品编码"),
    GOODS_NOT_EXIST(5001,"商品不存在"),
    GOODS_NOT_AUDIT(5002,"商品未审核通过"),
    ESGOODS_INSERT_ERROR(5003,"ES商品库插入异常"),
    ESGOODS_INDEX_DELETE_ERROR(5004,"ES商品库索引删除异常"),
    GOODS_ON_SALE(5005,"请先下架商品"),
    DUPLICATE_CODE(5006,"生成的编码重复，请重试"),
    GOODS_IS_DELETE(5007,"商品已被删除"),
    ESGOODS_DELETE_ERROR(5008,"ES商品库删除异常"),
    GOODS_NOT_ON_SALE(5009,"商品已下架"),
    STOCK_CHANGE_ERROR(5010,"库存修改失败"),

    ESGOODS_INDEX_CREATE_ERROR(5010,"ES商品库索引创建异常"),
    SAP_GOODS_STATUS_DOWN(5011,"商品在SAP系统已下架"),
    GOODS_EXIST_SHOWCASE(5012,"商品在橱窗的推荐位中"),

    STOCK_NOT_ENOUGH(5010,"商品库存不足"),
    NOT_EXIST_OR_NOT_ON_SALE(5013,"商品不存在或已下架"),


    /**
     * 购物车
     */
    CART_NOT_EXIST(5001,"购物车不存在"),
    INVALID_TOKEN(5002,"重复提交"),
    NOT_ZERO(5003,"购物车不能为零"),



    /**
     * 注册&登录
     */
    IMAGE_VERIFY_CODE_ERROR(5000,"图形验证码错误"),
    MOBILE_SMS_CODE_ERROR(5001,"手机短信验证码错误"),
    SEND_SMS_CODE_ERROR(5002,"发送手机短信验证码错误"),
    MOBILE_ERROR(5003,"手机号码格式错误"),
    PASSWORD_ERROR(5004,"密码错误"),
    REGISTER_FAIL(5005,"注册失败"),
    LOGIN_FAIL(5006,"登录失败"),
    NOT_REG(5007,"用户名错误或未注册"),
    USER_DISABLE(5008,"用户名错误或未注册"),
    IS_REGISTER(5009,"该手机号已经注册，请直接登录"),
    PASS_NULL(5010,"密码为空"),
    NOT_LOGIN(5011,"您还未登陆或登录已过期，请重新登录"),
    SAME_MOBILE(5011,"手机号相同"),
    PAY_PWD_ERROR(5012,"支付密码错误！"),
    NO_PAY_PWD(5013,"您还未设置过支付密码，请先设置支付密码！"),
    SAME_PAY_PWD(5014,"不能与旧支付密码相同！"),
    HAVE_PAY_PWD(5014,"支付密码已存在！"),

    /**
     * 短信验证码
     */
    SMS_SIGN_ERROR(6000,"签名有误"),
    UPPER_LIMIT_COUNT(6001,"请不要频繁操作，或者该手机号达已到限流次数"),
    SMS_TEMPLATE_ERROR(6002,"短信模板不合法(不存在或被拉黑)"),
    SMS_ERROR(6003,"发送短信错误，请联系系统管理员，该手机号是否受限"),

    /**
     * 退款退货
     */
    REFUND_APPLY_ERROR(7000,"退款/退货申请失败"),
    REFUND_UPDATE_ERROR(7001,"对不起，商家审核后不可以修改"),
    REFUND_AMOUNT_ERROR(7002,"退款金额不能大于订单金额"),
    REFUND_FEE_ERROR(7003,"退款运费不能大于订单运费"),
    REFUND_STATUS_ERROR(7004,"买家还没发货，请勿乱操作"),
    REFUND_NO_AUDIT_PERMISSION(7005,"对不起，您不是该订单的商家，没有操作权限！"),
    REFUND_APPEAL_PERIOD(7006,"对不起，当前时间还没达到申诉周期！"),
    REFUND_REPEAT_APPLY(7007,"对不起，该订单已在退款中，请勿重复申请！"),

    /**
     * 店铺
     */
    SHOP_NOT_FIND(6000,"店铺查询错误"),
    SHOP_ONSALE_ERROR(6010,"店铺下架商品失败"),

    /**
     * 支付
     */
    WX_PAY_ERROR(8100,"微信支付失败"),
    WX_PAY_NO_OPENID(8102,"支付类型为JSAPI缺少openid"),
    WX_PAY_NO_PRODUCTID(8103,"支付类型为NATIVE缺少productId"),
    WX_PAY_QUERY_ERROR(8104,"微信订单查询失败"),
    WX_REFUND_ERROR(8151,"微信退款失败"),
    WX_REFUND_ERRAMOUNT(8152,"退款金额比订单金额大"),
    WX_REFUND_QUERY_ERROR(8153,"微信退款查询失败"),

    ALP_REFUND_ERROR(8200,"支付宝退款失败"),
    ALP_REFUND_QUERY_ERROR(8201,"支付宝退款查询失败"),
    ALPPAY_ERROR(8202,"支付宝支付失败"),
    NOT_PAY(8400,"未支付"),
    PAY_NOT_SUPPORT(8333,"暂时不支持该支付类型"),

    /**
     * 清分结算
     */
    SETTLE_CLEARING_NO_DEFAULT(1101,"清分规则没有默认值"),
    SETTLE_CLEARING_NO_DEFAULT_DETAIL(1102,"没有默认清分规则明细"),

    /**
     * 提现
     */
    WITHDRAWAL_BANK_STATUS_ERROR(1301,"银行卡未审核成功，不能提现"),
    WITHDRAWAL_BANK_STATUS_AUDIT(1302,"银行卡待审核状态，不能修改"),
    WITHDRAWAL_UNFINISH_ERROR(1303,"提现未完成，不能修改银行卡"),

    /**
     * 以下需要添加额外的错误
     */
    PARAM_ERROR(9001,"参数错误"),

    LOG_ERROR(10000,"插入日志错误"),

    USER_NOT_EXIST(1004,"用户不存在"),

    PAY_AMOUNT_ERROR(10001,"支付金额错误"),

    ORDER_ERROR(1005,"订单操作失败"),

    ACCOUNT_NOT_EXIST(1006,"账户不存在"),

    WITHDRAWAL_ERROR(1007,"提现余额超过账户余额"),

    ORDER_STATUS_ERROR(1008,"订单状态异常"),

    ORDER_PAID(1009,"订单已支付"),

    ORDER_OVERTIME(1010,"订单超时"),

    ORDER_NO_EXIST(1011,"订单不存在"),

    ALI_PAY_ERROR(1012,"支付宝调起失败"),

    GET_OPENID_ERROR(1201,"获取微信openid失败")
    ;

    private int code;
    private String msg;

    ResultCode(int code, String msg){
        this.code =code;
        this.msg = msg;
    }

}
