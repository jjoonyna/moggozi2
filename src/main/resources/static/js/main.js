
function getRandomColorFromList() {
    const colors = ['#FA5857', '#4B4BFA', '#00F572', '#3EFA97'];
    const randomIndex = Math.floor(Math.random() * colors.length);
    return colors[randomIndex];
}
var zindex = 10;
// 카드 클릭 이벤트 함수 정의
// cardClickHandler 함수 정의
function cardClickHandler(e) {

    var isShowing = false;

    if ($(this).hasClass("show")) {
        isShowing = true;
    }

    if ($("div.cards").hasClass("showing")) {
        // 카드가 바로 보이게끔한다.
        $("div.card.show")
            .removeClass("show");

        if (isShowing) {
            // 이 카드가 표시되고 있었습니다 - 그리드 재설정
            $("div.cards")
                .removeClass("showing");
        } else {
            // 이 카드는 표시되지 않습니다 - 그것으로 들어감
            $(this)
                .css({ zIndex: zindex })
                .addClass("show");
        }

        zindex++;

    } else {
        // 카드가 없을 때
        $("div.cards")
            .addClass("showing");
        $(this)
            .css({ zIndex: zindex })
            .addClass("show");

        zindex++;
    }
    
    // 클릭 후 스크롤 위치 유지
    $('html, body').animate({
        scrollTop: $(this).offset().top
    }, 500);
}

// div.card 클릭 이벤트 핸들러 등록
$(document).on('click', 'div.card', cardClickHandler);

/*페이징처리*/
function getRandomColor() {
    var colors = ['#FA5857', '#4B4BFA', '#00F572', '#3EFA97'];
    return colors[Math.floor(Math.random() * colors.length)];
}

// 페이징 버튼 클릭 시 색상 적용
$('.page_nation a').click(function() {
    // 이전에 클릭한 버튼의 스타일 제거
    $('.page_nation a.active').removeClass('active').css({
        'background-color': '',
        'color': '',
        'border': ''
    });

    // 클릭한 버튼에 색상 적용
    $(this).addClass('active').css({
        'background-color': getRandomColor(),
        'color': '#fff',
        'border': '1px solid ' + getRandomColor()
    });
});

var currentPage = 0; // 현재 페이지를 추적하는 변수

// getPage 함수: 페이지 번호를 받아 loadPage 함수를 호출합니다.
function getPage(pageNumber) {
    // 페이지 번호를 1부터 시작하도록 수정
    var adjustedPageNumber = pageNumber;
    currentPage = adjustedPageNumber; // 현재 페이지 업데이트
    loadPage(adjustedPageNumber); // loadPage 함수를 호출하여 해당 페이지를 로드합니다.
}

// loadPage 함수: AJAX를 사용하여 서버에 해당 페이지에 대한 요청을 보냅니다.
function loadPage(pageNumber) {
    $.ajax({
        url: "/main?page=" + pageNumber,
        type: 'GET',
        success: function(data) {
			console.log(pageNumber);
            // 반환된 데이터에서 카드 부분만 추출합니다.
            var cardsData = $(data).find(".cards").html();
            // 카드 부분을 현재 페이지의 .cards 요소에 추가합니다.
            $(".cards").empty().append(cardsData); // 이전 카드를 지우고 새로운 카드 추가
            // 페이지 버튼에 활성화 클래스 적용
            $('.pagination-link').removeClass('active');
            $('.pagination-link[data-page="' + pageNumber + '"]').addClass('active');
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

// 페이지 버튼 클릭 시 페이지 로드
$(document).on('click', '.page-link', function(e) {
    e.preventDefault(); // 기본 동작 방지

    var pageNumber = parseInt($(this).text()); // 클릭한 페이지 버튼의 텍스트(페이지 번호)를 가져옴

    currentPage = pageNumber - 1; // 현재 페이지 업데이트

    loadPage(currentPage); // 해당 페이지 번호로 페이지를 로드
});
// 이전 페이지 버튼 클릭 시
function prevPage() {
	
    getPage(currentPage - 1); // 이전 페이지 번호를 전달하여 페이지 로드
}
//	다음 페이지 버튼 클릭 시
function nextPage() {
	
    getPage(currentPage + 1); // 다음 페이지 번호를 전달하여 페이지 로드
}
//	처음 페이지 버튼 클릭 시
function pprevPage() {
	
    getPage(0); // 페이지 번호를 0으로 설정하여 페이지 로드
}
/*방만들기 버튼 클릭시*/
$(document).ready(function() {
    $('#writeBtn').click(function() {
        location = "/result";
    });
});
