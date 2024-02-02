document.addEventListener('DOMContentLoaded', function() {
        // DOM이 완전히 로드된 후에 실행할 코드
        document.getElementById("searchForm").addEventListener("submit", function(event) {
            let text = document.getElementById("text").value;
            if (text.length === 0) {
                alert("캐릭터명을 입력해주세요");
                event.preventDefault(); // 폼 제출 막기
            }
        });
    });