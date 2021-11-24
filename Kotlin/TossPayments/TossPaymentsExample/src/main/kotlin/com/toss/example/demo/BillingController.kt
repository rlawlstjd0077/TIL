package com.toss.example.demo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import org.apache.tomcat.util.json.JSONParser
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*


@Controller
class BillingController {
    private val objectMapper = ObjectMapper()

    @RequestMapping("/success")
    fun success(
        /**
         * 1. Request Parameter 정보
         *
         * - authKey: 빌링키를 얻을 때 사용하는 인증 키
         * - customerKey: 가맹점에서 사용하는 사용자별 고유 ID
         */
        @RequestParam requestParams: Map<String, String>,
        model: Model,
    ): String {
        /**
         * 2. 발급 받은 시크릿 키 Base64 인코딩
         */
        val encodedAuthHeader = Base64.getEncoder().encodeToString(("$SECRET_KEY:").toByteArray())

        /**
         * 3. 빌링키 발급 Reequest 생성
         */
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/${requestParams.getValue("authKey")}"))
            .header("Authorization", "Basic $encodedAuthHeader")
            .header("Content-Type", "application/json")
            .method("POST", HttpRequest.BodyPublishers.ofString("{\"customerKey\":\"${requestParams.getValue("customerKey")}\"}"))
            .build()

        val response: HttpResponse<String> =
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())

        return if (response.statusCode() == OK.value()) {
            /**
             * 4. 빌링키, 카드 정보 포함된 Json 으로 성공 View 리턴
             */
            val jsonNode = objectMapper.readTree(response.body())
            model.addAttribute("response", jsonNode.toPrettyString())
            "success"
        } else {
            /**
             * 5. 실패 View 리턴
             */
            model.addAttribute("message", "카드 정보를 저장하는데 실패하였습니다.")
            "fail"
        }
    }

    @RequestMapping(value = ["/fail"])
    fun billingFail(
        /**
         * 1. Request Parameter 정보
         *
         * - code: 실패 코드
         * - customerKey: 실패 사유
         */
        @RequestParam(required = false) code: String?,
        @RequestParam(required = false) message: String?,
        model: Model,
    ): String {
        model.addAttribute("code", code)
        model.addAttribute("message", message)
        return "fail";
    }

    companion object {
        const val SECRET_KEY = "발급 받은 시크릿 키"
    }
}
