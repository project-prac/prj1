$(document).ready(function() {


window.addEventListener('load', () => {
  
    
    const reserveDate = document.getElementById('today').textContent;
    console.log(typeof reserveDate);
  
    fetch('/admin/reservation/resList.do',{
      method:'POST',
      headers:{
        'Content-Type' : 'application/json'
      },
      body:JSON.stringify({reservationDate : reserveDate})
      
    })
    .then(response => response.json())
    .then(data => {
      
    })
    
    // 객실종류 먼저가져오고, 그후에 예약된 객실 가져와서
    // 객실종류별로 뿌려줄것.
    
    
})

})
