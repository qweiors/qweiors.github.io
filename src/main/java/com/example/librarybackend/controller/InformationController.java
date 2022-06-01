package com.example.librarybackend.controller;

import com.alipay.api.AlipayApiException;
import com.example.librarybackend.service.User;
import com.example.librarybackend.service.UserOption;
import com.example.librarybackend.service.UserServe;
import com.example.librarybackend.utils.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.UUID;

@Controller
public class InformationController {
  private UserServe userServe = new UserServe();
  private HashMap<String, String> orderUserMap = new HashMap<>();

  @GetMapping("/toInformationMain")
  public String toInformationView(@ModelAttribute("id") String id, @ModelAttribute("windo") String window, Model model)
  {
    model.addAttribute("user", userServe.findById(id));
    model.addAttribute("windo", window);
    return "informationMain";
  }

  @GetMapping("/toInformation")
  public String mainView(@ModelAttribute("id") String id, Model model) {
    model.addAttribute("user", userServe.findById(id));
    return "information";
  }

  @GetMapping("getMembershipRequest")
  public String getMembershipRequest(@ModelAttribute("id") String id, Model model) {
    if (!userServe.getMembership(id)) {
      model.addAttribute("errmsg", "注册失败");
    }
    model.addAttribute("user", userServe.findById(id));
    return "information";
  }

  @GetMapping("cancelMembershipRequest")
  public String cancelMembershipRequest(@ModelAttribute("id") String id, Model model) {
    if (!userServe.cancelMembership(id)) {
      model.addAttribute("errmsg", "取消失败");
    }
    model.addAttribute("user", userServe.findById(id));
    return "information";
  }

  @GetMapping("/recharge")
  public ModelAndView recharge(@ModelAttribute("id") String id, @ModelAttribute("money") String money) throws AlipayApiException {
    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
        AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
        AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    //设置请求参数
    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
    alipayRequest.setReturnUrl(AlipayConfig.return_url);
    alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
    //商户订单号，商户网站订单系统中唯一订单号，必填
    String out_trade_no = UUID.randomUUID().toString();
    orderUserMap.put(out_trade_no, id);
    //付款金额，必填
    //订单名称，必填
    String subject = "充值";
    //商品描述，可空
    String body = "aaa";
    // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    String timeout_express = "1c";

    String requestInformation = "{\"out_trade_no\":\""+ out_trade_no +"\","
            + "\"total_amount\":"+ money +","
            + "\"subject\":\""+ subject +"\","
//            + "\"body\":\""+ body +"\","
//            + "\"timeout_express\":\""+ timeout_express +"\","
            + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
    System.out.println(requestInformation);

    alipayRequest.setBizContent(requestInformation);

    //请求
    return new ModelAndView("redirect:" + alipayClient.pageExecute(alipayRequest, "GET").getBody());
  }

  @GetMapping("/rechargeCallback")
  public String rechargeCallback(@ModelAttribute("out_trade_no") String trade, @ModelAttribute("total_amount") String amount, Model model) {
    double money = Double.parseDouble(amount);
    User user = userServe.findById(orderUserMap.get(trade));
    user.setAccount((float) (user.getAccount() + money));
    (new UserOption()).update(user.getId(), user);
    model.addAttribute("user", user);
    return "information";
  }
}
