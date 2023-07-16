package com.amin.ojrat.service.external;

import com.amin.ojrat.dto.payamak.send.SendSmsParam;
import com.amin.ojrat.dto.payamak.send.SendSmsResult;
import com.amin.ojrat.dto.payamak.status.GetSmsStatusParam;
import com.amin.ojrat.dto.payamak.status.GetSmsStatusResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "sms", url = "https://console.melipayamak.com/api")
public interface MelliPayamakClient {

    @PostMapping("/send/simple/950abd716ef64097a28642e28d69fc64")
    SendSmsResult sendSms(SendSmsParam param);

    @PostMapping("receive/status/950abd716ef64097a28642e28d69fc64")
    GetSmsStatusResult getStatus(GetSmsStatusParam getStatusParam);
}
