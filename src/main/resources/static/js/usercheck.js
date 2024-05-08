$(document).ready(function() {
    var joinButton = $('#joinButton');

    // 회원가입 버튼 상태를 관리하는 함수
    function updateJoinButtonState() {
        var isValid = true;

        // 아이디 유효성 검사
        isValid = isValid && validateUsername();

        // 이름 유효성 검사
        isValid = isValid && validateNormalname();

        // 닉네임 유효성 검사
        isValid = isValid && validateUsernick();

        // 이메일 유효성 검사
        isValid = isValid && validateUseremail();
        
        // 비밀번호 일치 여부 확인
        isValid = isValid && validatePasswordMatch();

        // 핸드폰 번호 유효성 검사
        isValid = isValid && validateUserph();

        // 생년월일 유효성 검사
        isValid = isValid && validateUseryear();


        // 상세 주소 유효성 검사
        isValid = isValid && validateUseraddress2();
        
        // 주소 유효성 검사
        isValid = isValid && validateUseraddress1();

        // 성별 선택 여부 확인
        isValid = isValid && validateUsergender();

        // 모든 조건이 충족되면 버튼 활성화
        if (isValid) {
            joinButton.prop('disabled', false);
        } else {
            joinButton.prop('disabled', true);
        }
    }

    // 아이디 유효성 검사 함수
    function validateUsername() {
        var username = $('#username').val().trim();
        var usernamePattern = /^[a-zA-Z0-9_]+$/;
        if (!usernamePattern.test(username)) {
            $('#usernameError').text('영문자, 숫자, 밑줄(_)만 입력 가능합니다.');
            return false;
        } else {
            $('#usernameError').text('');
            return true;
        }
    }

    // 이름 유효성 검사 함수
    function validateNormalname() {
        var normalname = $('#normalname').val().trim();
        if (normalname === '') {
            $('#normalnameError').text('이름을 입력하세요.');
            return false;
        } else {
            $('#normalnameError').text('');
            return true;
        }
    }

    // 닉네임 유효성 검사 함수
    function validateUsernick() {
        var usernick = $('#usernick').val().trim();
        if (usernick === '') {
            $('#usernickError').text('닉네임을 입력하세요.');
            return false;
        } else {
            $('#usernickError').text('');
            return true;
        }
    }
     // 이메일 유효성 검사 함수
    function validateUseremail() {
        var useremail = $('#useremail').val().trim();
        var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(useremail)) {
            $('#useremailError').text('올바른 이메일 형식이 아닙니다.');
            return false;
        } else {
            $('#useremailError').text('');
            return true;
        }
    }

    // 비밀번호 일치 여부 검사 함수
    function validatePasswordMatch() {
        var password = $('#password').val();
        var confirmPassword = $('#join_passwd').val();
        if (password !== confirmPassword) {
            $('#confirmPasswordError').text('비밀번호가 일치하지 않습니다.');
            return false;
        } else if(password == ""){
            $('#confirmPasswordError').text('');
            return false;
        } else{
			$('#confirmPasswordError').text('비밀번호가 일치합니다.');
            return true;
		}
    }

    // 핸드폰 번호 유효성 검사 함수
    function validateUserph() {
        var userph = $('#userph').val().trim();
        var phonePattern = /^\d{11}$/;
        if (!phonePattern.test(userph)) {
            $('#userphError').text('핸드폰 번호는 열한 자리 숫자로 입력하세요 (예: 00000000000).');
            return false;
        } else {
            $('#userphError').text('');
            return true;
        }
    }

    // 생년월일 유효성 검사 함수
    function validateUseryear() {
        var useryear = $('#useryear').val().trim();
        var yearPattern = /^\d{6}$/;
        if (!yearPattern.test(useryear)) {
            $('#useryearError').text('생년월일을 네 자리 숫자로 입력하세요 (예: 9900101).');
            return false;
        } else {
            $('#useryearError').text('');
            return true;
        }
    }

   

    // 상세 주소 유효성 검사 함수
    function validateUseraddress2() {
        var useraddress2 = $('#useraddress2').val().trim();
        if (useraddress2 === '') {
            $('#useraddress2Error').text('상세 주소를 입력하세요.');
            return false;
        } else {
            $('#useraddress2Error').text('');
            return true;
        }
    }
    function validateUseraddress1() {
        var useraddress1 = $('#useraddress1').val().trim();
        if (useraddress1 === '') {
            $('#useraddress2Error').text('주소를 입력하세요.');
            return false;
        } else {
            $('#useraddress2Error').text('');
            return true;
        }
    }

    // 성별 선택 여부 검사 함수
    function validateUsergender() {
        var usergender = $('input[name="usergender"]:checked').val();
        if (!usergender) {
            $('#genderError').text('성별을 선택하세요.');
            return false;
        } else {
            $('#genderError').text('');
            return true;
        }
    }

    // 각 입력 필드의 값이 변경될 때마다 유효성 검사 및 버튼 상태 업데이트
    $('#username, #normalname, #usernick, #password, #join_passwd, #userph, #useryear, #useremail, #useraddress2, input[name="usergender"]').on('input change', updateJoinButtonState);

    // 회원가입 버튼 클릭 시
    $('#joinButton').on('click', function() {
        // 여기서 실제 회원가입 처리 로직 수행
        // 서버로 데이터 전송 등
        alert('회원가입 완료!'); // 간단한 알림 메시지 (실제로는 서버로 데이터 전송 등의 작업을 수행)
    });
});
        
function checkUsernameDuplicate() {
    var username = $('#username').val();

            console.log(username);
    $.ajax({
        type: 'GET',
        url: '/check/'+username,
       
        success: function(data) {
            console.log(data);

            if (data==1) {
                $('#usernameError').text('이미 사용 중인 아이디입니다.');
            } else {
                $('#usernameError').text('사용 가능한 아이디입니다.');
            }
        },
        error: function(xhr, status, error) {
            console.error('AJAX 요청 실패: ', status, error);
            // 에러 처리 로직 추가
        }
    });
}

function checkUsernickDuplicate() {
    var usernick = $('#usernick').val();

            console.log(usernick);
    $.ajax({
        type: 'GET',
        url: '/checknick/'+usernick,
       
        success: function(data) {
            console.log(data);

            if (data==1) {
                $('#usernickError').text('이미 사용 중인 닉네임입니다.');
            } else {
                $('#usernickError').text('사용 가능한 닉네임입니다.');
            }
        },
        error: function(xhr, status, error) {
            console.error('AJAX 요청 실패: ', status, error);
            // 에러 처리 로직 추가
        }
    });
}