//function submitForm() {
//    var formData = new FormData(); // FormData 객체 생성
//
//    // 폼 데이터 추가
//    formData.append('user_id', document.getElementById('user_id').value);
//    formData.append('mokkoji_person', document.getElementById('mokkoji_person').value);
//    formData.append('mokkoji_title', document.getElementById('mokkoji_title').value);
//    formData.append('mokkoji_intro', document.getElementById('mokkoji_intro').value);
//    formData.append('mokkoji_images', document.getElementById('mokkoji_images').files[0]); // 파일 추가
//
//    // POST 요청 보내기
//    fetch('/homes/upload', {
//        method: 'POST',
//        body: formData
//    })
//    .then(response => response.json())
//    .then(data => {
//        console.log('Success:', data);
//        // 성공 처리 로직 추가
//    })
//    .catch(error => {
//        console.error('Error:', error);
//        // 에러 처리 로직 추가
//    });
//}

//이렇게도 가능함