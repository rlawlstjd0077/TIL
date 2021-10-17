/*

[1, 3, 9, 12, 20]이라는 정렬된 배열이 있을 때, 9의 위치를 찾는 함수를 작성하라
 
 [1,2,2,2,2,2,9,9,9,9,10]
 2
*/
fun main(args: Array<String>) {
    
    val list = listOf(1, 3, 9, 12, 20)
    
    verifyExecution(0) { findPosition(list, 1) }
    verifyExecution(1) { findPosition(list, 3) }
    verifyExecution(2) { findPosition(list, 9) }
	verifyExecution(3) { findPosition(list, 12) }
	verifyExecution(4) { findPosition(list, 20) }
    verifyExecution(5) { findPosition(list, 20) }
    verifyExecution(-1) { findPosition(list, 30) }
    verifyExecution(-1) { findPosition(list, -1) }
    
    val list2 = listOf(1, 3, 3, 3, 9, 9, 9, 20)    
    verifyExecution(1) { findPosition(list2, 3) }
    verifyExecution(4) { findPosition(list2, 9) }
}

fun findPosition(list: List<Int>, number: Int): Int {
    var low = 0
    var high = list.size - 1
    var mid = 0
    
    while (low <= high) {

        mid = (high + low) / 2        
        val foundNumber = list[mid]
        
        if (number > foundNumber) {
            low = mid + 1
        } else if (number < foundNumber) {
            high = mid - 1
        } else {
            return findFirst(list, mid);
        }
    }
    
    return -1
}

fun findFirst(list: List<Int>, index: Int): Int {
    var idx = index
    while (idx > 0 && list[idx] == list[idx - 1]) {
        idx -= 1
    }
    return idx;
}

fun verifyExecution(expected: Int, function: () -> Int) {
	val actual = function()
    if (actual == expected) {
        println("You are right! expected: $expected, actual: $actual")
    } else {
		println("You are wrong expected: $expected, actual: $actual")
    }
}