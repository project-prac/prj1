$(document).ready(function() {


window.addEventListener('load', () => {
  
  
    fetch('/admin/reservation/roomList.do',{
      method:'POST',
      headers:{
        'Content-Type' : 'application/json'
      },
      body: JSON.stringify({})
    })
    .then(response => response.json())
    .then(data =>{
      
      
      const checkInList = document.getElementById('list_checkIn');
      checkInList.innerHTML = '';
      let roomLists = data.roomList;
      console.log(roomLists)
      
      roomLists.forEach(roomList =>{
       if(roomList.depth != 0){
        
        let str = '<li class="List">';
        str += '<p>객실명 :'+roomList.roomName+':/'+roomList.totalRoom+'</p>'
          
        checkInList.insertAdjacentHTML('beforeend', str); // 삽입
       }
       
      })
      
    })
  
    /*
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
    
   */
    
    
})

})
