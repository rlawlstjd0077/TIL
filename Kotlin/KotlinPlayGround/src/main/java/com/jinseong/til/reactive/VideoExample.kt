package com.jinseong.til.reactive

import java.awt.Image
import java.awt.TextField
import java.net.URL
import javax.accessibility.AccessibleStateSet
import javax.swing.JPanel
import javax.swing.text.html.ImageView


// 1. UI Component 의존적인 로직이기 때문에 애초에 확장될 수 있는 가능성이 거의 없음 (Abstract Class 자체가 Component 를 상속한 형태임)
    // - 확장성에 대한 Effect 가 해당 화면에 대한 부분으로 한정되기 때문에 애초에 고려할 필요가 많이 없는 상태
// 2. Concrete Class 관점에서 Abstract Class 의 상속되는 불필요한 의존성들이 없음
    // - Abstract Class 에서 선언된 UI Component, 혹은 Method 를 가져다 쓰는 경우도 많아서 그마나 응집도가 높은 상태 (?)
// 3. Abstract Class 의 사용목적과 어느정도 일맥상통하지 않을까?
    // - 흔히 Abstract Class, Interface의 목적성을 두고 Abstract Class = Is A ~ , Interface = Has A ~ 타입으로써의 추상화를 이야기함.
    // - Context 알고리즘을 수행하는 Abstract Class 자체가 타입으로써 외부에서 쓰이는 경우
        // - DashboardController 기능을 수행하는 JPanel Class


abstract class DashboardControllerBase : JPanel() {
    lateinit var titleTextField: TextField
    lateinit var statusImage: ImageView
    lateinit var chartPanel: JPanel
    //ETC Components ..

    fun initUi() {
        titleTextField.text = "Dashboard"
        drawChart()
        // 각종 Component 초기화 로직 ..
    }

    fun changeStatus(status: String) {
        val imageUrl = getStatusImageByStatus(status)
        statusImage.image = URL(imageUrl)
    }

    abstract fun drawChart()
    abstract fun getStatusImageByStatus(status: String): String
}


class ADashboardController: DashboardControllerBase() {
    override fun drawChart() {
        val lienChart = createLineChart()
        chartPanel.add(lienChart)
    }

    override fun getStatusImageByStatus(status: String): String {
        return "https://www.google.com/sample.png"
    }

    private fun createLineChart(): JPanel {
        /**
         * Line Chart Component 생성 코드 ..
         */
        return JPanel()
    }
}

class BDashboardController: DashboardControllerBase() {
    override fun drawChart() {
        val pieChart = createPieChart()
        chartPanel.add(pieChart)
    }

    override fun getStatusImageByStatus(status: String): String {
        return when(status) {
            "READY" -> "https://www.google.com/ready.png"
            "COMPLETE" -> "https://www.google.com/complete.png"
            else -> "https://www.google.com/sample.png"
        }
    }

    private fun createPieChart(): JPanel {
        /**
         * Pie Chart Component 생성 코드 ..
         */
        return JPanel()
    }
}

