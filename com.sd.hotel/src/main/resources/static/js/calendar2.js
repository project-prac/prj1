
document.addEventListener('DOMContentLoaded', function() {


fetch('/admin/reservation/roomList.do', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({})
})
  .then(response => response.json())
  .then(data => {
    console.log("data:",data);

    rooLists = data.roomList;
    
    let roomArray = []; // roomArray 초기화
    
    // 현재 날짜 기준으로 날짜 범위 계산
    let currentDate = new Date();
    let year = currentDate.getFullYear();
    let month = currentDate.getMonth(); // 0부터 시작 (1월: 0, 2월: 1, ...)
    let lastDayOfMonth = new Date(year, month + 1, 0).getDate(); // 해당 월의 마지막 날 계산

    // 해당 월의 1일부터 마지막 날까지 반복
    for (let day = 1; day <= lastDayOfMonth; day++) {
      rooLists.forEach(roomList => {
        if (roomList.depth != 0) {
          // 해당 월, 날짜에 대해 객실 정보 추가
          roomArray.push({
            title: roomList.roomName + ':(/'+roomList.totalRoom,   // roomList에서 원하는 데이터 추출
            start: new Date(year, month, day), // 현재 연도와 월을 기준으로 각 날짜에 추가
            // end는 필요 없으므로 생략
          });
        }
      });
    }

    console.log(roomArray); // roomArray가 올바르게 채워졌는지 확인

    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      events: roomArray, // roomArray를 events에 사용
      displayEventTime: false // 시간 표시 안 함
    });

    calendar.render();
  })






function loadMonthData(date) {
  // 여기서 서버로부터 해당 월의 데이터만 가져오는 로직을 구현
  // 가져온 데이터로 calendar.addEvent() 등을 사용하여 이벤트 추가
  
  fetch('/admin/reservation/reservationList.do', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({})
  })
  .then(response => response.json())
  .then(data => {
      
    })

}

});