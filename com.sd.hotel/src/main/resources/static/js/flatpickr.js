$(document).ready(function() {
  // Flatpickr 설정
  let checkinPicker = flatpickr("#checkin", {
    dateFormat: "Y-m-d",
    altInput: true,
    minDate:"today", //오늘 이전 날짜 선택 불가능
    altFormat: "F j, Y",
    onChange: function (selectedDates, dateStr) {
      document.getElementById("checkin").value = dateStr;
      checkoutPicker.set("minDate", selectedDates[0]); //minDate는 Flatpicker내부동작
      
    },
  });

  let checkoutPicker = flatpickr("#checkout", {
    dateFormat: "Y-m-d",
    altInput: true,
    altFormat: "F j, Y",
    onChange: function (selectedDates, dateStr) {
      document.getElementById("checkout").value = dateStr;
      calculateNights(); // 숙박 일수 계산
    },
  });

  function calculateNights() {
    const checkinDate = checkinPicker.selectedDates[0];
    const checkoutDate = checkoutPicker.selectedDates[0];
    if (checkinDate && checkoutDate) {
      const diffTime = Math.abs(checkoutDate - checkinDate);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      document.getElementById("nights").innerText = diffDays;
    }
  }



});
