package com.amin.ojrat.service.external;

import com.amin.ojrat.dto.payamak.send.SendSmsParam;
import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.dto.payamak.status.GetSmsStatusParam;
import com.amin.ojrat.dto.payamak.status.GetSmsStatusResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "sms", url = "https://console.melipayamak.com/api")
public interface MelliPayamakClient {


    @PostMapping("/send/simple/5261b0a1467f4b1f998fa1fc64ade771")
    SendSmsResult sendSms(SendSmsParam param);


    @PostMapping("receive/status/5261b0a1467f4b1f998fa1fc64ade771")
    GetSmsStatusResult getStatus(GetSmsStatusParam getStatusParam);

}



