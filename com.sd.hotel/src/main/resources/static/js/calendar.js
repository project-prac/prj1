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
      let rooLists = data.roomList;
      let roomArray = [];



      function getDateRange(year, month) {
        
        let dates = [];

        /* 이전 달 10일 */
        
        let prevMonthLastDay = new Date(year, month, 0).getDate();
        // └─ 현재 월의 0번째 날, 이전 달의 마지막 날
        let prevMonthYear = month === 0 ? year - 1 : year;
        let prevMonth = month === 0 ? 11 : month - 1;
        // 이전달의 10일전부터~
        for (let day = prevMonthLastDay - 9; day <= prevMonthLastDay; day++) {
          dates.push(moment([prevMonthYear, prevMonth, day]).format('YYYY-MM-DD'));
        }

        // 이번 달 전체
        let lastDayOfMonth = new Date(year, month + 1, 0).getDate();
        for (let day = 1; day <= lastDayOfMonth; day++) {
          dates.push(moment([year, month, day]).format('YYYY-MM-DD'));
        }

        // 다음 달 10일
        let nextMonthYear = month === 11 ? year + 1 : year;
        let nextMonth = month === 11 ? 0 : month + 1;
        for (let day = 1; day <= 10; day++) {
          dates.push(moment([nextMonthYear, nextMonth, day]).format('YYYY-MM-DD'));
        }

        return dates;
      }


      let currentDate = new Date();
      let year = currentDate.getFullYear();
      let month = currentDate.getMonth();
      let dateRange = getDateRange(year, month);


      // 비동기 처리
      let fetchPromises = dateRange.map(targetDate => {
        return fngetResList(targetDate).then(reserves => {
          rooLists.forEach(roomList => {
            if (roomList.depth != 0) {
              let reservedCount = reserves.filter(reservation => reservation.roomNo === roomList.roomNo).length;
              roomArray.push({
                title: roomList.roomName + ':(' + reservedCount + '/' + roomList.totalRoom + ')',
                start: targetDate
              });
            }
          });
        });
      });



      Promise.all(fetchPromises).then(() => {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth',
          events: roomArray,
          displayEventTime: false,
          eventClick: function(info) {
            var eventDate = info.event.start; // Date 객체
            var formattedDate =  moment(eventDate).format('YYYY-MM-DD');//
            console.log(formattedDate);
            window.location.href = `/admin/reservation/reservedList.do?date=${formattedDate}`;

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




/*


*/
