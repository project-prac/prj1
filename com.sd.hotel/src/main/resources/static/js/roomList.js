
// 컨텍스트 경로 추출함수
const fnGetContextPath = ()=>{
  
  const host = location.host;  /* localhost:8080 반환 */
  const url = location.href;   /* 현재 웹페이지 전체 URL 반환 http://localhost:8080/mvc/getDate.do */
  const begin = url.indexOf(host) + host.length;
  const end = url.indexOf('/', begin + 1);
  return url.substring(begin, end);
}


$(document).on('click', '.roomName', (e) => {
  let roomNo = e.target.dataset.roomNo;

  location.href = fnGetContextPath()+'/room/room.do?roomNo=' + roomNo;

})