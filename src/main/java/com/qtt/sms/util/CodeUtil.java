package com.qtt.sms.util;

/**
 * 
 */
public class CodeUtil {
    
    /**
     * 包装轻码云的错误编码返回给客户
     * @param code
     * @return
     */
    public static int getCodeForAgent(String code) {
        int result = T.integerValue(code);
        return getCodeForAgent(result);
    }
    
    /**
     * 包装轻码云的错误编码返回给客户
     * @param code
     * @return
     */
    public static int getCodeForAgent(int code) {
        int result = 1;
        switch (code) {
        case 0 :
        case 1 :
        case 2 :
        case 3 :
        case 4 :
        case 6 :
        case 7 :
        case 8 :
        case 12 :
        case 13 :
        case 16 :
        case 17 :
        case 18 :
        case 20 :
        case 22 :
        case 23 :
        case 25 :
        case 79 :
        case 82 :
        case 94 :
        case 95 :
        case 96 :
        case 97 :
        case 98 :
        case 99 :
        case 100 :
        case 103 :
        case 104 :
        case 105 :
        case 110 :
        case 111 :
        case 127 :
        case 128 :
        case 129 :
        case 131 :
        case 132 :
        case 133 :
        case 134 :
        case 135 :
        case 137 :
        case 138 :
        case 139 :
        case 140 :
        case 141 :
        case 142 :
        case 143 :
        case 144 :
            result = code;  break;

        default:
            result = 1;     break;
        }
        return result;
    }
    
    /**
     * 获取轻码云错误编码的详情
     * http://qingmayun.com/zhuangtaima.html
     * @param code
     * @return
     */
    public static String getCodeDesc(String code) {
        int result = T.integerValue(code);
        return getCodeDesc(result);
    }
    
    /**
     * 获取轻码云错误编码的详情
     * http://qingmayun.com/zhuangtaima.html
     * @param code
     * @return
     */
    public static String getCodeDesc(int code) {
        String result = "";
        switch (code) {
            case 0: result = "请求成功"; break;
            case 1: result = "未知错误"; break;
            case 2: result = "未知的方法名"; break;
            case 3: result = "请求方式错误"; break;
            case 4: result = "参数非法"; break;
            case 5: result = "timestamp已过期"; break;
            case 6: result = "sign错误"; break;
            case 7: result = "重复提交"; break;
            case 8: result = "操作频繁"; break;
            case 11: result = "请求的xml格式不对"; break;
            case 12: result = "请使用post"; break;
            case 13: result = "请求url格式不正确"; break;
            case 15: result = "时间戳超出有效时间范围"; break;
            case 16: result = "请求json格式不对"; break;
            case 17: result = "数据库操作失败"; break;
            case 18: result = "参数为空"; break;
            case 19: result = "订单已存在"; break;
            case 20: result = "用户不存在"; break;
            case 21: result = "子账号余额不足"; break;
            case 22: result = "操作频繁"; break;
            case 23: result = "开发者余额不足"; break;
            case 25: result = "手机格式不对"; break;
            case 26: result = "手机号存在"; break;
            case 27: result = "子账号名称已存在"; break;
            case 28: result = "子账号名称过长"; break;
            case 29: result = "回调开发者服务器异常"; break;
            case 30: result = "回调地址为空"; break;
            case 31: result = "appId为空或者没有传值"; break;
            case 32: result = "主叫号码为空或者没有传值"; break;
            case 33: result = "被叫号码为空或者没有传值"; break;
            case 34: result = "子账号为空或者没有传值"; break;
            case 35: result = "主叫号码和被叫号码相同"; break;
            case 36: result = "验证码格式不对（4-8位数字）"; break;
            case 37: result = "limit格式不对"; break;
            case 38: result = "start格式不对"; break;
            case 39: result = "验证码为空或者缺少此参数"; break;
            case 40: result = "用户名或者密码错误"; break;
            case 50: result = "短信或者语音验证码错误"; break;
            case 51: result = "显示号码与被叫号码一样，不允许呼叫"; break;
            case 52: result = "回拨主叫号码格式错误"; break;
            case 53: result = "被叫号码格式错误"; break;
            case 54: result = "显号格式错误"; break;
            case 55: result = "应用不包含此子账号"; break;
            case 56: result = "开发者不包含此应用"; break;
            case 60: result = "请求数据不存在"; break;
            case 61: result = "app不存在"; break;
            case 62: result = "developerId 请求错误"; break;
            case 63: result = "app未上线"; break;
            case 64: result = "请求Content-Type错误"; break;
            case 65: result = "请求Accept错误"; break;
            case 70: result = "手机号未绑定"; break;
            case 71: result = "通知类型已停用或者未创建"; break;
            case 72: result = "balance格式不对（必须为大于等于0的double）"; break;
            case 73: result = "charge格式不对（必须为大于等于0的double）"; break;
            case 74: result = "主叫和子账户绑定的手机号不相同"; break;
            case 75: result = "子账户没有绑定手机号"; break;
            case 76: result = "时间格式不对"; break;
            case 77: result = "开始时间小于结束时间"; break;
            case 78: result = "开始时间和結束時間必須是同一天"; break;
            case 79: result = "服务器内部异常"; break;
            case 80: result = "子账号不存在"; break;
            case 81: result = "通知计费系统失败"; break;
            case 82: result = "参数校验失败"; break;
            case 83: result = "充值失败"; break;
            case 84: result = "子账号没有托管 不能进行充值"; break;
            case 85: result = "开发者不包含子帐号"; break;
            case 86: result = "DEMO不能进行充值"; break;
            case 87: result = "IQ类型错误"; break;
            case 90: result = "回调地址为空"; break;
            case 91: result = "没有语音"; break;
            case 93: result = "没有这个语音文件或者审核没通过"; break;
            case 94: result = "每批发送的手机号数量不得超过100个"; break;
            case 95: result = "未开通邮件短信功能"; break;
            case 96: result = "邮件模板未审核通过"; break;
            case 97: result = "邮件模板未启用"; break;
            case 98: result = "同一手机号每天只能发送n条相同的内容"; break;
            case 99: result = "相同的应用每天只能给同一手机号发送n条不同的内容"; break;
            case 100: result = "短信内容不能含有关键字"; break;
            case 101: result = "配置短信端口号失败"; break;
            case 102: result = "一个开发者只能配置一个端口"; break;
            case 103: result = "应用的邮件模板不存在"; break;
            case 104: result = "相同的应用当天给同一手机号发送短信的条数小于等于n"; break;
            case 105: result = "本开发者只能发短信给移动手机"; break;
            case 106: result = "时间戳(timestamp)参数为空"; break;
            case 107: result = "签名(sig)参数为空"; break;
            case 108: result = "时间戳(timestamp)格式错误"; break;
            case 109: result = "子账号已被关闭"; break;
            case 110: result = "解析post数据失败，post数据不符合格式要求"; break;
            case 111: result = "匹配到黑名单"; break;
            case 112: result = "accountSid参数为空"; break;
            case 113: result = "短信内容和模板匹配度过低"; break;
            case 114: result = "clientNumber参数为空"; break;
            case 115: result = "charge参数为空"; break;
            case 116: result = "charge格式不对，不能解析成double"; break;
            case 117: result = "fromTime参数为空"; break;
            case 118: result = "toTime参数为空"; break;
            case 119: result = "fromTime参数格式不正确"; break;
            case 120: result = "toTime参数格式不正确"; break;
            case 122: result = "date参数为空"; break;
            case 123: result = "date的值不在指定范围内"; break;
            case 124: result = "没有查询到话单（所以没有生成下载地址）"; break;
            case 125: result = "emailTemplateId参数为空"; break;
            case 126: result = "to参数为空"; break;
            case 127: result = "param参数为空"; break;
            case 128: result = "templateId参数为空"; break;
            case 129: result = "模板类型错误"; break;
            case 130: result = "serviceType参数为空"; break;
            case 131: result = "content参数为空"; break;
            case 132: result = "本接口的邮件短信业务只能发送移动手机"; break;
            case 133: result = "错误的业务类型"; break;
            case 134: result = "没有和内容匹配的模板"; break;
            case 135: result = "应用没有属于指定类型业务并且已审核通过、已启用的模板"; break;
            case 136: result = "开发者不能调用此接口"; break;
            case 137: result = "没有权限自定义邮件内容"; break;
            case 138: result = "短信没有签名不能发送"; break;
            case 139: result = "短信签名已进入黑名单不能发送"; break;
            case 140: result = "邮件短信发送间隔太小"; break;
            case 141: result = "一小时内发送给单个手机次数超过限制"; break;
            case 142: result = "一天内发送给单个手机次数超过限制"; break;
            case 143: result = "含有非法关键字"; break;
            case 144: result = "mobile参数为空"; break;
            case 145: result = "新手机号和旧手机号相同，不必修改"; break;
            case 146: result = "minutes格式不对（必须为大于等于0的double）"; break;

            default: result = "未知错误"; break;
        }
        return result;
    }
 
}
