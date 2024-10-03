
// 컨텍스트 경로 추출함수
const fnGetContextPath = ()=>{
  
  const host = location.host;  /* localhost:8080 반환 */
  const url = location.href;   /* 현재 웹페이지 전체 URL 반환 http://localhost:8080/mvc/getDate.do */
  const begin = url.indexOf(host) + host.length;
  const end = url.indexOf('/', begin + 1);
  return url.substring(begin, end);
}


document.addEventListener('DOMContentLoaded',function(){
  
  var userId = document.getElementById('userId');
  
  fetch(fnGetContextPath()+'/getId.do',{
    method:'POST',
    headers:{
      'Content-Type':'application/json'
    }
  })
  .then(response => response.json())
  .then(data => {
    console.log(data.userId);
    var getId = data.userId
    var manager = 'manager';
    var newId;
    if(getId!=null){
      
      console.log(getId.substring(7));
      var num = Number(getId.substring(7))+1
      console.log(num)
      console.log(manager+num)
      newId = manager+num;
      console.log(newId)
      //userId.name=newId;
      userId.value=newId;
      console.log(typeof newId);
    }else{
      newId='manager1'
      //userId.name=newId
      userId.value=newId;
    }
  })
  .catch(error =>{
    console.error('Error:', error);
  })
  
  /**/
  
  
})