//package com.jinseong.twitter
//
//import com.twitter.clientlib.ApiException
//import com.twitter.clientlib.TwitterCredentials
//import com.twitter.clientlib.api.TwitterApi
//import com.twitter.clientlib.model.AddOrDeleteRulesRequest
//import com.twitter.clientlib.model.AddOrDeleteRulesResponse
//import org.junit.jupiter.api.Test
//
//
///**
// * @author Jay
// */
//class TwitterAPiTest {
//
//    @Test
//    fun `test`() {
//        // given
//        val credentials = TwitterCredentials()
//
//        credentials.twitterConsumerKey = "JF14Z0Pct1sJAyWqrupkbwMoM"
//        credentials.twitterConsumerSecret = "R0WK9VFswSNp4peRJrYZhwRuGOYtDOxANEc7JNWMPVvvPpQm13"
//        credentials.bearerToken =
//            "AAAAAAAAAAAAAAAAAAAAALG2YwEAAAAASVnk%2FrakmJ8P3NwfbmBayomt88M%3DklGdEVXIrNHu7Y52tbjnOeFolkN6cH0b65hLe3J910HVsE7ru3"
//        credentials.twitterToken = "1490238224299528198-zQ1TD7MvNXsR2UKZ0czyj6NgD5ejaC"
//        credentials.twitterTokenSecret = "92BsW1PFq1GAgAl0ORF9krjWyl0pdRn3eshh8N8xkDRWN"
//
//        val twitterApi = TwitterApi(credentials)
//
//        val dryRun =
//            true // Boolean | Dry Run can be used with both the add and delete action, with the expected result given, but without actually taking any action in the system (meaning the end state will always be as it was when the request was submitted). This is particularly useful to validate rule changes.
//
//        val addOrDeleteRulesRequest = AddOrDeleteRulesRequest() // AddOrDeleteRulesRequest |
//
//        try {
//            val result: AddOrDeleteRulesResponse = twitterApi.addOrDeleteRules(dryRun, addOrDeleteRulesRequest)
//            println(result)
//        } catch (e: ApiException) {
//            System.err.println("Exception when calling TwitterApi#addOrDeleteRules")
//            System.err.println("Status code: " + e.code)
//            System.err.println("Reason: " + e.responseBody)
//            System.err.println("Response headers: " + e.responseHeaders)
//            e.printStackTrace()
//        }
//
//        // when
//
//        // then
//    }
//}
