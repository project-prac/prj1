$(document).ready(function() {


window.addEventListener('load', () => {
  
    const todayElement = document.getElementById('today');
    let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let date = today.getDate();
    
    let week = new Array('일', '월', '화', '수', '목', '금', '토')
    let day = week[today.getDay()];
    
    
   // todayElement.innerHTML = year + '년 ' + month + '월 ' + date + '일 ' + day + '요일';
       
  
  
  
    fetch('/admin/reservation/reservedList.do', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({reserveDate: date })
  })
    .then(response => response.json())
    .then(data => {
      
      const checkInList = document.getElementById('list_checkIn');
      checkInList.innerHTML = '';
      
      reserves = data.reserves;
      console.log(reserves);
      
      reserves.forEach(reserve => {
        
        let str = '<li class="list">';
        str += '<div class="row"><div class="row_tit">예약번호 :</div><div class="">'+reserve.reservationNo+'</div></div>'
        str += '<div class="row"><div class="row_tit">예약자명 :</div><div class="">'+reserve.resName+'</div></div>'
        str += '<div class="row"><div class="row_tit">연락처 :</div><div class="">'+reserve.resPhone+'</div></div>'
        str += '<div class="row"><div class="row_tit">객실명 :</div><div class="">'+reserve.room.roomName+'</div></div>'
        str += '<div class="row"><div class="row_tit">체크인-체크아웃:</div><div class="">'+reserve.checkInDate+'~'+reserve.checkOutDate +'</div></div>'
        str += '<div class="row"><div class="row_tit">인원수</div><div class="">'+reserve.guestCount+'</div></div>'
        str += '<div class="row"><div class="row_tit">메모</div><div class="">'+reserve.resNote+'</div></div>'
        str+='</li>';
        
        
        checkInList.insertAdjacentHTML('beforeend', str); // 삽입
        
      })

      
    })
})

})
