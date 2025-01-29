$(document).ready(function() {

 





  //예약 취소 후 메시지
  const fnRemoveResult = () => {
    //const removeResult = '${deleteMessage}';

    console.log(removeResult)

    if (removeResult !== '') {
      if (removeResult) {
        alert(removeResult)
      } else {
        alert(errorMessage);
      }
    }

  }



  // 예약 상세보기
  const fnReserveDetail = () => {
    $(document).on('click', '.btn_detail', (e) => {

      const parent = e.target.parentElement;
      const roomNo = parent.querySelector('input#roomNo');
      const reservationNo = parent.querySelector('input#reservationNo');
      console.log(roomNo.value);
      console.log("reservationNo", reservationNo.value);
      const url = '/user/myReserveDetail.do';

      const params = {
        //roomNo: roomNo.value,
        reservationNo: reservationNo.value
      };

      fnSubmitForm(url, params)

    })

  }

  fnSubmitForm = (url, params) => {
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = url;


    for (const key in params) {
      if (params.hasOwnProperty(key)) {
        const input = document.createElement('input');
        input.type = 'hidden'; // 숨겨진 필드
        input.name = key; // 파라미터 이름
        input.value = params[key]; // 파라미터 값
        form.appendChild(input);

      }
    }
    
    //debugger
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);


  }

  fnRemoveResult();


  // 예약 취소하기
  const fnReserveRemove = () => {

    $(document).on('click', '.btn_remove', (e) => {

      if (confirm("현재 선택된 예약 현황을 취소하시겠습니까 ? 한번 취소한 예약은 되돌릴 수 없으므로 신중히 결정해주십시오.")) {
        const parent = e.target.parentElement;
        const roomNo = parent.querySelector('input#roomNo');
        const reservationNo = parent.querySelector('input#reservationNo');

        const url = '/hotel/room/removeRoom.do';

        const params = {
          roomNo: roomNo.value,
          reservationNo: reservationNo.value
        };

        fnSubmitForm(url, params)
      }

    })
  }


  // 결제하기
  const fnPay = () => {
    $(document).on('click', '.btn_pay', (e) => {
      const url="/hotel/room/goReservation.do"
      
        const parent = e.target.parentElement;
        const roomNo = parent.querySelector('input#roomNo');
        const reservationNo = parent.querySelector('input#reservationNo');
      
        const params = {
          roomNo: roomNo.value,
          reservationNo: reservationNo.value
        }
      fnSubmitForm(url, params) 
    })
  }



  fnReserveDetail();
  fnReserveRemove();
  fnPay();

})


