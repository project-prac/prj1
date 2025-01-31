document.addEventListener('DOMContentLoaded', function () {

  fetch('/admin/reservation/roomList.do', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({})
  })
    .then(response => response.json())
    .then(data => {
      let rooLists = data.roomList;
      let roomArray = [];

      let currentDate = new Date();
      let year = currentDate.getFullYear();
      let month = currentDate.getMonth();
      let lastDayOfMonth = new Date(year, month+1, 0).getDate();

      let fetchPromises = []; // 비동기 호출을 담을 배열
      
      console.log(lastDayOfMonth);

      for (let day = 1; day <= lastDayOfMonth; day++) {
        let targetDate =  moment([year, month, day]).format('YYYY-MM-DD');
        console.log(`날짜 확인: ${targetDate}`);
        // 각 날짜에 대해 비동기 호출 추가
        fetchPromises.push(
          fngetResList(targetDate).then(reserves => {
            rooLists.forEach(roomList => {
              if (roomList.depth != 0) {
                
                let reservedCount = reserves.filter(reservation => reservation.roomNo === roomList.roomNo).length;
                roomArray.push({
                  title: roomList.roomName + ':('+reservedCount + '/' + roomList.totalRoom,
                  start: targetDate
                });
              }
            });
          })
        );
        console.log(roomArray)
      }

      // 모든 비동기 호출이 완료된 후 달력 렌더링
      Promise.all(fetchPromises).then(() => {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth',
          events: roomArray,
          displayEventTime: false,
          eventClick:function(info){
              var eventDate = info.event.start; // Date 객체
              var formattedDate = eventDate.toISOString().split('T')[0]; //
              console.log(formattedDate)
             //window.location.href = `/admin/reservation/reservedList.do?date=${formattedDate}`;
          }
        });

        calendar.render();
      });
    });

  function fngetResList(date) {
    return fetch('/admin/reservation/resList.do', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ reservationDate: date })
    })
      .then(response => response.json())
      .then(data => data.reserves);
  }
});
