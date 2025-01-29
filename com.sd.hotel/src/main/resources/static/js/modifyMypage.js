/**
 * 
 */

//modify-mail클릭하면AbortController disabled풀리면서AbortController
//인증번호 보내기가 활성화되야함



const fnModifyMail = () => {

  const modifyBtn = document.getElementById('modify-mail');
  const verifyBtn = document.getElementById('verify-btn');
  const InputEmail = document.getElementById('InputEmail');
  modifyBtn.style.visibility = 'hidden';
  verifyBtn.style.visibility='visible';
  InputEmail.readOnly = false;
  
}

//fnModifyMail();

document.getElementById('modify-mail').addEventListener('click', fnModifyMail);