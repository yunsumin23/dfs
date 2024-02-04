function hyerangTimeCal() {
    var hyerangT = document.getElementById("hyerangT").value;
    var hyerangTElements = document.getElementsByName("hyerangTn");
    var selectedValue;

    for (var i = 0; i < hyerangTElements.length; i++) {
        if (hyerangTElements[i].checked) {
            selectedValue = hyerangTElements[i].value;
            break;
        }
    }
    console.log(selectedValue);
    hyerangT = hyerangT - 6 * selectedValue;
    console.log("벞교" + selectedValue + "세트는" + hyerangT);

    // AJAX로 서버에 값 전송
    $.ajax({
        type: 'post', // 타입 (get, post, put 등등)
        url: '/dfsc/search_result.jsp', // 요청할 서버url
        async: true, // 비동기화 여부 (default : true)
        headers: { // Http header
            "Content-Type": "application/json",
            "X-HTTP-Method-Override": "POST"
        },
        dataType: 'text', // 데이터 타입 (html, xml, json, text 등등)
        data: {
            "hyerangT": hyerangT // 단일한 값을 직접 전송
        },
        success: function (result) { // 결과 성공 콜백함수
        	updateTotalTime();
//            console.log(result);
        },
        error: function (request, status, error) { // 결과 에러 콜백함수
            console.log(error);
        }
    });
}
function updateTotalTime() {
    // hyerangT를 이용하여 totalTime을 계산 (가정: 특정한 계산 방식 사용)
    var totalTime = hyerangT * 2; // 예시: hyerangT를 2배로 설정

    // totalTime을 HTML 엘리먼트에 업데이트
    document.getElementById('totalTimeElementId').value = totalTime;
}

function hakalTimeCal() {
	var hakalTElements = document.getElementsByName("hakalTn");
	var selectedValue;

	for (var i = 0; i < hakalTElements.length; i++) {
		if (hakalTElements[i].checked) {
			selectedValue = hakalTElements[i].value;
			break;
		}
	}
	console.log(selectedValue);
}

function adukTimeCal() {
	var adukTElements = document.getElementsByName("adukTn");
	var selectedValue;

	for (var i = 0; i < adukTElements.length; i++) {
		if (adukTElements[i].checked) {
			selectedValue = adukTElements[i].value;
			break;
		}
	}
	console.log(selectedValue);
}