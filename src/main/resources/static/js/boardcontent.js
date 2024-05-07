$(document).ready(function() {
    // 텍스트 영역의 기본 높이를 설정합니다.
    var initialHeight = $('#textArea').outerHeight();

    // 텍스트가 입력될 때마다 텍스트 영역의 높이를 조절합니다.
    $('#textArea').on('input', function() {
        // 현재 텍스트 영역에 입력된 텍스트의 높이를 계산합니다.
        var scrollHeight = $(this)[0].scrollHeight;

        // 텍스트 영역의 높이를 조절합니다.
        $(this).outerHeight(scrollHeight > initialHeight ? scrollHeight : initialHeight);
    });
});
