var nameCheck = false;
var idCheck = false;
var emailCheck = false;
var passwordCheck = false;
var passwordConfirm = false;

var mobileCheck = false;
var agreeCheck = false;



// 컨텍스트 경로 추출함수
const fnGetContextPath = ()=>{
  
  const host = location.host;  /* localhost:8080 반환 */
  const url = location.href;   /* 현재 웹페이지 전체 URL 반환 http://localhost:8080/mvc/getDate.do */
  const begin = url.indexOf(host) + host.length;
  const end = url.indexOf('/', begin + 1);
  return url.substring(begin, end);
}


/*
정규표현식
^ : 문자열의 시작
[a-z]: 소문자 영문자로 시작
[a-z0-9_-]{4,15}: 두번째 문자부터 5자에서 16자까지
소문자,숫자,-,_ 만 사용할 수 잇음
$:문자열의 끝을 나타냄

*/

const fnCheckName = () =>{
  let inpName = document.getElementById('inp-name');
  let name = inpName.value;
  let totalByte = 0;
  for(let i =0;i<name.length;i++){
    if(name.charCodeAt(i)>127){
      /*
      name.charCodeAt(i)는 문자열 name의 i 번째 위치에 있는 문자의 유니코드 값을 반환하는데, 
      이 값이 127을 초과하는 경우, 일반적으로 한글이나 기타 비영어권 문자는 2바이트를 차지하는 것으로 간주됨.
      한글과 같은 문자를 구분하여 처리하기 위한 조건. 
      */
      totalByte+= 2;
    }else{
      totalByte++;
    }
  }
  nameCheck = (totalByte <=20);
  if(!nameCheck){
    alert('이름은 20 바이트를 초과할 수 없습니다.');
    inpName.focus();
  } 
  
}


const fnCheckId = (e) =>{

  let inpId = document.getElementById('InputId');
  let regId = /^[a-z][a-z0-9_-]{4,15}$/
  
  if(!regId.test(inpId.value)){
    e.preventDefault();
    alert('아이디는 영문소문자,숫자,_-를 포함하여 5자리~16자리만 가능합니다');
    return;
  }
  
  fetch(fnGetContextPath() + '/check-availability',{
    method:'POST',
    headers:{
      'Content-Type':'application/json'
    },
    body:JSON.stringify({
      'userId':inpId.value
    })
  })
  .then( console.log(inpId.value))
  .then(response => response.json())
  .then(resData =>{
    if(resData.enableId){
      document.getElementById('id-comment').innerHTML ='사용가능한 ID입니다'
       idCheck=true;
    }else{
      document.getElementById('id-comment').innerHTML ='이미 사용중인 ID입니다'
      idCheck=false;
    }
  })
  
}


const fnCheckEmail = () =>{
  
  let inpEmail = document.getElementById('InputEmail');
  let regEmail = /^[A-Za-z0-9-_]{2,}@[A-Za-z0-9]+(\.[A-Za-z]{2,6}){1,2}$/;
  if(!regEmail.test(inpEmail.value)){
    alert('이메일 형식이 올바르지 않습니다.');
    emailCheck = false;
    return;
  }
  
  fetch('/user/code',{
    method: 'POST',
    headers: {
      'Content-Type' : 'application/json'
    },
    body: JSON.stringify({
      'email' : inpEmail.value
    })
  })
  .then(response => response.json())
  .then(resData =>{
    alert(inpEmail.value + '로 인증코드를 전송했습니다');
    let inpCode = document.getElementById('inp-code');
    let bthVerifyCode = document.getElementById('btn-verify-code');
    inpCode.disabled = false;
    bthVerifyCode.disabled = false;
    bthVerifyCode.addEventListener('click', (e)=>{
      if(resData.code == inpCode.value){
        alert('인증되었습니다.');
        emailCheck = true;
      }else{
        alert('잘못된 인증번호입니다.');
        emailCheck = false;
      }
      
    })
  })
  
/*    
const fnCheckPassword = () =>{
  let inpPW = document.getElementById('');
}
  */
  
  
}

const fnSignup = () => {
  
  document.getElementById('frm-signup').addEventListener('submit', (e) => {
    
    if(!nameCheck){
      e.preventDefault();
      alert('성명을 확인하세요.')
      return;
    }
    if(!idCheck){
      e.preventDefault();
      alert('아이디를 확인하세요.');
      return;
    }
    

    
  })
}


document.getElementById('inp-name').addEventListener('keyup', fnCheckName);
document.getElementById('btn-id').addEventListener('click',fnCheckId);
document.getElementById('btn-email').addEventListener('click',fnCheckEmail);

fnSignup();