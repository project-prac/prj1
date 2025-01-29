


/* 썸네일 부분 */
var swiper = new Swiper(".mySwiper", {
  loop: true,
  spaceBetween: 10,
  slidesPerView: 3.5,
  freeMode: true,
  watchSlidesProgress: true,
});

/* 메인 */
var swiper2 = new Swiper(".mySwiper2", {
  loop: true,
  spaceBetween: 10,
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  thumbs: {
    swiper: swiper,
  },
});




const fnGetContextPath = () => {

  const host = location.host;  /* localhost:8080 반환 */
  const url = location.href;   /* 현재 웹페이지 전체 URL 반환 http://localhost:8080/mvc/getDate.do */
  const begin = url.indexOf(host) + host.length;
  const end = url.indexOf('/', begin + 1);
  return url.substring(begin, end);
}


$(document).on('click', '.roomName', (e) => {
  let roomNo = e.target.dataset.roomNo;

  location.href = fnGetContextPath() + '/room/room.do?roomNo=' + roomNo;

})