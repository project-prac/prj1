window.addEventListener('load', () => {
  const reserveDate = document.getElementById('today').textContent;

  Promise.all([
    fetch('/admin/reservation/roomList.do', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({})
    })
      .then(response => response.json()),

    fetch('/admin/reservation/resList.do', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ reservationDate: reserveDate })
    })
      .then(response => response.json())
  ]).then(([roomData, reservationData]) => {
    const checkInList = document.getElementById('list_checkIn');
    checkInList.innerHTML = '';

    console.log(roomData, reservationData);

    let reservationCounts = {};

    // 예약 데이터 매핑
    reservationData.reserves.forEach(res => {
      const roomNo = res.roomNo;
      reservationCounts[roomNo] = (reservationCounts[roomNo] || 0) + 1;
    });

    roomData.roomList.forEach(roomList => {
      if (roomList.depth != 0) {
        let reservationCount = reservationCounts[roomList.roomNo] || 0;

        let str = `
          <li class="List">
            <p>객실명: ${roomList.roomName} (총 객실 수: ${roomList.totalRoom}) 
            - 예약 수: ${reservationCount}</p>
            <div class="reservation-details">
              ${generateDetails(roomList.roomNo, reservationData.reserves)}
            </div>
          </li>`;

        checkInList.insertAdjacentHTML('beforeend', str);
      }
    });

    //예약상세
    function generateDetails(roomNo, reserves) {
      const roomReservations = reserves.filter(reservation => reservation.roomNo === roomNo);
      
      return roomReservations.map(reservation => `
        <p>예약자: ${reservation.resName}, 예약 날짜: ${reservation.reservationDate}</p>
      `).join('');
    }

  });

});
