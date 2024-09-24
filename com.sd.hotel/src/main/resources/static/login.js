var idCheck = false;

/*
정규표현식
^ : 문자열의 시작
[a-z]: 소문자 영문자로 시작
[a-z0-9_-]{4,15}: 두번째 문자부터 5자에서 16자까지
소문자,숫자,-,_ 만 사용할 수 잇음
$:문자열의 끝을 나타냄

*/


const fnLogin = () => {
  
  document.getElementById('frm-login').addEventListener('submit', (e) => {
    
    let inpId = document.getElementById('inpId');
    let regId =  /^[a-z][a-z0-9_-]{4,15}$/
  
    if(!regId.test(inpId.value)){
      e.preventDefault();
      alert('아이디를 확인하세요.');
      idCheck=false;
      return;
    }else{
      idCheck=true;
    }
    
  })
}


fnLogin();


/*로그인 실패*/

function getErrorMessageFromURL() {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get('exception');
}


// 페이지가 로드될 때 실행
/*
document.addEventListener('DOMContentLoaded',function(){
  const errorMessage = getErrorMessageFromURL();
  
  if(errorMessage){
    alert(errorMessage);
  }
})*/