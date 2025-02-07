window.addEventListener('load', () => {
  
  
  
  
  const reserveDate = document.getElementById('today').textContent;
  //console.log(reserveDate)
  

  Promise.all([
    fetch('/admin/room/data/category', {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json'
         },
      body: JSON.stringify({})
    })
      .then(response => response.json()),
     

    fetch('/admin/reservation/data/all', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ reservationDate: reserveDate })
    })
      .then(response => response.json())
  ])
  .then(([roomData, reservationData]) => {
    const checkInList = document.getElementById('list_checkIn');
    checkInList.innerHTML = '';

    //console.log(roomData, reservationData);

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
          <li class="list">
            <p>객실명: ${roomList.roomName}: ( ${reservationCount} / ${roomList.totalRoom} )</p>
            <div class="details">
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
        <p>
        <span>예약자: ${reservation.resName}</span>
        <span>연락처: ${reservation.resPhone}</span>
        <span>상태: ${reservation.status}</span>
        <span><a href="/">상세보기</a></span>
        </p>
      `).join('');
    }

  });

});
