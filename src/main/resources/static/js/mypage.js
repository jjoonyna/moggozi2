/**
 * 
 */
	function openDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				document.getElementById('userzip').value = data.zonecode;
				document.getElementById('useraddress1').value = data.address;
			}
		}).open();
	};
function myinfo(){
	location.href="mypage";
}
function mypwdchange(){
	location.href="mypwdchange";
}
function mydelete(){
	location.href="mydelete";
}

