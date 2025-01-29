$(document).ready(function() {
  // 게스트 선택 드롭다운
  const guestButton = document.getElementById("guestButton");
  const guestDropdown = document.getElementById("guestDropdown");
/*
  guestButton.addEventListener("click", (event) => {
    guestDropdown.classList.toggle("show");
  });




document.addEventListener("click", (event) => {
  if (!guestDropdown.contains(event.target) && !guestButton.contains(event.target)) {
    guestDropdown.classList.remove("show");
  }
});*/

  const adultsInput = document.getElementById('adults');
  const infantsInput = document.getElementById('infants');
  const guestCount = document.getElementById('guestCount');
  guestCount.value = parseInt(adultsInput.value) + parseInt(infantsInput.value);
  const maxGuests = 4;

  // 함수: 성인/어린이 수를 조정
  function updateCount(input, delta) {
    const adultsCount = parseInt(adultsInput.value);
    const infantsCount = parseInt(infantsInput.value);

    // 새로운 값 계산
    const newValue = parseInt(input.value) + delta;
    const toatalCount = adultsCount + infantsCount + delta;
    // 조건 확인
    if (input.id === "adults" && newValue < 1) return; // 성인은 1 이상 유지
    if (toatalCount > maxGuests) return; // 총 인원은 4를 초과할 수 없음

    // 값 업데이트
    input.value = newValue;

    guestCount.value = toatalCount;

  }


  document.querySelectorAll("button").forEach(button => {
    button.addEventListener("click", function() {
      const input = this.parentElement.querySelector("input[type='number']");
      if (this.textContent === "+") {
        updateCount(input, 1);
      } else if (this.textContent === "-") {
        updateCount(input, -1);
      }
    });
  });



  /* 객실 이미지 스와이퍼 적용*/
    var swiper = new Swiper(".mySwiper", {
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
      },
    });



})


